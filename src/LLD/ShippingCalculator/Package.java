package LLD.ShippingCalculator;

public class Package {

    private double weight;
    private double volume;
    private PackageType packageType;

    public Package(double weight, double volume, PackageType packageType) {
        this.weight = weight;
        this.volume = volume;
        this.packageType = packageType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }
}
