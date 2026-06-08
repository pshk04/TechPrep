package LLD.ParkingLotSystem;

public enum SpotSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String value;

    SpotSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
