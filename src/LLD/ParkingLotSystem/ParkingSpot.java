package LLD.ParkingLotSystem;

public class ParkingSpot {

    private String spotId;
    private SpotSize spotSize;

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public ParkingSpot(String spotId, SpotSize size) {
        this.spotId = spotId;
        this.spotSize = size;
    }

    public boolean isAvailable() {
        // your code here
        return false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        // your code here
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        // your code here
        return false;
    }

    public Vehicle removeVehicle() {
        // your code here
        return null;
    }
}
