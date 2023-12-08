import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CarsStorage carsStorage = CarsStorage.getInstance();
        RentalStorage rentalStorage = RentalStorage.getInstance();
        RentalService rentalService = new RentalService(carsStorage, rentalStorage);
        carsStorage.addCar(new Car("opel", "astra", "123", Type.STANDARD));
        carsStorage.addCar(new Car("opel", "astra", "1", Type.STANDARD));
        rentalService.rent(1,"123", LocalDate.of(1290,12,12), LocalDate.of(2000,12,12));
        rentalService.rent(2,"1", LocalDate.of(1290,12,12), LocalDate.of(2000,12,12));

        System.out.println(rentalStorage.getRentalList());
    }
}