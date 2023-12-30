package com.example.retrovideogamesystemca2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class SearchController {

    private GamesSystem system;
    public SearchController(){
        this.system = GamesSystem.getInstance();
    }

    public void initialize(){

    }

    public void switchToGameSystem(ActionEvent event) throws IOException {
        SceneManager.getInstance().switchScene("gameSystem.fxml");
    }
}
