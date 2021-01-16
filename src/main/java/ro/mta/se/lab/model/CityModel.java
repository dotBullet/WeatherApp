package ro.mta.se.lab.model;

public class CityModel {

    private int id;
    private String denumire;

    public CityModel(int id, String denumire) {
        this.id = id;
        this.denumire = denumire;
    }

    public CityModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
