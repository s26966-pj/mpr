public class Car {
    private final String brand;
    private final String model;
    private final String vin;
    private final Quality quality;

    public Car(String brand, String model, String vin, Quality quality){
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.quality = quality;
    }

    public Quality getQuality() {
        return quality;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

}
