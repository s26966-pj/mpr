package org.example;

import org.example.CarsStorage;
import org.example.RentalService;
import org.example.RentalStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@SpringBootApplication
public class Main {
    private final CarsStorage carsStorage;
    private final RentalService rentalService;

    public Main(CarsStorage carsStorage, RentalService rentalService) {
        this.carsStorage = carsStorage;
        this.rentalService = rentalService;

        execProcess();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void execProcess() {
    carsStorage.addCar(new Car("brand", "model", "123", Type.STANDARD));
    rentalService.rent(1, "123", LocalDate.now(), LocalDate.now().plusDays(3));

    boolean isAvailable = rentalService.isAvailable("123",LocalDate.now(), LocalDate.now().plusDays(4));
    System.out.println(isAvailable);
    }
}