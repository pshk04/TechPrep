package LLD.AlertingEngine;

import java.util.*;

public class AlertingEngine {

    private Map<String, Rule> rulesMap;
    private Map<String, Channel> channelsMap;
    private Map<String, List<String>> ruleChannelsMap;
    private PriorityQueue<DataMetric> dataMetricMaxheap;
    private List<String> alertsList;
    private Map<String, List<DataMetric>> dataMetricsMap;

    public AlertingEngine() {

        this.rulesMap = new LinkedHashMap<>();
        this.channelsMap = new HashMap<>();
        this.ruleChannelsMap = new HashMap<>();
        this.dataMetricMaxheap = new PriorityQueue<>((a,b) ->{
            int ingestionTimestampComparator = Integer.compare(b.getTimestamp(), a.getTimestamp());
            if(ingestionTimestampComparator == 0){
                return Double.compare(b.getValue(), a.getValue());
            }
            return ingestionTimestampComparator;
        });
        this.alertsList = new ArrayList<>();
        this.dataMetricsMap = new HashMap<>();
    }

    public boolean add_rule(String rule_id, String rule_type, String metric, String config) {

        if(!this.rulesMap.containsKey(rule_id)){
            Rule newRule = new Rule(rule_type, metric, rule_id, config);
            String[] configValues = extractConfigValue(config);
            Map<String, Double> alertMetricsMap = newRule.getAlertMetricsMap();
            alertMetricsMap.put(configValues[0], Double.valueOf(configValues[1]));
            this.rulesMap.put(rule_id, newRule);
            return true;
        }
        return false;
    }

    public static String[] extractConfigValue(String config){
        String ruleType = "";

        if(config.indexOf("change") >= 0){
            config = config.replace("change", "").replace("\"","").replace(":","");
            config = config.replace("{","").replace("}","");
            ruleType = "change";
        }else if(config.indexOf("threshold") >= 0){
            config = config.replace("threshold", "").replace("\"","").replace(":","");
            config = config.replace("{","").replace("}","");
            ruleType = "threshold";
        }else if(config.indexOf("std_devs") >= 0){
            config = config.replace("std_devs", "").replace("\"","").replace(":","");
            config = config.replace("{","").replace("}","");
            ruleType = "anamoly";
        }
        return new String[]{ruleType, config};
    }

    public boolean remove_rule(String rule_id) {
        System.out.println("Removing rule: "+rule_id);

        if(this.rulesMap.containsKey(rule_id)){
            this.rulesMap.remove(rule_id);
            return true;
        }
        return false;
    }

    public boolean add_channel(String channel_id, String channel_type) {
        if(!this.channelsMap.containsKey(channel_id)){
            Channel channel = new Channel(channel_id, channel_type);
            this.channelsMap.put(channel_id, channel);
            return true;
        }
        return false;
    }

