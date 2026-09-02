package Sorting;

import java.util.*;

public class LogFileParsingAndFrequencyAnalysis {

    public static void main(String[] args) {
        List<List<Object>> logsList = new ArrayList<>();
        Object[][][] logObjects = {
                {
                        {1,"login"},
                        {2,"click"},
                        {3,"login"},
                        {10,"logout"},
                        {11,"click"}
                },
                {
                        {5,"error"},
                        {6,"warn"},
                        {7,"error"},
                        {8,"warn"},
                        {20,"info"}
                },
        };
        int[] windows = {2,3};

        List<Object> objectList;

        for(int j = 0; j < logObjects.length; j++) {
            objectList = new ArrayList<>();
            objectList.add(String.valueOf(logObjects[j][0]));
            objectList.add(String.valueOf(logObjects[j][1]));
            logsList.add(objectList);
        }

        for(int i = 0 ; i < windows.length; i++){
            System.out.println("The most frequent log event pair is: "+mostFrequentEventPair(logsList, windows[i]));
        }
    }

    public static List<String> mostFrequentEventPair(List<List<Object>> logs, int window) {

        Map<String, Integer> logPairCountMap = new TreeMap<>();
        List<List<String>> convertedLogs = new ArrayList<>();

        for(List<Object> logObject : logs){

            int timeStamp = Integer.parseInt(logObject.get(0)+"");
            String event = String.valueOf(logObject.get(1));
            System.out.println(timeStamp+" "+event);
//            List<String> logItem = new ArrayList<>();
//            logItem.add(timeStamp);
//            logItem.add(event);
//            convertedLogs.add(logItem);
        }

        Collections.sort(convertedLogs,
                (a, b) -> Integer.compare(Integer.parseInt(a.get(0)), Integer.parseInt(b.get(0)))
        );
        int previousLogTimeStamp = (Integer) (logs.get(0).get(0)), currentLogTimeStamp = 0, difference = 0, maxFrequency = Integer.MIN_VALUE, currentFrequency = 0;
        String previousLogEvent = (String) (logs.get(0).get(1)), currentLogEvent = "", eventsPair = "";
        List<String> eventsList;

        for(int i = 1 ; i < logs.size(); i++){
            currentLogTimeStamp = (Integer) (logs.get(i).get(0));
            difference = currentLogTimeStamp - previousLogTimeStamp;
            currentLogEvent = (String) (logs.get(i).get(1));
            eventsList = new ArrayList<>();

            if(difference <= window){
                eventsList.add(previousLogEvent);
                eventsList.add(currentLogEvent);
                Collections.sort(eventsList);
                eventsPair = String.join(" ", eventsList);

                if(!logPairCountMap.containsKey(eventsPair)){
                    logPairCountMap.put(eventsPair, 1);
                }else{
                    logPairCountMap.put(eventsPair, logPairCountMap.get(eventsPair) + 1);
                }
                previousLogEvent = currentLogEvent;
                previousLogTimeStamp = currentLogTimeStamp;
            }
        }

        for(Map.Entry<String, Integer> entry : logPairCountMap.entrySet()){
            currentFrequency = entry.getValue();
            if(currentFrequency > maxFrequency){
                maxFrequency = currentFrequency;
                eventsPair = entry.getKey();
            }
        }
        String[] resultPair = eventsPair.split(" ");
        return Arrays.asList(resultPair);
    }
}
