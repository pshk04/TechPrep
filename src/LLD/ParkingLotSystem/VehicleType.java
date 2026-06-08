package LLD.ParkingLotSystem;

public enum VehicleType {
    MOTORCYCLE("Motorcycle"),
    CAR("Car"),
    BUS("Bus");

    private final String value;

    VehicleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