    public boolean attach_channel(String rule_id, String channel_id) {

        if(this.channelsMap.containsKey(channel_id) && this.rulesMap.containsKey(rule_id)){
            List<String> channelsList = null;

            if(!this.ruleChannelsMap.containsKey(rule_id)) {
                channelsList = new ArrayList<>();
                channelsList.add(channel_id);
                this.ruleChannelsMap.put(rule_id, channelsList);
                return true;
            }else if(this.ruleChannelsMap.containsKey(rule_id) && !this.ruleChannelsMap.get(rule_id).contains(channel_id)) {
                channelsList = this.ruleChannelsMap.get(rule_id);
                channelsList.add(channel_id);
                this.ruleChannelsMap.put(rule_id, channelsList);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public void ingest(String metric, int timestamp, double value) {

        DataMetric dataMetric = new DataMetric(timestamp, metric, value);
        this.dataMetricMaxheap.offer(dataMetric);
        List<DataMetric> dataMetricList = null;

        if(this.dataMetricsMap.containsKey(metric)){
            dataMetricList = this.dataMetricsMap.get(metric);
        }else{
            dataMetricList = new ArrayList<>();
        }
        dataMetricList.add(dataMetric);
        this.dataMetricsMap.put(metric, dataMetricList);
    }

    public List<String> evaluate() {
        String alertString = "", channelType = "";
        double thresholdValueFromData = 0.0, thresholdValueFromRule = 0.0, changeValueFromRule = 0.0, changeValueFromData = 0.0;
        double anamolyValueFromData = 0.0, anamolyValueFromRule = 0.0;
        DataMetric dataMetric = null;
        List<String> channelsList = null;
        boolean isDataPointDeviatedFromStd;
        List<String> alertsListForActiveRules = new ArrayList<>();

        for(Map.Entry<String, Rule> entry : this.rulesMap.entrySet()){
            Rule currentRule = entry.getValue();
            thresholdValueFromRule = currentRule.getAlertMetricsMap().get("threshold");
            changeValueFromRule = currentRule.getAlertMetricsMap().get("change");
            anamolyValueFromRule = currentRule.getAlertMetricsMap().get("anamoly");

            if(thresholdValueFromRule != -1 && !this.dataMetricMaxheap.isEmpty()){
                dataMetric = this.dataMetricMaxheap.peek();
                thresholdValueFromData = dataMetric.getValue();
                if(thresholdValueFromData > thresholdValueFromRule){
                    channelsList = this.ruleChannelsMap.get(currentRule.getId());
                    if(channelsList != null && channelsList.size() > 0) {
                        for (String channelId : channelsList) {
                            if (this.channelsMap.containsKey(channelId)) {
                                channelType = this.channelsMap.get(channelId).getType();
                            } else {
                                channelType = "NO_CHANNEL";
                            }
                            alertString = currentRule.getId() + ":" + channelType;
                            this.alertsList.add(alertString);
                            alertsListForActiveRules.add(alertString);
                        }
                    }else{
                        channelType = "NO_CHANNEL";
                        alertString = currentRule.getId() + ":" + channelType;
                        this.alertsList.add(alertString);
                        alertsListForActiveRules.add(alertString);
                    }
                }
            }else  if(changeValueFromRule != -1 && !this.dataMetricMaxheap.isEmpty()){
                int size = 2;
                List<DataMetric> last2DataPointsList = new ArrayList<>();
                DataMetric dataMetricObj = null;

                while(!this.dataMetricMaxheap.isEmpty() && size > 0){
                    dataMetricObj = this.dataMetricMaxheap.poll();
                    last2DataPointsList.add(dataMetricObj);
                    size--;
                }

                for(DataMetric dataMetric1 : last2DataPointsList){
                    if(size == 0){
                        changeValueFromData = dataMetric1.getValue();
                        size++;
                    }else{
                        changeValueFromData -= dataMetric1.getValue();
                    }
                    this.dataMetricMaxheap.offer(dataMetric1);
                }

                if(changeValueFromData > changeValueFromRule){
                    channelsList = this.ruleChannelsMap.get(currentRule.getId());
                    if(channelsList != null && channelsList.size() > 0) {
                        for (String channelId : channelsList) {
                            if (this.channelsMap.containsKey(channelId)) {
                                channelType = this.channelsMap.get(channelId).getType();
                            } else {
                                channelType = "NO_CHANNEL";
                            }
                            alertString = currentRule.getId() + ":" + channelType;
                            this.alertsList.add(alertString);
                            alertsListForActiveRules.add(alertString);
                        }
                    }else{
                        channelType = "NO_CHANNEL";
                        alertString = currentRule.getId() + ":" + channelType;
                        this.alertsList.add(alertString);
                        alertsListForActiveRules.add(alertString);
                    }
                }
            }else  if(anamolyValueFromRule != -1 && !this.dataMetricMaxheap.isEmpty() && this.dataMetricMaxheap.size() >= 2){
                dataMetric = this.dataMetricMaxheap.peek();
                anamolyValueFromData = dataMetric.getValue(); // 90
                isDataPointDeviatedFromStd = isDataPointDeviated(anamolyValueFromData,anamolyValueFromRule,this.dataMetricsMap, dataMetric.getType());

                if(isDataPointDeviatedFromStd){
                    channelsList = this.ruleChannelsMap.get(currentRule.getId());
                    if(channelsList != null && channelsList.size() > 0) {
                        for (String channelId : channelsList) {
                            if (this.channelsMap.containsKey(channelId)) {
                                channelType = this.channelsMap.get(channelId).getType();
                            } else {
                                channelType = "NO_CHANNEL";
                            }
                            alertString = currentRule.getId() + ":" + channelType;

                            this.alertsList.add(alertString);
                            alertsListForActiveRules.add(alertString);
                        }
                    }else{
                        channelType = "NO_CHANNEL";
                        alertString = currentRule.getId() + ":" + channelType;
                        this.alertsList.add(alertString);
                        alertsListForActiveRules.add(alertString);
                    }
                }
            }
        }

        return alertsListForActiveRules;
    }

    public static boolean isDataPointDeviated(double latestAnamolyDataPoint,
                                           double standardDeviationFromRule,
                                           Map<String, List<DataMetric>> dataMetricsMap,
                                           String metricType){

        double currentMean = 0.0, meanDifference = 0.0, standardDeviation = 0.0, dataMetricValue = 0.0, standardDeviationThreshold = 0.0;
        int dataMetricsSize = 0;
        List<Double> dataPointValuesList = new ArrayList<>();

        for(Map.Entry<String, List<DataMetric>> entry : dataMetricsMap.entrySet()){
            if(entry.getKey().equals(metricType)){
                List<DataMetric> dataMetricList = entry.getValue();
                for(DataMetric dataMetric : dataMetricList) {
                    dataMetricValue = dataMetric.getValue();
                    dataPointValuesList.add(dataMetricValue);
                    currentMean += dataMetricValue;
                    dataMetricsSize++;
                }
            }
        }

        currentMean /= dataMetricsSize; // 120.4
        meanDifference = (double) Math.abs(latestAnamolyDataPoint - currentMean); // 79.6
        standardDeviation = computeStandardDeviation(dataPointValuesList, currentMean);
        standardDeviationThreshold = standardDeviation * standardDeviationFromRule;

        if(meanDifference > standardDeviationThreshold){
            return true;
        }
        return false;
    }

    public static double computeStandardDeviation(List<Double> valuesList, double mean){
        double variance = 0.0, standardDeviation = 0.0;

        for(double value : valuesList){
            variance += Math.pow(Math.abs(value - mean), 2);
        }
        variance /= valuesList.size();
        standardDeviation = Math.sqrt(variance);

        return standardDeviation;
    }

    public List<String> get_alerts() {
        return this.alertsList;
    }

    public int get_rule_count() {
        return this.rulesMap.size();
    }

    public int get_metric_data_count(String metric) {
        if(this.dataMetricsMap.containsKey(metric)) {
            return this.dataMetricsMap.get(metric).size();
        }
        return 0;
    }

    public static void main(String[] args) {

        AlertingEngine engine = new AlertingEngine();

        System.out.println(engine.add_channel("ch1", "Slack"));
        System.out.println(engine.add_channel("ch2", "Email"));
        System.out.println(engine.add_rule("r1", "Threshold", "cpu", "{\"threshold\": 90.0}"));
        System.out.println(engine.attach_channel("r1", "ch1"));
        System.out.println(engine.attach_channel("r1", "ch2"));
        engine.ingest("cpu", 1, 85);
        engine.ingest("cpu", 2, 92);
        engine.ingest("cpu", 3, 95);
        System.out.println(engine.evaluate());
        System.out.println(engine.get_alerts());

        System.out.println();

        engine = new AlertingEngine();

        System.out.println(engine.add_rule("r1", "Change", "mem", "{\"change\": 10.0}"));
        System.out.println(engine.add_rule("r2", "Threshold", "mem", "{\"threshold\": 80.0}"));
        System.out.println(engine.add_channel("ch1", "PagerDuty"));
        System.out.println(engine.attach_channel("r1", "ch1"));
        engine.ingest("mem", 1, 50);
        engine.ingest("mem", 2, 55);
        System.out.println(engine.evaluate());
        engine.ingest("mem", 3, 70);
        engine.ingest("mem", 4, 85);
        System.out.println(engine.evaluate());
        System.out.println(engine.get_alerts());
        System.out.println(engine.get_rule_count());

        System.out.println();

        engine = new AlertingEngine();

        System.out.println(engine.add_rule("r1", "Threshold", "cpu", "{\"threshold\": 70.0}"));
        System.out.println(engine.add_rule("r2", "Change", "cpu", "{\"change\": 8.0}"));
        System.out.println(engine.add_rule("r3", "Anomaly", "cpu", "{\"std_devs\": 1.5}"));
        System.out.println(engine.add_channel("ch1", "Slack"));
        System.out.println(engine.add_channel("ch2", "PagerDuty"));
        System.out.println(engine.attach_channel("r1", "ch1"));
        System.out.println(engine.attach_channel("r2", "ch2"));
        System.out.println(engine.attach_channel("r3", "ch1"));
        System.out.println(engine.attach_channel("r3", "ch2"));
        engine.ingest("cpu", 1, 50);
        engine.ingest("cpu", 2, 52);
        engine.ingest("cpu", 3, 51);
        engine.ingest("cpu", 4, 53);
        engine.ingest("cpu", 5, 90);
        System.out.println(engine.evaluate()); // Correct
        System.out.println(engine.get_alerts());
        System.out.println(engine.remove_rule("r1"));
        System.out.println(engine.evaluate());
        System.out.println(engine.get_alerts());
        System.out.println(engine.get_rule_count());

        System.out.println();

        engine = new AlertingEngine();

        System.out.println(engine.add_rule("r1", "Anomaly", "latency", "{\"std_devs\": 2.0}"));
        System.out.println(engine.add_channel("ch1", "Slack"));
        System.out.println(engine.attach_channel("r1", "ch1"));
        engine.ingest("latency", 1, 100);
        engine.ingest("latency", 2, 102);
        System.out.println(engine.evaluate());
        engine.ingest("latency", 3, 101);
        engine.ingest("latency", 4, 99);
        engine.ingest("latency", 5, 200);
        System.out.println(engine.evaluate());
        System.out.println(engine.get_alerts());

    }
}
