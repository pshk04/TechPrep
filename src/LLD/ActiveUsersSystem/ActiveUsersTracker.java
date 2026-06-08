package LLD.ActiveUsersSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveUsersTracker {
    private int windowTime;
    private Map<Integer, List<Integer>> logMap;

    public ActiveUsersTracker(int timeWindow) {
        this.windowTime = timeWindow * 60;
        this.logMap = new HashMap<>();
    }

    public void addLog(int userId, int timestamp) {
        List<Integer> logs;
        if(this.logMap.containsKey(userId)){
            logs = this.logMap.get(userId);
        }else{
            logs = new ArrayList<>();
        }
        logs.add(timestamp);
        this.logMap.put(userId, logs);
        printLogMap();
    }

    public int activeUsers(int timestamp) {
        int initialTime = timestamp - this.windowTime;
        int uniqueUsersCount = 0, logsWithinTimeRange = 0;

        for(int userId : this.logMap.keySet()){
            List<Integer> logs = this.logMap.get(userId);
            for(int logTime : logs){
                if(logTime > initialTime && logTime <= timestamp){
                    logsWithinTimeRange++;
                }
            }
            if(logsWithinTimeRange > 0){
                uniqueUsersCount++;
                logsWithinTimeRange = 0;
            }
        }
        return uniqueUsersCount;
    }

    public List<Integer> getActiveUserIds(int timestamp) {
        int initialTime = timestamp - this.windowTime;
        int logsWithinTimeRange = 0;
        List<Integer> activeUsersIdList = new ArrayList<>();

        for(int userId : this.logMap.keySet()){

            List<Integer> logs = this.logMap.get(userId);
            for(int logTime : logs){
                if(logTime > initialTime && logTime <= timestamp){
                    logsWithinTimeRange++;
                }
            }
            if(logsWithinTimeRange > 0){
                activeUsersIdList.add(userId);
                logsWithinTimeRange = 0;
            }
        }
        return activeUsersIdList;
    }

    public void printLogMap(){
        for(Map.Entry<Integer, List<Integer>> entry : this.logMap.entrySet()){
            System.out.print(entry.getKey() +" : "+entry.getValue()+", ");
        }
        System.out.println();
    }
}
