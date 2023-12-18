package com.example.retrovideogamesystemca2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameSystemController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("welcome to JavaFX Application!");
    }
}