package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarsStorage {
    private final List<Car> carsList = new ArrayList<>();

    public List<Car> getAllCars() {
        return carsList;
    }

    public void addCar(Car car) {
        carsList.add(car);
    }

    public void purgeList() {
        carsList.clear();
    }

    public Optional<Car> findCarByVin(String vin) {
        return carsList.stream().filter(car -> car.getVin().equals(vin)).findFirst();
    }
}
