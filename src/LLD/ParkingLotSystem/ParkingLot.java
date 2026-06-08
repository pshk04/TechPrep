package LLD.ParkingLotSystem;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int numLevels;
    private int spotsPerLevel;
    private int maxSmallSpots;
    private int maxMediumSpots;
    private int maxLargeSpots;
    private int smallSpots;
    private int mediumSpots;
    private int largeSpots;
    private Map<String, ParkingSpot> vehicleParkingSpotMap;

    public ParkingLot(int numLevels, int spotsPerLevel) {
        this.numLevels = numLevels;
        this.spotsPerLevel = spotsPerLevel;
        this.maxSmallSpots = (int)(this.spotsPerLevel * (0.2) * this.numLevels);
        this.maxMediumSpots = (int)(this.spotsPerLevel * (0.5) * this.numLevels);
        this.maxLargeSpots = (int)(this.spotsPerLevel * (0.3) * this.numLevels);
        this.vehicleParkingSpotMap = new HashMap<>();
    }

    public boolean parkVehicle(Vehicle vehicle) {

        String spotType = vehicle.getVehicleType().getValue();
        SpotSize spotSize;

        if(spotType.equals("Motorcycle")){
            spotSize = SpotSize.SMALL;
        } else if (spotType.equals("Car")) {
            spotSize = SpotSize.MEDIUM;
        }else{
            spotSize = SpotSize.LARGE;
        }

        ParkingSpot parkingSpot = new ParkingSpot("1A", spotSize);
        VehicleType type = vehicle.getVehicleType();
        if(!this.vehicleParkingSpotMap.containsKey(vehicle.getLicensePlate()) && this.isSpotAvailable(type)) {
            if(type.equals(VehicleType.CAR) && this.mediumSpots < this.maxMediumSpots){
                this.mediumSpots += 1;
            } else if (type.equals(VehicleType.MOTORCYCLE) && this.smallSpots < this.maxSmallSpots) {
                this.smallSpots += 1;
            }else if (type.equals(VehicleType.BUS) && this.largeSpots < this.maxLargeSpots) {
                this.largeSpots += 1;
            }
            this.vehicleParkingSpotMap.put(vehicle.getLicensePlate(), parkingSpot);
            return true;
        }
        return false;
    }

    public boolean isSpotAvailable(VehicleType type){
        if(type.equals(VehicleType.CAR) && this.mediumSpots < this.maxMediumSpots){
            return true;
        } else if (type.equals(VehicleType.MOTORCYCLE) && this.smallSpots < this.maxSmallSpots) {
            return true;
        }else if (type.equals(VehicleType.BUS) && this.largeSpots < this.maxLargeSpots) {
            return true;
        }else{
            return false;
        }
    }

    public boolean removeVehicle(String licensePlate) {
        if(!this.vehicleParkingSpotMap.containsKey(licensePlate)) {
            return false;
        }else{
            this.updateParkingLotSize(this.vehicleParkingSpotMap.get(licensePlate).getSpotSize());
            this.vehicleParkingSpotMap.remove(licensePlate);
            return true;
        }
    }

    public void updateParkingLotSize(SpotSize size){
        if(size.equals(SpotSize.MEDIUM)){
            this.mediumSpots -= 1;
        } else if (size.equals(SpotSize.SMALL)) {
            this.smallSpots -= 1;
        }else if (size.equals(SpotSize.LARGE)) {
            this.largeSpots -= 1;
        }
    }

    public int getAvailableSpots(VehicleType vehicleType) {
        if(vehicleType.equals(VehicleType.CAR)){
            return (this.maxMediumSpots - this.mediumSpots);
        } else if (vehicleType.equals(VehicleType.MOTORCYCLE)) {
            return (this.maxSmallSpots - this.smallSpots);
        }else{
            return (this.maxLargeSpots - this.largeSpots);
        }
    }
}
