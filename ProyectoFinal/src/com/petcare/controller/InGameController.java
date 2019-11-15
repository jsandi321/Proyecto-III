package com.petcare.controller;

import com.petcare.model.Pet;
import com.petcare.util.GameCentral;
import com.petcare.util.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class InGameController extends Thread{
    private static final Duration REFRESH_FREQUENCY = Duration.millis(83);

    private Pet actualPet;
    private String actualPetName;
    private String previousPetName;

    @FXML private ImageView petImage;
    @FXML private Label lblTime, lblPoints;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private ProgressBar hungryProgressBar, dirtProgressBar, healthProgressBar, happinessProgressBar; //.setProgress

    public void initialize() {
        try{
            if(GameCentral.isFirstTime()){     // IF primera vez que se entra a la pantalla InGame
                System.out.println("true");
                GameCentral.setFirstTime(false);
                GameCentral.startRefreshThread();   // Inicia el Thread
                Time.startReloj();      // Inicia el Thread
            }

            System.out.println("GCPlayerName: "+GameCentral.getPlayer().getName());

            ArrayList<Pet> pets = GameCentral.getPlayer().getPets();

            for(Pet pet: pets){
                choiceBox.getItems().add(pet.getName());
            }

            choiceBox.setValue(pets.get(0).getName());


            actualPet = pets.get(0);
            actualPetName = actualPet.getName().toLowerCase();
            previousPetName = actualPetName;
            petImage.setImage(new Image( "com/petcare/img/"+actualPetName+".gif"));

            refresh();

        } catch (Exception e) {
            System.out.println("Error: Player sin Pets");
        }
    }


    public void limpiar(){
        GameCentral.limpiar(actualPet);
    }

    public void acariciar(){
        GameCentral.acariciar(actualPet);
    }

    public void curar(){
        GameCentral.curar(actualPet);
    }

    public void alimentar(){
        GameCentral.alimentar(actualPet);
    }

    private void getActualPet(){
        String petName = choiceBox.getValue();
        if(!petName.toLowerCase().equals(previousPetName)){
            actualPet = GameCentral.getPlayer().getPetByName(petName);
            actualPetName = actualPet.getName().toLowerCase();

            previousPetName = actualPetName;

            petImage.setImage(new Image( "com/petcare/img/"+petName+".gif"));
        }
    }

    private void refresh(){
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        actionEvent -> refreshScene()
                ),
                new KeyFrame(
                        REFRESH_FREQUENCY
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void refreshScene(){
        getActualPet();

        lblTime.setText(Time.getTime());


        hungryProgressBar.setProgress((double)actualPet.getStatus().getHungry()/5);
        dirtProgressBar.setProgress((double)actualPet.getStatus().getDirt()/5);
        healthProgressBar.setProgress((double)actualPet.getStatus().getHealth()/5);
        happinessProgressBar.setProgress((double)actualPet.getStatus().getHappiness()/5);

        String playerPoints = GameCentral.getPlayer().getPlayerScore().toString();
        String playerName = GameCentral.getPlayer().getName();
        playerName = playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        lblPoints.setText(playerName+"  "+playerPoints+" Puntos");

    }


    public void showPetSelectionScreen() throws IOException {
        GameCentral.showScene("PetSelection","PetSelection");
    }
}
