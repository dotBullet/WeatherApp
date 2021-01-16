package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class WeatherModel {

    private StringProperty cityLabel;
    private StringProperty weatherLabel;
    private StringProperty degreesLabel;
    private StringProperty humidityLabel;
    private StringProperty windLabel;

    public WeatherModel( String cityLabel, String weatherLabel, String degreesLabel, String humidityLabel, String windLabel) {

        this.cityLabel = new SimpleStringProperty(cityLabel);
        this.weatherLabel = new SimpleStringProperty(weatherLabel);
        this.degreesLabel = new SimpleStringProperty(degreesLabel);
        this.humidityLabel = new SimpleStringProperty(humidityLabel);
        this.windLabel = new SimpleStringProperty(windLabel);
    }

    public String getCityLabel() {
        return cityLabel.get();
    }

    public StringProperty cityLabelProperty() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel.set(cityLabel);
    }

    public String getWeatherLabel() {
        return weatherLabel.get();
    }

    public StringProperty weatherLabelProperty() {
        return weatherLabel;
    }

    public void setWeatherLabel(String weatherLabel) {
        this.weatherLabel.set(weatherLabel);
    }

    public String getDegreesLabel() {
        return degreesLabel.get();
    }

    public StringProperty degreesLabelProperty() {
        return degreesLabel;
    }

    public void setDegreesLabel(String degreesLabel) {
        this.degreesLabel.set(degreesLabel);
    }

    public String getHumidityLabel() {
        return humidityLabel.get();
    }

    public StringProperty humidityLabelProperty() {
        return humidityLabel;
    }

    public void setHumidityLabel(String humidityLabel) {
        this.humidityLabel.set(humidityLabel);
    }

    public String getWindLabel() {
        return windLabel.get();
    }

    public StringProperty windLabelProperty() {
        return windLabel;
    }

    public void setWindLabel(String windLabel) {
        this.windLabel.set(windLabel);
    }
}
