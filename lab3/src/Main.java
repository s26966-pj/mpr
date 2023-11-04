public class Main {
    public static void main(String[] args) {
    CarsStorage carsStorage = CarsStorage.getInstance();
    Car car1 = new Car("ford","mondeo", "W011321213213", Quality.STANDARD);
    carsStorage.addCar(car1);
    System.out.println(carsStorage.getAllCars());
    }
}