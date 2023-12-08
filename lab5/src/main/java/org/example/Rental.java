package org.example;

import java.time.LocalDate;

public class Rental {
    private final User user;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Rental(User user, Car car, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
