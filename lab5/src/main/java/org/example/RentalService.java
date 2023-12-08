package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalService {

    private final CarsStorage carsStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarsStorage carsStorage, RentalStorage rentalStorage) {
        this.carsStorage = carsStorage;
        this.rentalStorage = rentalStorage;
    }

    public double estimatePrice(String vin, LocalDate startDate, LocalDate endDate) {
        Car car = findCarByVin(vin).orElseThrow();
        double dailyCost = 100;
        int days = (int) Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        double price = days * dailyCost * car.getType().getMultiplier();
        if(price > 0) {
            return days * dailyCost * car.getType().getMultiplier();
        } else return 0;
    }

    public Rental rent(int userId, String vin, LocalDate startDate, LocalDate endDate) {
        Car car = findCarByVin(vin).orElseThrow();
        if (isAvailable(vin, startDate, endDate)) {
            Rental rental = new Rental(new User(userId), car, startDate, endDate);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            return null;
        }
    }

    public boolean isAvailable(String vin, LocalDate startDate, LocalDate endDate) {
        boolean carDoesNotExist = findCarByVin(vin).isEmpty();
        if (carDoesNotExist) {
            return false;
        }
        if (rentalStorage.getRentalList().isEmpty()) {
            return true;
        }
        List<Rental> rentalForVin = rentalStorage.getRentalList()
                .stream().filter(rental -> rental.getCar().getVin().equals(vin)).toList();

        for (Rental rental : rentalForVin) {
            if (isOverlappingDate(startDate, endDate, rental)) {
                return false;
            }
        }
        return true;
    }


    private boolean isOverlappingDate(LocalDate startDate, LocalDate enDate, Rental rental) {
        boolean isEndDateBeforeRentalStart = enDate.isBefore(rental.getStartDate());
        boolean isStartDateAfterRentalEnd = startDate.isAfter(rental.getEndDate());
        return !(isEndDateBeforeRentalStart || isStartDateAfterRentalEnd);
    }

    public Optional<Car> findCarByVin(String vin) {
        return carsStorage.getAllCars().stream().filter(car -> car.getVin().equals(vin)).findFirst();
    }
}
