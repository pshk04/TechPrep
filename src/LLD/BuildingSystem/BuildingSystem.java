package LLD.BuildingSystem;

import java.util.HashMap;
import java.util.Map;

public class BuildingSystem {

    private Map<Integer, BuildingStructure> buildingsMap;

    public BuildingSystem() {
        this.buildingsMap = new HashMap<>();
    }

    public void createHospital(int buildingId) {

        BuildingStructure newBuilding = new BuildingStructure(buildingId, 0, "Hospital");
        this.buildingsMap.put(buildingId, newBuilding);
    }

    public void createSchool(int buildingId) {
        BuildingStructure newBuilding = new BuildingStructure(buildingId, 0, "School");
        this.buildingsMap.put(buildingId, newBuilding);
    }

    public void buildFloor(int buildingId) {

        if(this.buildingsMap.containsKey(buildingId)){
            BuildingStructure newBuilding = this.buildingsMap.get(buildingId);
            newBuilding.setFloorsCount(newBuilding.getFloorsCount() + 1);
        }
    }

    public int countFloors(int buildingId) {
        int floorsCount = 0;

        if(this.buildingsMap.containsKey(buildingId)){
            floorsCount = this.buildingsMap.get(buildingId).getFloorsCount();
        }
        return floorsCount;
    }

    public String printStatus(int buildingId) {

        String buildingStatus = "";

        if(this.buildingsMap.containsKey(buildingId)){
            BuildingStructure building = this.buildingsMap.get(buildingId);
            buildingStatus = building.getBuildingType() +": "+building.getFloorsCount()+" floors";
        }
        return buildingStatus;
    }

    public static void main(String[] args) {

        BuildingSystem sys = new BuildingSystem();

        sys.createHospital(1);
        sys.buildFloor(1);
        sys.buildFloor(1);
        sys.buildFloor(1);
        System.out.println(sys.countFloors(1));
        System.out.println(sys.printStatus(1));
        System.out.println();

        sys = new BuildingSystem();

        sys.createHospital(1);
        sys.createSchool(2);
        sys.buildFloor(1);
        sys.buildFloor(2);
        sys.buildFloor(2);
        sys.buildFloor(2);
        System.out.println(sys.countFloors(1));
        System.out.println(sys.countFloors(2));
        System.out.println(sys.printStatus(1));
        System.out.println(sys.printStatus(2));

        System.out.println();

    }
}
