package LLD.AlertingEngine;

public class DataMetric {

    private int timestamp;
    private double value;
    private String type;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DataMetric(int timestamp, String type, double value) {
        this.timestamp = timestamp;
        this.type = type;
        this.value = value;
    }
}
