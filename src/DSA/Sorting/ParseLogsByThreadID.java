package Sorting;

import java.util.*;

public class ParseLogsByThreadID {

    public static void main(String[] args) {
        String[][] logsList = {
                {
                        "1 thread1 start process",
                        "3 thread2 init module",
                        "2 thread1 loading config",
                        "5 thread2 module ready",
                        "4 thread1 process complete"
                },
                {
                        "10 alpha hello world",
                        "5 beta foo bar",
                        "1 alpha starting up",
                        "8 beta checking status"
                }
        };

        for(int i = 0; i < logsList.length; i++){
            for(List<Object> logFromASpecificThread : parseLogs(logsList[i])){
                System.out.println(logFromASpecificThread);
            }
        }
    }

    public static List<List<Object>> parseLogs(String[] logs) {
        Map<String, List<Object>> threadLogsMap = new TreeMap<>();
        List<List<Object>> result = new ArrayList<>();

        for(String log : logs){
            String[] logElements = log.split(" ");
            String threadId = logElements[1];
            List<Object> logString;

            if(threadLogsMap.containsKey(threadId)){
                logString = threadLogsMap.get(threadId);
            }else{
                logString = new ArrayList<>();
            }

            logString.add(log);
            Collections.sort(logString, (a,b) ->
                    Integer.compare(Integer.parseInt(String.valueOf(a).split(" ")[0]), Integer.parseInt(String.valueOf(b).split(" ")[0]))
            );
            threadLogsMap.put(threadId, logString);
        }

        for(Map.Entry<String, List<Object>> entry : threadLogsMap.entrySet()){
            List<Object> logObjectList = new ArrayList<>();
            logObjectList.add(entry.getKey());
            logObjectList.add(entry.getValue());
            result.add(logObjectList);
        }
        return result;
    }

}
