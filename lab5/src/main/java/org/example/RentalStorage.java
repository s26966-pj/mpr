package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentalStorage {
    private static RentalStorage rentalStorage;
    private final List<Rental> rentalList = new ArrayList<>();

    private RentalStorage() {}

    public static RentalStorage getInstance() {
        if (rentalStorage == null) {
            rentalStorage = new RentalStorage();
        }
        return rentalStorage;
    }

    public void addRental(Rental rental){
        rentalList.add(rental);
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void purgeList() {
        rentalList.clear();
    }
}
