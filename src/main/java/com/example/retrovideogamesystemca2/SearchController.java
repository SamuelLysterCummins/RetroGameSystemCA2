package com.example.retrovideogamesystemca2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchController {

    private GamesSystem system;

    public SearchController() {
        this.system = GamesSystem.getInstance();
        initializeHashTables();
    }

    private FastHash<GameMachine> machineHashTable;
    private FastHash<Game> gameHashTable;
    private FastHash<GamePort> gamePortHashTable;

    @FXML
    private Button mySearchButtonMachine;
    @FXML
    private Button mySearchButtonGame;
    @FXML
    private Button mySearchButtonPort;
    @FXML
    private TextField gameMachineName;

    @FXML
    private TextField gameMachineMedia;

    @FXML
    private TextField gameMachineType;

    @FXML
    private TextField gameMachineManufacturer;

    @FXML
    private TextField gameMachineReleaseYear;

    @FXML
    private TextField gameName;

    @FXML
    private TextField gameDeveloper;

    @FXML
    private TextField gamePublisher;

    @FXML
    private TextField gameReleaseYear;

    @FXML
    private TextField portedGameName;

    @FXML
    private TextField portedGameMachine;

    @FXML
    private TextField portedGameReleaseYear;
    @FXML
    private ListView<GameMachine> gameMachineListSearchView;
    @FXML
    private ListView<Game> gameListSearchView;
    @FXML
    private ListView<GamePort> gamePortListSearchView;

    private void initializeHashTables() {
        machineHashTable = new FastHash<>(1000);
        gameHashTable = new FastHash<>(1000);
        gamePortHashTable = new FastHash<>(1000);

        // Need tests for this.
        FastHash<GameMachine> allMachines = system.getGameMachines();
        for (GameMachine machine : allMachines) {
            if (machine != null) {
                String machineKey = generateMachineKey(machine.getMachineName(), machine.getType(), machine.getMachineReleaseYear(), machine.getManufacturer());
                machineHashTable.add(machineKey, machine);
            }
        }

        FastHash<Game> allGames = system.getGames();
        for (Game game : allGames) {
            if (game != null) {
                String gameKey = generateGameKey(game.getGameName(), game.getPublisher(), game.getDeveloper(), game.getGameReleaseYear());
                gameHashTable.add(gameKey, game);
            }
        }

        FastHash<GamePort> allGamePorts = system.getGamePorts();
        for (GamePort gamePort : allGamePorts) {
            if (gamePort != null) {
                String gamePortKey = generateGamePortKey(gamePort.getOriginalGame(), gamePort.getPortedMachine(), gamePort.getGamePortReleaseYear());
                gamePortHashTable.add(gamePortKey, gamePort);
            }
        }
    }

    private String generateMachineKey(String machineName, String type, int year, String manufacturer) {
        return machineName + "-" + type + "-" + year + "-" + manufacturer;
    }

    private String generateGameKey(String gameTitle, String publisher, String developer, int releaseYear) {return gameTitle + "-" + publisher + "-" + developer + "-" + releaseYear;}

    private String generateGamePortKey(Game originalGame, GameMachine portedMachine, int gamePortReleaseYear) {
        String originalGameName = (originalGame != null) ? originalGame.getGameName() : "";
        String portedMachineName = (portedMachine != null) ? portedMachine.getMachineName() : "";
        return originalGameName + "-" + portedMachineName + "-" + gamePortReleaseYear;}



    // GameMachine + its fxml portion
    public GameMachine searchGameMachine(String name, String type, int year, String manufacturer) {
        String key = generateMachineKey(name, type, year, manufacturer);
        return machineHashTable.get(key);
    }
    public void SearchingGameMachine(ActionEvent event) {
        String name = gameMachineName.getText().trim();
        String manufacturer = gameMachineManufacturer.getText().trim();
        String type = gameMachineType.getText().trim();
        int year = 0;

        try {
            if (!gameMachineReleaseYear.getText().trim().isEmpty()) {
                year = Integer.parseInt(gameMachineReleaseYear.getText().trim());
            }
        } catch (NumberFormatException e) {
            gameMachineListSearchView.getItems().clear();
            return;
        }

        GameMachine searchedGameMachine = searchGameMachine(name, type, year, manufacturer);

        gameMachineListSearchView.getItems().clear();
        if (searchedGameMachine != null) {
            gameMachineListSearchView.getItems().add(searchedGameMachine);
        } else {
            System.out.println("Game machine not found.");
        }
    }
// Game + its fxml portion
    public Game searchGame(String gameTitle, String publisher, String developer, int releaseYear) {
        String key = generateGameKey(gameTitle, publisher, developer, releaseYear);
        return gameHashTable.get(key);
    }
    public void SearchingGame(ActionEvent event) {
        String gameTitle = gameName.getText().trim();
        String publisher = gamePublisher.getText().trim();
        String developer = gameDeveloper.getText().trim();
        int releaseYear = 0;

        try {
            if (!gameReleaseYear.getText().trim().isEmpty()) {
                releaseYear = Integer.parseInt(gameReleaseYear.getText().trim());
            }
        } catch (NumberFormatException e) {
            gameListSearchView.getItems().clear();
            return;
        }

        Game searchedGame = searchGame(gameTitle, publisher, developer, releaseYear);

        gameListSearchView.getItems().clear();
        if (searchedGame != null) {
            gameListSearchView.getItems().add(searchedGame);
        } else {
            System.out.println("Game not found.");
        }
    }
    // GamePort + its fxml portion
    public GamePort searchGamePort(String gameName, String machineName, int releaseYear) {
        Game game = system.getGame(gameName);
        GameMachine machine = system.getGameMachine(machineName);

        if (game == null || machine == null) {
            return null;
        }

        String key = generateGamePortKey(game, machine, releaseYear);
        return gamePortHashTable.get(key);
    }
    public void SearchingGamePort(ActionEvent event) {
        String gameName = portedGameName.getText().trim();
        String machineName = portedGameMachine.getText().trim();
        int releaseYear = 0;

        try {
            if (!portedGameReleaseYear.getText().trim().isEmpty()) {
                releaseYear = Integer.parseInt(portedGameReleaseYear.getText().trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid year format");
            gamePortListSearchView.getItems().clear();
            return;
        }

        GamePort searchedGamePort = searchGamePort(gameName, machineName, releaseYear);

        gamePortListSearchView.getItems().clear();
        if (searchedGamePort != null) {
            gamePortListSearchView.getItems().add(searchedGamePort);
        } else {
            System.out.println("Game port not found.");
        }
    }

    // This should be most of the core search stuff done.
    @FXML
    public void initialize() {
    }

    @FXML
    public void switchToGameSystem(ActionEvent event) throws IOException {
        SceneManager.getInstance().switchScene("gameSystem.fxml");
    }

    public void switchToSave(ActionEvent event) throws IOException{
        SceneManager.getInstance().switchScene("save.fxml");
    }
}
