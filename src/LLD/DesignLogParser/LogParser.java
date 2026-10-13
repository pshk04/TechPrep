package LLD.DesignLogParser;

import java.util.*;

public class LogParser {
    private Map<Integer, Map<String, List<String>>> logsCounterMap;
    private PriorityQueue<String[]> logsMinHeap;

    public LogParser() {
        this.logsCounterMap = new TreeMap<>();
        this.logsMinHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0]));
        });
    }

    public void addLog(String log) {
        Map<String, List<String>> logsLevelMap;
        List<String> logsList;
        String[] logArray = log.split(":");
        int timestamp = Integer.parseInt(logArray[0]);
        String logLevel = logArray[1];

        if(!this.logsCounterMap.containsKey(timestamp)){
            logsLevelMap = new HashMap<>();
            logsList = new ArrayList<>();
        }else{
            logsLevelMap = this.logsCounterMap.get(timestamp);
            if(logsLevelMap.containsKey(logLevel)){
                logsList = logsLevelMap.get(logLevel);
            }else{
                logsList = new ArrayList<>();
            }
        }
        logsList.add(log);
        logsLevelMap.put(logLevel, logsList);
        this.logsCounterMap.put(timestamp, logsLevelMap);
        this.logsMinHeap.offer(new String[]{logArray[0], log});
    }

    public List<String> getLogsByLevel(String level) {
        List<String> logsByLevelList = new ArrayList<>();

        for(Map.Entry<Integer, Map<String, List<String>>> entry : this.logsCounterMap.entrySet()){
            Map<String, List<String>> logsMap = entry.getValue();
            if(logsMap.containsKey(level)) {
                logsByLevelList.addAll(logsMap.get(level));
            }
        }

        return logsByLevelList;
    }

    public List<String> getLogsByTimeRange(int start, int end) {
        List<String> logsByTimeRange = new ArrayList<>();
        PriorityQueue<String[]> logsMiniHeapCopy = new PriorityQueue<>(this.logsMinHeap);

        while(!logsMiniHeapCopy.isEmpty()){
            String[] log = logsMiniHeapCopy.poll();
            int timestamp = Integer.parseInt(log[0]);
            if(timestamp >= start && timestamp <= end){
                logsByTimeRange.add(log[1]);
            }
        }
        return logsByTimeRange;
    }

    public int getEventCount(String level) {
        List<String> logsByLevelList = this.getLogsByLevel(level);
        return logsByLevelList.size();
    }
}
