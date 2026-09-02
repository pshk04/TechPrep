package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LogStringProcessor {

    public static void main(String[] args) {
        String[][] logs = {
                {
                        "dig1 8 1 5 1",
                        "let1 art can",
                        "dig2 3 6",
                        "let2 own kit dig",
                        "let3 art zero"
                },
                {
                        "a1 9 2 3 1",
                        "g1 act car",
                        "zo4 4 7",
                        "ab1 off key dog",
                        "a8 act zoo"
                }
        };

        for(int i = 0 ; i < logs.length; i++){
            System.out.println("Processed logs are in the following order:"+ Arrays.toString(reorderLogFiles(logs[i])));
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        List<String> digitLogsList = new ArrayList<>();
        PriorityQueue<String[]> minHeapForLetterLogs = new PriorityQueue<>((a, b) ->{
           int priorityCompare =  a[1].compareTo(b[1]);
           if(priorityCompare == 0){
               return a[0].compareTo(b[0]);
           }
           return priorityCompare;
        });
        String logId = "", logContent = "";
        String[] parsedLogs = new String[logs.length];
        int index = 0;

        for(String log : logs){
            logId = log.split(" ")[0];
            logContent = log.substring(log.indexOf(" ") + 1);

            if(Character.isDigit(logContent.charAt(0))){
                digitLogsList.add(log);
            }else{
                minHeapForLetterLogs.offer(new String[]{logId, logContent});
            }
        }

        while(!minHeapForLetterLogs.isEmpty()){
            String[] letterLog = minHeapForLetterLogs.poll();
            parsedLogs[index] = String.join(" ", letterLog);
            index++;
        }

        for(String digitLog : digitLogsList){
            parsedLogs[index] = digitLog;
            index++;
        }
        return parsedLogs;

    }

}
