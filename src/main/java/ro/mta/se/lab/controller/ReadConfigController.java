package ro.mta.se.lab.controller;

import ro.mta.se.lab.model.CityModel;
import ro.mta.se.lab.model.CountryModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadConfigController {

    private ReadConfigController() {
    }

    public static List<CountryModel> readConfigFile(String path){

        boolean verify;
        List<CountryModel> result = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        Scanner scanner;

        try{
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            return null;
        }

        String fileData = null;
        while(scanner.hasNextLine()){
            fileData = scanner.nextLine();
            String data[] = fileData.split("\\s+");
            if(data.length!=3){
                return null;
            }
            CityModel city = new CityModel();
            city.setId(Integer.parseInt(data[0]));
            city.setDenumire(data[1]);
            verify = false;
            for (CountryModel country : result) {
                if (country.getCode().equals(data[2])) {
                    country.getCityList().add(city);
                    break;
                }
            }
            if(!verify){
                CountryModel aux = new CountryModel(new ArrayList<>(), data[2]);
                aux.getCityList().add(city);
                result.add(aux);
            }
        }
        return result;
    }
}
