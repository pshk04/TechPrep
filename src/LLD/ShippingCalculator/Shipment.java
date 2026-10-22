package LLD.ShippingCalculator;

public class Shipment {

    private int id;
    private double distance;
    private Package aPackage;

    public Shipment(int id, double distance, Package aPackage) {
        this.id = id;
        this.distance = distance;
        this.aPackage = aPackage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
