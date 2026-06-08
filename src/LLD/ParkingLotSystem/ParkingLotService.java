package LLD.ParkingLotSystem;

public class ParkingLotService {
    public static void main(String[] args) {
        Vehicle v_car  = new Vehicle("C123", VehicleType.CAR);
        Vehicle v_bus  = new Vehicle("B999", VehicleType.BUS);
        Vehicle v_moto = new Vehicle("M001", VehicleType.MOTORCYCLE);

        ParkingLot lot = new ParkingLot(2, 10);
        System.out.println(lot.parkVehicle(v_car));
        System.out.println(lot.parkVehicle(v_bus));
        System.out.println(lot.getAvailableSpots(VehicleType.MOTORCYCLE));
        System.out.println(lot.parkVehicle(v_moto));
        System.out.println(lot.removeVehicle("C123"));
        System.out.println(lot.getAvailableSpots(VehicleType.CAR));
    }
}
