package org.example;

import org.example.CarsStorage;
import org.example.RentalService;
import org.example.RentalStorage;

public class Main {
    public static void main(String[] args) {
        CarsStorage carsStorage = CarsStorage.getInstance();
        RentalStorage rentalStorage = RentalStorage.getInstance();
        RentalService rentalService = new RentalService(carsStorage, rentalStorage);


    }
}