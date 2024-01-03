package com.example.retrovideogamesystemca2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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

    private String generateMachineKey(String machineName, String type, int year, String manufacturer) {return machineName + "-" + type + "-" + year + "-" + manufacturer;}

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
        String name = "";
        String type = "";
        int year = 0;
        String manufacturer = "";

        GameMachine searchedGameMachine = searchGameMachine(name, type, year, manufacturer);

        if (searchedGameMachine != null) {
            gameMachineListSearchView.getItems().clear();
            gameMachineListSearchView.getItems().add(searchedGameMachine);
        } else {
            gameMachineListSearchView.getItems().clear();
            System.out.println("Game machine not found.");
        }
    }
// Game + its fxml portion
    public Game searchGame(String gameTitle, String publisher, String developer, int releaseYear) {
        String key = generateGameKey(gameTitle, publisher, developer, releaseYear);
        return gameHashTable.get(key);
    }
    public void SearchingGame(ActionEvent event) {
        String gameTitle = "";
        String publisher = "";
        String developer = "";
        int releaseYear = 0;

        Game searchedGame = searchGame(gameTitle, publisher, developer, releaseYear);

        if (searchedGame != null) {
            gameListSearchView.getItems().clear();
            gameListSearchView.getItems().add(searchedGame);
        } else {
            gameListSearchView.getItems().clear();
            System.out.println("Game not found.");
        }
    }
    // GamePort + its fxml portion
    public GamePort searchGamePort(Game game, GameMachine machine, int releaseYear) {
        String key = generateGamePortKey(game, machine, releaseYear);
        return gamePortHashTable.get(key);
    }
    public void SearchingGamePort(ActionEvent event) {
        Game game = null;
        GameMachine machine = null;
        int releaseYear = 0;

        GamePort searchedGamePort = searchGamePort(game, machine, releaseYear);

        if (searchedGamePort != null) {
            gamePortListSearchView.getItems().clear();
            gamePortListSearchView.getItems().add(searchedGamePort);
        } else {
            gamePortListSearchView.getItems().clear();
            System.out.println("Game not found.");
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
}
