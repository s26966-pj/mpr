package org.example;

import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalServiceMockTest {
    @Mock
    private CarsStorage carsStorage;

    @Mock
    private RentalStorage rentalStorage;

    @InjectMocks
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
}
