package com.petcare.util;

import com.petcare.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameCentral extends Thread {

    //Atributos
    private static Player player;
    private static Stage stage;
    private static Thread thread;
    private static Boolean firstTime = true;

    //Getters and Setters
    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        GameCentral.player = player;
    }

    public static void setStage(Stage stage) {
        GameCentral.stage = stage;
    }

    public static Boolean isFirstTime() {
        return firstTime;
    }

    public static void setFirstTime(Boolean firstTime) {
        GameCentral.firstTime = firstTime;
    }

    //Metodos

    // Se encarga de actualizar cada dato del juego cada hora dentro de este.
    private static void refreshGameData() {
        //GAME LOOP
        ArrayList<Pet> playerPets = player.getPets();
        int globalScore = 0;

        for (Pet pet : playerPets) {
            refreshPetStatus(pet.getStatus());  // Actualiza el estado de la mascota con respecto a sus horarios.
            delayChecker(pet.getStatus());      // Verifica si hay algún atraso en los horarios de atención y actualiza el estado con respecto a eso.
            refreshPetScore(pet.getStatus());   // Actualiza el puntaje de la mascota con respecto a su estado
            globalScore = globalScore + pet.getStatus().getPetScore();    //Se suma cada petScore para dar con el globalScore
        }
        player.setPlayerScore(globalScore);     //globalScore = Suma de todos los petScore del Player
    }

    // Actualiza el estado de la mascota con respecto a sus horarios de atención.
    private static void refreshPetStatus(Status status) {
        int actualHour = Time.getIntHours();

        ArrayList<Integer> hours = status.getAffectionHours();
        for (Integer hour : hours) {
            if (actualHour == hour) {
                status.decHappiness(1);
                status.setAcariciarDelay(status.getAcariciarDelay() + 1);
            }
        }

        hours = status.getEatingHours();
        for (Integer hour : hours) {
            if (actualHour == hour) {
                status.incHungry(1);
                status.setAlimentarDelay(status.getAlimentarDelay() + 1);
            }
        }

        hours = status.getHygieneHours();
        for (Integer hour : hours) {
            if (actualHour == hour) {
                status.incDirt(1);
                status.setLimpiarDelay(status.getLimpiarDelay() + 1);
            }
        }

        hours = status.getMedicationHours();
        for (Integer hour : hours) {
            if (actualHour == hour) {
                status.decHealth(1);
                status.setCurarDelay(status.getCurarDelay() + 1);
            }
        }
    }

    // Actualiza el puntaje de la mascota con respecto a su estado.
    private static void refreshPetScore(Status status) {
        if (status.getHappiness() > 3) {
            status.incPetScore(10);
        }
        if (status.getHappiness() <= 3) {
            status.decPetScore(10);
        }
        if (status.getHealth() == 5) {
            status.incPetScore(10);
        }
        if (status.getHealth() >= 2) {
            status.decPetScore(10);
        }
        if (status.getDirt() >= 4) {
            status.decPetScore(10);
        }
        if (status.getHungry() < 4) {
            status.incPetScore(10);
        }
    }

    // Acción de curar.
    // Modifica los valores respectivos con dicha acción
    // Health+1     CurarDelay = 0
    public static void curar(Pet pet) {
        pet.getStatus().incHealth(1);
        pet.getStatus().setCurarDelay(0);
    }

    // Acción de limpiar
    // Modifica los valores respectivos con dicha acción
    // Health+1     Dirt=0      Happiness+1     limpiarDelay=0
    public static void limpiar(Pet pet) {
        pet.getStatus().incHealth(1);
        pet.getStatus().decDirt(5);
        pet.getStatus().incHappiness(1);
        pet.getStatus().setLimpiarDelay(0);
    }

    // Acción de acariciar
    // Modifica los valores respectivos con dicha acción
    // Happiness+1     AcariciarrDelay = 0
    public static void acariciar(Pet pet) {
        pet.getStatus().incHappiness(1);
        pet.getStatus().setAcariciarDelay(0);
    }

    // Acción de alimentar
    // Modifica los valores respectivos con dicha acción
    // Hungry=0     AlimentarDelay = 0
    public static void alimentar(Pet pet) {
        pet.getStatus().decHungry(5);
        pet.getStatus().setAlimentarDelay(0);
    }

    // Verifica si hay algún atraso en los horarios de atención y actualiza el estado con respecto a eso.
    private static void delayChecker(Status status) {
        if (status.getCurarDelay() >= 2) {
            status.decHealth(1);
        }
        if (status.getLimpiarDelay() >= 2) {
            status.incDirt(1);
            status.decHealth(1);
        }
        if (status.getAcariciarDelay() >= 2) {
            status.decHappiness(1);
        }
        if (status.getAlimentarDelay() >= 2) {
            status.incHungry(1);
            status.decHealth(1);
        }
    }

    // Thread de actualización de los datos del juego actual
    public static void startRefreshThread() {
        thread = new Thread((() -> {
            Thread ct = Thread.currentThread();
            while (ct == thread) {
                try {
                    refreshGameData();
                    GameFile.saveGameData(player);
                    Thread.sleep(5000); //Pausa de 5000Milisegundos (Una Hora del juego)
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }));
        thread.start();
    }

    // Muestra la escena con nombre fxmlName y cambia el titulo de la ventana a "Pet care -"+title
    public static void showScene(String fxmlName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameCentral.class.getResource("../view/"+fxmlName+".fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("PetCare - "+title);
        stage.setScene(scene);
        stage.show();
    }

}
