package com.petcare.controller;

import com.petcare.model.Pet;
import com.petcare.model.Status;
import com.petcare.util.GameCentral;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class PetSelectionController {

    @FXML ImageView btnDog, btnCat, btnBird, btnRabbit;

    @FXML Button btnBack;

    public void initialize() {
        btnDog.setOnMouseClicked(e -> {
            GameCentral.getPlayer().addPet(defaultDog());
            System.out.println("Dog agregado a Pets");
            try {
                showInGameScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnCat.setOnMouseClicked(e -> {
            GameCentral.getPlayer().addPet(defaultCat());
            System.out.println("Cat agregado a Pets");
            try {
                showInGameScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnBird.setOnMouseClicked(e -> {
            GameCentral.getPlayer().addPet(defaultBird());
            System.out.println("Bird agregado a Pets");
            try {
                showInGameScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnRabbit.setOnMouseClicked(e -> {
            GameCentral.getPlayer().addPet(defaultRabbit());
            System.out.println("Rabbit agregado a Pets");
            try {
                showInGameScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }


    //MASCOTAS PREDETERMINADAS

    private Pet defaultDog(){
        Status status = new Status();
        status.getAffectionHours().add(8);
        status.getAffectionHours().add(15);
        status.getAffectionHours().add(29);
        status.getEatingHours().add(6);
        status.getEatingHours().add(12);
        status.getEatingHours().add(21);
        status.getHygieneHours().add(9);
        status.getMedicationHours().add(10);
        return new Pet("Perro", status);
    }

    private Pet defaultCat(){
        Status status = new Status();
        status.getAffectionHours().add(16);
        status.getEatingHours().add(8);
        status.getEatingHours().add(21);
        status.getHygieneHours().add(22);
        status.getMedicationHours().add(5);

        return new Pet("Gato", status);
    }

    private Pet defaultRabbit(){
        Status status = new Status();

        status.getAffectionHours().add(12);
        status.getAffectionHours().add(6);
        status.getEatingHours().add(9);
        status.getEatingHours().add(22);

        return new Pet("Conejo", status);
    }

    private Pet defaultBird(){
        Status status = new Status();

        status.getAffectionHours().add(7);
        status.getEatingHours().add(10);
        status.getEatingHours().add(20);
        status.getHygieneHours().add(22);
        status.getMedicationHours().add(7);

        return new Pet("Pajarito", status);
    }




    public void showInGameScreen() throws IOException {
        if (GameCentral.getPlayer().getPets().isEmpty()){   // IF Player no tiene Pets
            System.out.println("El Jugador no tiene mascotas");
        }else{
            GameCentral.showScene("InGame","Game");
        }
    }




}
