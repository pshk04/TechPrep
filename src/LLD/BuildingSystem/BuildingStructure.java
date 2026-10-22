package LLD.BuildingSystem;

public class BuildingStructure {

    private int id;
    private int floorsCount;
    private String buildingType;

    public BuildingStructure(int id, int floorsCount, String buildingType) {
        this.id = id;
        this.floorsCount = floorsCount;
        this.buildingType = buildingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
}
