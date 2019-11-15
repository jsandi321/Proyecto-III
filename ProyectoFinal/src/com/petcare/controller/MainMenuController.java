package com.petcare.controller;

import com.petcare.util.GameCentral;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class MainMenuController {
    @FXML Button btnLoadGame, btnNewGame;

    public void initialize(){
    }

    public void showLoadGameScreen() throws IOException {
        GameCentral.showScene("LoadGScreen","LoadGame");
    }

    public void showNewGameScreen() throws IOException {
        GameCentral.showScene("NewGScreen","NewGame");
    }

}
