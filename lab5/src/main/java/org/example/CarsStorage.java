package org.example;

import java.util.ArrayList;
import java.util.List;
public class CarsStorage {
    private static CarsStorage carsStorage;
    private final List<Car> carsList = new ArrayList<>();

    private CarsStorage() {
    }
    public static CarsStorage getInstance() {
        if (carsStorage == null) {
            carsStorage = new CarsStorage();
        }
        return carsStorage;
    }

    public List<Car> getAllCars() {
        return carsList;
    }

    public void addCar(Car car) {
        carsList.add(car);
    }

}
