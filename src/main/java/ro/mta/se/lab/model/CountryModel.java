package ro.mta.se.lab.model;

import ro.mta.se.lab.model.CityModel;

import java.util.List;

public class CountryModel {

    private List<CityModel> cityList;
    private String code;

    public CountryModel(List<CityModel> cityList, String code) {
        this.cityList = cityList;
        this.code = code;
    }

    public CountryModel() {
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
