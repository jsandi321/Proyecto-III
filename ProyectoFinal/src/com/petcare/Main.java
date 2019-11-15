package com.petcare;

import com.petcare.util.GameCentral;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Se le asigna un icono a la ventana
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        // Se guarda el Stage en GameCentral
        GameCentral.setStage(primaryStage);

        //Se muestra la ventana con la escena de MainMenu.fxml
        GameCentral.showScene("MainMenu","MainMenu");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
