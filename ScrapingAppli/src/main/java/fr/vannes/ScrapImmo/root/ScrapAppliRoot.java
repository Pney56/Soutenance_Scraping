package fr.vannes.ScrapImmo.root;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScrapAppliRoot extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/vannes/ScrapImmo/home.fxml"));

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Interface Principale");

        // Définition des dimensions minimales
        primaryStage.setMinWidth(1800); // Largeur minimale
        primaryStage.setMinHeight(920); // Hauteur minimale

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
