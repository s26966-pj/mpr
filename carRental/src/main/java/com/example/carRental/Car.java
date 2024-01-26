package com.example.carRental;

public class Car {
    private String brand;
    private String model;
    private String vin;
    private Type type;

    public Car(String brand, String model, String vin, Type type) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.type = type;
    }

}
