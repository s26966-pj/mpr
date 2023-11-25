import org.example.CarsStorage;
import org.example.RentalService;
import org.example.RentalStorage;
import org.junit.jupiter.api.Test;
public class RentalServiceTest {
    private RentalStorage rentalStorage = RentalStorage.getInstance();
    private CarsStorage carsStorage = CarsStorage.getInstance();
    private RentalService rentalService = new RentalService(carsStorage, rentalStorage);

    @Test
    void shouldFindCarByVin() {

    }


}
