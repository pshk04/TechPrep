package LLD.AlertingEngine;

import java.util.HashMap;
import java.util.Map;

public class Rule {

    private String id;
    private String type;
    private String metric;
    private String configString;
    private Map<String, Double> alertMetricsMap;

    public Rule(String type, String metric, String id, String configString) {
        this.type = type;
        this.metric = metric;
        this.id = id;
        this.configString = configString;
        this.alertMetricsMap = new HashMap<>();
        this.alertMetricsMap.put("threshold", -1.0);
        this.alertMetricsMap.put("change", -1.0);
        this.alertMetricsMap.put("anamoly", -1.0);
    }

    public Map<String, Double> getAlertMetricsMap() {
        return alertMetricsMap;
    }

    public void setAlertMetricsMap(Map<String, Double> alertMetricsMap) {
        this.alertMetricsMap = alertMetricsMap;
    }

    public String getConfigString() {
        return configString;
    }

    public void setConfigString(String configString) {
        this.configString = configString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
