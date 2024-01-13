package org.example;

import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceIntTest {

    @MockBean
    private CarsStorage carsStorage;

    @MockBean
    private RentalStorage rentalStorage;

    @Autowired
    private RentalService rentalService;

    @Test
    void shouldCalculateEstimatedPrice() {
        //GIVEN
        Car value = new Car("brand", "model", "asdasdas", Type.STANDARD);
        when(carsStorage.findCarByVin(anyString())).thenReturn(Optional.of(value));
        //WHEN
        double estimatePrice = rentalService.estimatePrice("fsdfsgrgdg", LocalDate.now(),LocalDate.now().plusDays(3));
        //THEN
        assertThat(estimatePrice).isEqualTo(300);
    }

    @Test
    void shouldBeAvailable() {
        Car car = new Car("model", "brand", "123", Type.STANDARD);
        when(carsStorage.findCarByVin(anyString())).thenReturn(Optional.of(car));

        Rental rental1 = new Rental(new User(1), car, LocalDate.now(), LocalDate.now().plusDays(9));
        Rental rental2 = new Rental(new User(1), car, LocalDate.now().plusDays(21), LocalDate.now().plusDays(30));
        rentalStorage.addRental(rental1);
        rentalStorage.addRental(rental2);

        boolean isAvailable = rentalService.isAvailable("123", LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));

        assertThat(isAvailable).isTrue();
    }
}
