package ro.mta.se.lab.model;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.controller.MainViewController;
import ro.mta.se.lab.controller.ReadConfigController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Clasa ce va realiza doua teste.
 *
 * @author Bucur Bogdan-Andrei
 */

public class CityModelTest {

    MainViewController mainViewController = new MainViewController();

    /**
     * Testam daca primim informatii de la clasa ReadConfigController, cu alte cuvinte daca
     * avem minim o tara in acel fisier.
     */
    @Test
    public void getAllInfoFromConfig() {
        String path = "src/main/resources/config.cfg";
        assertNotNull(ReadConfigController.readConfigFile(path));
    }

    /**
     * Testam daca reusim sa primim raspuns de la API-ul openweathermap.org
     *
     */
    @Test
    public void doAPIRequest() {
        assertNotNull(mainViewController.getWeather("Razvilka"));
        assertNotNull(mainViewController.getWeather("Ploufragan"));
    }

}