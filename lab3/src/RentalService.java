import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalService {

    // rent, isAvailable, estimatePrice
    private final CarsStorage carsStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarsStorage carsStorage, RentalStorage rentalStorage) {
        this.carsStorage = carsStorage;
        this.rentalStorage = rentalStorage;
    }

    /*
    rentalService.rent(vin, userId, startDate, endDate) --3--
        czy samochod istnieje
        czy jest dostpeny --4--
            if
            true: wynajac samochod
            else: powiadomic ze nie istnieje
        zwrocic status wynajecia

    rentalService.isAvailable(vin, startDate, endDate)
        czy samochod istnieje
        czy istnieje rental dla tego samochodu
            if
            true: data poczatkowa dostepnosci mniejsza od daty koncowej rentala
                  data koncowa dostepnosci mniejsza od poczatkowej rentala
            false: zwracamy informacje ze auto jest dostepne

    rentalService.estimatePrice(vin, startDate, endDate) --2--
        wyszukac samochod dla VINu --1--
        ilosc dni * cena za dzien * wspolczynnik z Typu samochodu
     */

    private Optional<Car> getCar(String vin) {
        return carsStorage.getAllCars().stream().filter(car -> car.getVin().equals(vin)).findFirst();
    }

    public double estimatePrice(String vin, LocalDate startDate, LocalDate endDate) {
        Car car = getCar(vin).orElseThrow();
        int dailyCost = 100;
        int days = (int) Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        return days * dailyCost * car.getType().getMultiplier();
    }

    public Rental rent(int userId, String vin, LocalDate startDate, LocalDate endDate) {
        Car car = getCar(vin).orElseThrow();
        if (isAvailable(vin, startDate, endDate)) {
            Rental rental = new Rental(new User(userId), car, startDate, endDate);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            return null;
        }
    }

    public boolean isAvailable(String vin, LocalDate startDate, LocalDate endDate) {
        boolean carDoesNotExist = getCar(vin).isEmpty();
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
        boolean isEndDateBeforeRentalStart = enDate.isBefore(rental.getDateFrom());
        boolean isStartDateAfterRentalEnd = startDate.isAfter(rental.getDateTo());
        return !(isEndDateBeforeRentalStart || isStartDateAfterRentalEnd);
    }
}
