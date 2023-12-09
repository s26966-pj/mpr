package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private final List<Rental> rentalList = new ArrayList<>();

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
