package com.petcare.controller;

import com.petcare.util.GameCentral;
import com.petcare.util.GameFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class LoadGScreenController {

    @FXML private TextField textFieldName;

    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
    }

    @FXML
    public void loadGame() throws IOException, ParseException {
        String filename = textFieldName.getText().toLowerCase();
        if(new File("SaveGames/"+filename+".json").exists()){   // IF existe una partida con ese nombre

            GameFile.setFileName(filename);
            GameCentral.setPlayer(GameFile.loadGameDate());

            System.out.println("GameFile name: "+GameFile.getFileName());
            GameCentral.showScene("InGame","Game");

        }else{
            errorLabel.setText("No existe una partida con ese nombre");
        }
    }

}
