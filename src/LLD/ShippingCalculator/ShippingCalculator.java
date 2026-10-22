package LLD.ShippingCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ShippingCalculator {

    private Map<Integer, Shipment> shipmentMap;

    public ShippingCalculator() {
        this.shipmentMap = new HashMap<>();
    }

    public void addShipment(int id, String packageType, double weight, double volume, double distance) {
        if(!this.shipmentMap.containsKey(id)){
            Package aPackage = new Package(weight, volume, PackageType.valueOf(packageType));
            Shipment shipment = new Shipment(id, distance, aPackage);
            this.shipmentMap.put(id, shipment);
        }
    }

    public double getTransportCost(int id) {
        double transportCost = 0.0;

        if(this.shipmentMap.containsKey(id)){
            Shipment shipment = this.shipmentMap.get(id);
            Package aPackage = shipment.getaPackage();

            if(aPackage.getPackageType().equals(PackageType.STANDARD)){
                transportCost = aPackage.getWeight() * shipment.getDistance() * 0.5;
            }else if(aPackage.getPackageType().equals(PackageType.HAZARDOUS)){
                transportCost = aPackage.getWeight() * shipment.getDistance() * 1.2;
            }else if(aPackage.getPackageType().equals(PackageType.FRAGILE)){
                transportCost = aPackage.getWeight() * shipment.getDistance() * 0.8;
            }
        }
        return roundDecimalHalfUp(transportCost);
    }

    public double getServiceCost(int id) {
        double serviceCost = 0.0;

        if(this.shipmentMap.containsKey(id)){
            Shipment shipment = this.shipmentMap.get(id);
            Package aPackage = shipment.getaPackage();

            if(aPackage.getPackageType().equals(PackageType.STANDARD)){
                serviceCost = aPackage.getVolume() * 10;
            }else if(aPackage.getPackageType().equals(PackageType.HAZARDOUS)){
                serviceCost = aPackage.getVolume() * 25;
            }else if(aPackage.getPackageType().equals(PackageType.FRAGILE)){
                serviceCost = aPackage.getVolume() * 20;
            }
        }
        return roundDecimalHalfUp(serviceCost);
    }

    public double getOverallCost(int id) {
        double overAllCost = 0.0;

        if(this.shipmentMap.containsKey(id)){
            Shipment shipment = this.shipmentMap.get(id);
            Package aPackage = shipment.getaPackage();

            if(aPackage.getPackageType().equals(PackageType.STANDARD)){
                overAllCost = (this.getTransportCost(id) + this.getServiceCost(id)) * 1.0;
            }else if(aPackage.getPackageType().equals(PackageType.HAZARDOUS)){
                overAllCost = (this.getTransportCost(id) + this.getServiceCost(id)) * 1.5;
            }else if(aPackage.getPackageType().equals(PackageType.FRAGILE)){
                overAllCost = (this.getTransportCost(id) + this.getServiceCost(id)) * 1.25;
            }
        }
        return roundDecimalHalfUp(overAllCost);
    }

    public static double roundDecimalHalfUp(double value){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static void main(String[] args) {
        ShippingCalculator calc = new ShippingCalculator();

        calc.addShipment(1, "STANDARD", 10, 2, 100);
        System.out.println(calc.getTransportCost(1));
        System.out.println(calc.getServiceCost(1));
        System.out.println(calc.getOverallCost(1));

        calc.addShipment(2, "HAZARDOUS", 10, 2, 100);
        System.out.println(calc.getTransportCost(2));
        System.out.println(calc.getServiceCost(2));
        System.out.println(calc.getOverallCost(2));

        System.out.println();

        calc = new ShippingCalculator();

        calc.addShipment(10, "FRAGILE", 5, 1.5, 200);
        System.out.println(calc.getTransportCost(10));
        System.out.println(calc.getServiceCost(10));
        System.out.println(calc.getOverallCost(10));

        calc.addShipment(11, "STANDARD", 1, 1, 1);
        System.out.println(calc.getOverallCost(11));
    }
}
