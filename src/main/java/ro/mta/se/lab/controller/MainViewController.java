package ro.mta.se.lab.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import ro.mta.se.lab.model.CityModel;
import ro.mta.se.lab.model.CountryModel;
import ro.mta.se.lab.model.WeatherModel;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.List;


public class MainViewController {

    private List<CountryModel> countryList;

    @FXML
    ChoiceBox countries;
    @FXML
    ChoiceBox cities;

    @FXML
    private Label cityLabel;
    @FXML
    private Label weatherLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label degreesLabel;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label windLabel;

    public MainViewController(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        if(countryList.isEmpty()){return;}
        else{
            for (CountryModel countryModel : countryList){
                if(countries.getItems().contains(countryModel.getCode()))
                {
                    System.out.println(countryModel.getCode()+ " exista deja !");
                }
                else
                    countries.getItems().add(countryModel.getCode());
            }

            countries.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    String countryCode = (String) countries.getItems().get((Integer) t1);
                    cities.getItems().clear();
                    for (CountryModel countryModel : countryList){
                        if(countryModel.getCode().equalsIgnoreCase(countryCode) && cities.getItems().isEmpty()){
                            for(CityModel cityModel : countryModel.getCityList()){
                                cities.getItems().add(cityModel.getDenumire());
                            }
                        }
                    }
                }
            });
            cities.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if(cities.getItems().size() != 0) {
                        String cityName = (String) cities.getItems().get((Integer) t1);
                        JSONObject jsonObject = getWeather(cityName);
                        cityLabel.setText(cityName);
                        weatherLabel.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
                        degreesLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) + " \u2103");
                        humidityLabel.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) + "%");
                        windLabel.setText(String.format("%.1f Km/H", (jsonObject.getJSONObject("wind").getDouble("speed") * 3.6)));

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MILLISECOND, -calendar.getTimeZone().getOffset(calendar.getTimeInMillis()));
                        calendar.add(Calendar.SECOND, jsonObject.getInt("timezone"));

                        timeLabel.setText(calendar.getTime().toString());

                        final String pathHistory = "src/main/resources/history";
                        writeHistory(pathHistory,String.valueOf(jsonObject));
                    }
                }
            });

        }
    }

    private void writeHistory(String file , String data){
        try{
        FileWriter fileWriter = null;
            fileWriter = new FileWriter(file, true);
        fileWriter.write(data + "\n");
        fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public JSONObject getWeather(String city) {
        JSONObject jsonObject = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0243a56442a8b9ef2b80ab504a51dd3c"))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonObject = new JSONObject(response.body().toString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
        return jsonObject;
    }

}
