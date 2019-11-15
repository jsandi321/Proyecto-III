package com.petcare.controller;

import com.petcare.model.Player;
import com.petcare.util.GameCentral;
import com.petcare.util.GameFile;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewGScreenController {

    @FXML
    private TextField textFieldName;

    public void showPetSelectionScene() throws IOException {
        String filename = textFieldName.getText().toLowerCase();
        GameCentral.setPlayer(new Player(filename));
        GameFile.setFileName(filename);
        GameCentral.showScene("PetSelection","PetSelection");
    }
}
