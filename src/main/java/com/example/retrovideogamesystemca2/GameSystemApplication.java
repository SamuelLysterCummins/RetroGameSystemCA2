package com.example.retrovideogamesystemca2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSystemApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager.getInstance().setPrimaryStage(primaryStage);
        SceneManager.getInstance().switchScene("gameSystem.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }

}