package com.example.retrovideogamesystemca2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSystemController {

    private FastHash<GameMachine> gameMachines = new FastHash<>(10);
    private FastHash<Game> games = new FastHash<>(10);
    private FastHash<GamePort> gamePorts = new FastHash<>(10);

    @FXML
    private ListView<GameMachine> gameMachineListView;
    @FXML
    private ListView<Game> gameListView;
    @FXML
    private ListView<GamePort> gamePortListView;
    @FXML
    private Button myDeleteButton;

    private GamesSystem system;

    public GameSystemController(){
        system = GamesSystem.getInstance();
    }

    public void initialize(){
refreshGameMachines();
        refreshGames();
        refreshPortedGames();
    }

    public void refreshGameMachines() {
        gameMachineListView.getItems().clear();
        if (system.getGameMachines() != null) {
            for (GameMachine gameMachine : system.getGameMachines()) {
                gameMachineListView.getItems().add(gameMachine);
            }
        }
    }


    public void refreshGames(){
        gameListView.getItems().clear();
        if (system.getGames() != null) {
            for (Game game : system.getGames()) {
                gameListView.getItems().add(game);
            }
        }
    }

    public void refreshPortedGames(){
gamePortListView.getItems().clear();
        if (system.getGames() != null) {
            for (Game game : system.getGames()) {
                if (game.getPorts() != null) {
                    for (GamePort gamePort : game.getPorts()) {
                        gamePortListView.getItems().add(gamePort);
                    }
                }
            }
        }
    }

    public void showAddGameMachineDialog() {
        gameListView.getSelectionModel().clearSelection();
        gamePortListView.getSelectionModel().clearSelection();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Game Machine");

        VBox dialogVbox = new VBox(20);
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Machine Name");
        TextField manufacturerField = new TextField();
        manufacturerField.setPromptText("Enter Manufacturer");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter Description");
        TextField typeField = new TextField();
        typeField.setPromptText("Enter Type");
        TextField mediaField = new TextField();
        mediaField.setPromptText("Enter Media Type");
        TextField yearField = new TextField();
        yearField.setPromptText("Enter Release Year");
        TextField priceField = new TextField();
        priceField.setPromptText("Enter Price");
        TextField imageUrlField = new TextField();
        imageUrlField.setPromptText("Enter Image URL");

        Button submitButton = new Button("Add");
        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String manufacturer = manufacturerField.getText();
                String description = descriptionField.getText();
                String type = typeField.getText();
                String media = mediaField.getText();
                int year = Integer.parseInt(yearField.getText());
                double price = Double.parseDouble(priceField.getText());
                String imageUrl = imageUrlField.getText();

                addGameMachineButton(name, manufacturer, description, type, media, year, price, imageUrl);
                dialog.close();
            } catch (NumberFormatException ex) {

                System.out.println("Invalid input: " + ex.getMessage());
            }
        });

        dialogVbox.getChildren().addAll(nameField, manufacturerField, descriptionField, typeField,
                mediaField, yearField, priceField, imageUrlField, submitButton);

        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void showAddGameDialog() {
        gameMachineListView.getSelectionModel().clearSelection();
        gamePortListView.getSelectionModel().clearSelection();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Game");

        VBox dialogVbox = new VBox(20);
        TextField gameNameField = new TextField();
        gameNameField.setPromptText("Enter Game Name");
        TextField publisherField = new TextField();
        publisherField.setPromptText("Enter Publisher");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter Description");
        TextField developerField = new TextField();
        developerField.setPromptText("Enter Developer");
        TextField machineDevelopedForField = new TextField();
        machineDevelopedForField.setPromptText("Enter Machine Developed For");
        TextField gameReleaseYearField = new TextField();
        gameReleaseYearField.setPromptText("Enter Game Release Year");
        TextField coverField = new TextField();
        coverField.setPromptText("Enter Cover URL");

        Button submitButton = new Button("Add");
        submitButton.setOnAction(e -> {
            try {
                String gameName = gameNameField.getText();
                String publisher = publisherField.getText();
                String description = descriptionField.getText();
                String developer = developerField.getText();
                String machineDevelopedFor = machineDevelopedForField.getText();
                int gameReleaseYear = Integer.parseInt(gameReleaseYearField.getText());
                String cover = coverField.getText();

                addGameButton(gameName, publisher, description, developer, machineDevelopedFor, gameReleaseYear, cover);
                dialog.close();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
        });

        dialogVbox.getChildren().addAll(gameNameField, publisherField, descriptionField, developerField,
                machineDevelopedForField, gameReleaseYearField, coverField, submitButton);

        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void showAddGamePortDialog() {
        gameMachineListView.getSelectionModel().clearSelection();
        gameListView.getSelectionModel().clearSelection();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Game Port");

        VBox dialogVbox = new VBox(20);
        TextField originalGameField = new TextField();
        originalGameField.setPromptText("Enter Original Game Name");
        TextField portedMachineField = new TextField();
        portedMachineField.setPromptText("Enter Ported Machine Name");
        TextField portDeveloperField = new TextField();
        portDeveloperField.setPromptText("Enter Port Developer");
        TextField gamePortReleaseYearField = new TextField();
        gamePortReleaseYearField.setPromptText("Enter Game Port Release Year");
        TextField coverField = new TextField();
        coverField.setPromptText("Enter Cover URL");

        Button submitButton = new Button("Add");
        submitButton.setOnAction(e -> {
            try {
                String originalGameName = originalGameField.getText();
                String portedMachineName = portedMachineField.getText();
                String portDeveloper = portDeveloperField.getText();
                int gamePortReleaseYear = Integer.parseInt(gamePortReleaseYearField.getText());
                String cover = coverField.getText();

                addGamePortButton(originalGameName, portedMachineName, portDeveloper, gamePortReleaseYear, cover);
                dialog.close();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
        });

        dialogVbox.getChildren().addAll(originalGameField, portedMachineField, portDeveloperField,
                gamePortReleaseYearField, coverField, submitButton);

        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public void addGameMachineButton(String name, String manufacturer, String description, String type, String media, int year, double price, String imageUrl) {
        GameMachine gameMachine = new GameMachine(name, manufacturer, description, type, media, year, price, imageUrl);
        system.addGameMachine(name, gameMachine);
        refreshGameMachines();
    }



    public void addGameButton(String gameName, String publisher, String description, String developer, String machineDevelopedFor, int gameReleaseYear, String cover) {
        Game game = new Game(gameName, publisher, description, developer, machineDevelopedFor, null, gameReleaseYear, cover);
        system.addGame(gameName, game);
        refreshGames();
        }


    public void addGamePortButton(String originalGameName, String portedMachineName, String portDeveloper, int gamePortReleaseYear, String cover) {
        Game originalGame = system.getGame(originalGameName);
        GameMachine portedMachine = system.getGameMachine(portedMachineName);

        if (originalGame != null && portedMachine != null) {
            GamePort gamePort = new GamePort(originalGame, portedMachine, portDeveloper, gamePortReleaseYear, cover);
            originalGame.addPort(originalGameName + "-" + portedMachineName, gamePort);
            system.addGame(originalGameName, originalGame);
            gamePortListView.getItems().add(gamePort);
        } else {
            showAlert("Error", "Original game or ported machine not found");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void deleteButton(ActionEvent event) {

        if (!gameMachineListView.getSelectionModel().isEmpty()) {
            deleteSelectedGameMachine();
            gameListView.getSelectionModel().clearSelection();
            gamePortListView.getSelectionModel().clearSelection();
        } else if (!gameListView.getSelectionModel().isEmpty()) {
            deleteSelectedGame();
            gameMachineListView.getSelectionModel().clearSelection();
            gamePortListView.getSelectionModel().clearSelection();
        } else if (!gamePortListView.getSelectionModel().isEmpty()) {
            deleteSelectedGamePort();
            gameMachineListView.getSelectionModel().clearSelection();
            gameListView.getSelectionModel().clearSelection();
        } else {
            showAlert("Error", "No item selected for deletion");
        }
    }

    public void deleteSelectedGameMachine() {
        GameMachine selectedGameMachine = gameMachineListView.getSelectionModel().getSelectedItem();
        if (selectedGameMachine != null) {
            gameMachines.remove(selectedGameMachine.getMachineName());
            gameMachineListView.getItems().remove(selectedGameMachine);
        } else {
            showAlert("Error", "No game machine selected for deletion");
        }
    }

    public void deleteSelectedGame() {
        Game selectedGame = gameListView.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            games.remove(selectedGame.getGameName());
            gameListView.getItems().remove(selectedGame);
        } else {
            showAlert("Error", "No game selected for deletion");
        }
    }

    public void deleteSelectedGamePort() {
        GamePort selectedGamePort = gamePortListView.getSelectionModel().getSelectedItem();
        if (selectedGamePort != null) {
            String key = selectedGamePort.getOriginalGame().getGameName() + "-" + selectedGamePort.getPortedMachine().getMachineName();
            gamePorts.remove(key);
            gamePortListView.getItems().remove(selectedGamePort);
        } else {
            showAlert("Error", "No game port selected for deletion");
        }
    }

    @FXML
    private void editButton(ActionEvent event) {
        if (!gameMachineListView.getSelectionModel().isEmpty()) {
            editGameMachineDialog();
        } else if (!gameListView.getSelectionModel().isEmpty()) {
            editGameDialog();
        } else if (!gamePortListView.getSelectionModel().isEmpty()) {
            editGamePortDialog();
        } else {
            showAlert("Error", "No item selected for editing");
        }
    }

    @FXML
    private void editGameMachineDialog() {
        GameMachine selectedGameMachine = gameMachineListView.getSelectionModel().getSelectedItem();
        if (selectedGameMachine != null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Edit Game Machine");

            VBox dialogVbox = new VBox(20);
            TextField nameField = new TextField(selectedGameMachine.getMachineName());
            TextField manufacturerField = new TextField(selectedGameMachine.getManufacturer());
            TextField descriptionField = new TextField(selectedGameMachine.getDescription());
            TextField typeField = new TextField(selectedGameMachine.getType());
            TextField mediaField = new TextField(selectedGameMachine.getMedia());
            TextField yearField = new TextField(String.valueOf(selectedGameMachine.getMachineReleaseYear()));
            TextField priceField = new TextField(String.valueOf(selectedGameMachine.getMachinePrice()));
            TextField imageUrlField = new TextField(selectedGameMachine.getImageUrl());

            Button submitButton = new Button("Save Changes");
            submitButton.setOnAction(e -> {
                selectedGameMachine.setMachineName(nameField.getText());
                selectedGameMachine.setManufacturer(manufacturerField.getText());
                selectedGameMachine.setDescription(descriptionField.getText());
                selectedGameMachine.setType(typeField.getText());
                selectedGameMachine.setMedia(mediaField.getText());
                selectedGameMachine.setMachineReleaseYear(Integer.parseInt(yearField.getText()));
                selectedGameMachine.setMachinePrice(Double.parseDouble(priceField.getText()));
                selectedGameMachine.setImageUrl(imageUrlField.getText());
                gameMachineListView.refresh();
                dialog.close();
            });

            dialogVbox.getChildren().addAll(nameField, manufacturerField, descriptionField, typeField, mediaField, yearField, priceField, imageUrlField, submitButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 400);
            dialog.setScene(dialogScene);
            dialog.show();
        } else {
            showAlert("Error", "No game machine selected for editing");
        }
    }

    @FXML
    private void editGameDialog() {
        Game selectedGame = gameListView.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Edit Game");

            VBox dialogVbox = new VBox(20);
            TextField gameNameField = new TextField(selectedGame.getGameName());
            TextField publisherField = new TextField(selectedGame.getPublisher());
            TextField descriptionField = new TextField(selectedGame.getDescription());
            TextField developerField = new TextField(selectedGame.getDeveloper());
            TextField machineDevelopedForField = new TextField(selectedGame.getMachineDevelopedFor());
            TextField gameReleaseYearField = new TextField(String.valueOf(selectedGame.getGameReleaseYear()));
            TextField coverField = new TextField(selectedGame.getCover());

            Button submitButton = new Button("Save Changes");
            submitButton.setOnAction(e -> {
                selectedGame.setGameName(gameNameField.getText());
                selectedGame.setPublisher(publisherField.getText());
                selectedGame.setDescription(descriptionField.getText());
                selectedGame.setDeveloper(developerField.getText());
                selectedGame.setMachineDevelopedFor(machineDevelopedForField.getText());
                selectedGame.setGameReleaseYear(Integer.parseInt(gameReleaseYearField.getText()));
                selectedGame.setCover(coverField.getText());
                gameListView.refresh();
                dialog.close();
            });

            dialogVbox.getChildren().addAll(gameNameField, publisherField, descriptionField, developerField, machineDevelopedForField, gameReleaseYearField, coverField, submitButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 400);
            dialog.setScene(dialogScene);
            dialog.show();
        } else {
            showAlert("Error", "No game selected for editing");
        }
    }

    private void editGamePortDialog() {
        GamePort selectedGamePort = gamePortListView.getSelectionModel().getSelectedItem();
        if (selectedGamePort != null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Edit Game Port");

            VBox dialogVbox = new VBox(20);
            TextField originalGameField = new TextField(selectedGamePort.getOriginalGame().getGameName());
            TextField portedMachineField = new TextField(selectedGamePort.getPortedMachine().getMachineName());
            TextField portDeveloperField = new TextField(selectedGamePort.getPortDeveloper());
            TextField gamePortReleaseYearField = new TextField(String.valueOf(selectedGamePort.getGamePortReleaseYear()));
            TextField coverField = new TextField(selectedGamePort.getCover());

            Button submitButton = new Button("Save Changes");
            submitButton.setOnAction(e -> {
                gamePortListView.refresh();
                dialog.close();
            });

            dialogVbox.getChildren().addAll(originalGameField, portedMachineField, portDeveloperField, gamePortReleaseYearField, coverField, submitButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 400);
            dialog.setScene(dialogScene);
            dialog.show();
        } else {
            showAlert("Error", "No game port selected for editing");
        }
    }

    public void switchToSearch(ActionEvent event) throws IOException{
        SceneManager.getInstance().switchScene("search.fxml");
    }
}