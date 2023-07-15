package de.aittr.auto;

public class Auto {

    private Long id;
    private String plate;
    private String brand;
    private String owner;

    public Auto() {
    }

    public Auto(Long id, String plate, String brand, String owner) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
