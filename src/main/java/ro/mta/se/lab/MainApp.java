package ro.mta.se.lab;

import ro.mta.se.lab.controller.MainViewController;
import ro.mta.se.lab.controller.ReadConfigController;
import ro.mta.se.lab.model.CountryModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Clasa principala al acestui proiect.
 *
 * @author Bucur Bogdan-Andrei
 */

public class MainApp extends Application {

    public static void main(String[] args )
            {
        launch(args);
    }


    public void start(Stage primaryStage) {

        String path = "src/main/resources/config.cfg";
        List<CountryModel> countryList = ReadConfigController.readConfigFile(path);
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().getResource("/view/MainView.fxml"));
            loader.setController(new MainViewController(countryList));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Weather Forecast App");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
