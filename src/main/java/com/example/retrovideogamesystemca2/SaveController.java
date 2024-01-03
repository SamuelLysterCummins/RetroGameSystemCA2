package com.example.retrovideogamesystemca2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SaveController {

    private GamesSystem system;

    public SaveController() {
        this.system = GamesSystem.getInstance();
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            system.save("src/main/resources/system_data.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void load(ActionEvent event) {
        try {
            GamesSystem loadedSystem = GamesSystem.load("src/main/resources/system_data.xml");
            GamesSystem.setInstance(loadedSystem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToGameSystem(ActionEvent event) throws IOException {
        SceneManager.getInstance().switchScene("gameSystem.fxml");
    }

    public void switchToSearch(ActionEvent event) throws IOException{
        SceneManager.getInstance().switchScene("search.fxml");
    }
}
