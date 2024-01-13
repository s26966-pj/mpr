package org.example;

import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {
    private RentalStorage rentalStorage;
    private CarsStorage carsStorage;
    private RentalService rentalService;

    @BeforeEach
    void startUp() {
        rentalStorage = new RentalStorage();
        carsStorage = new CarsStorage();
        rentalService = new RentalService(carsStorage, rentalStorage);
    }

    @AfterEach
    void endUp() {
        carsStorage.purgeList();
        rentalStorage.purgeList();
    }

    @Test
    void shouldFindCarByVin() {
        //GIVEN
        String equalVin = "123";
        Car car = new Car("model", "brand", equalVin, Type.STANDARD);
        carsStorage.addCar(car);
        //WHEN
        Optional<Car> searchedCar = carsStorage.findCarByVin(equalVin);
        //THEN
        assertThat(searchedCar).isPresent();
    }

    @Test
    void shouldNotFindCarByVin() {
        String vin1 = "123";
        String vin2 = "456";
        Car car = new Car("model", "brand", vin1, Type.STANDARD);
        carsStorage.addCar(car);

        Optional<Car> searchedCar = carsStorage.findCarByVin(vin2);

        assertThat(searchedCar).isNotPresent();
    }

    @Test
    void shouldEstimatePrice() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);

        double estimatedPrice = rentalService.estimatePrice(vin, LocalDate.now(), LocalDate.now().plusDays(5));

        assertThat(estimatedPrice).isEqualTo(500);
    }

    @Test
    void shouldNotEstimatePrice() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);

        double estimatedPrice = rentalService.estimatePrice(vin, LocalDate.now(), LocalDate.now().plusDays(-5));

        assertThat(estimatedPrice).isEqualTo(0);
    }

    @Test
    void shouldBeAvailable() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);
        Rental rental1 = new Rental(new User(1), car, LocalDate.now(), LocalDate.now().plusDays(9));
        Rental rental2 = new Rental(new User(1), car, LocalDate.now().plusDays(21), LocalDate.now().plusDays(30));
        rentalStorage.addRental(rental1);
        rentalStorage.addRental(rental2);

        boolean isAvailable = rentalService.isAvailable(vin, LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));

        assertThat(isAvailable).isTrue();
    }

    @Test
    void shouldNotBeAvailableByOverlappingDateByStartDate() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);
        Rental rental = new Rental(new User(1), car, LocalDate.now(), LocalDate.now().plusDays(10));
        rentalStorage.addRental(rental);

        boolean isAvailable = rentalService.isAvailable(vin, LocalDate.now().plusDays(9), LocalDate.now().plusDays(20));

        assertThat(isAvailable).isFalse();
    }

    @Test
    void shouldNotBeAvailableByOverlappingDateByEndDate() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);
        Rental rental = new Rental(new User(1), car, LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        rentalStorage.addRental(rental);

        boolean isAvailable = rentalService.isAvailable(vin, LocalDate.now(), LocalDate.now().plusDays(11));

        assertThat(isAvailable).isFalse();
    }

    @Test
    void shouldNotBeAvailableByCarDoesNotExist() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);

        boolean isAvailable = rentalService.isAvailable("notvin", LocalDate.now(), LocalDate.now().plusDays(11));

        assertThat(isAvailable).isFalse();
    }

    @Test
    void shouldRentCar() {
        String vin = "123";
        Car car = new Car("model", "brand", vin, Type.STANDARD);
        carsStorage.addCar(car);

        Rental rental = rentalService.rent(1, vin, LocalDate.now(), LocalDate.now().plusDays(1));

        assertThat(rental).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("inputData")
    void shouldHaveOverlappingDates(LocalDate startDate, LocalDate endDate) {
        Car car = new Car("model", "brand", "123", Type.STANDARD);
        carsStorage.addCar(car);

        boolean abc = rentalService.isAvailable(car.getVin(), startDate, endDate);

        assertThat(abc).isTrue();
    }

    public static Stream<Arguments> inputData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023,11,25), LocalDate.of(2023,11,30)),
                Arguments.of(LocalDate.of(2023,11,15), LocalDate.of(2023,11,26)),
                Arguments.of(LocalDate.of(2023,11,27), LocalDate.of(2023,11,28)),
                Arguments.of(LocalDate.of(2023,11,29), LocalDate.of(2023,11,6)),
                Arguments.of(LocalDate.of(2023,11,20), LocalDate.of(2023,11,25))
        );
    }

    @ParameterizedTest
    @MethodSource("")
    void shouldHaveNotOverlappingDates(LocalDate startDate, LocalDate endDate) {
        Car car = new Car("model", "brand", "123", Type.STANDARD);
        carsStorage.addCar(car);
        Rental rental = new Rental(new User(1), car, LocalDate.of(2023,11,20), LocalDate.of(2023,11,25));
        rentalStorage.addRental(rental);

        boolean isAvailable = rentalService.isAvailable(car.getVin(), startDate, endDate);

        assertThat(isAvailable).isTrue();
    }



}
