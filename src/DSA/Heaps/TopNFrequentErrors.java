package Heaps;

import java.util.*;

public class TopNFrequentErrors {
    public static void main(String[] args) {
        String[][] logsList = {
                {
                        "[ERROR] Null pointer exception",
                        "[INFO] Server started",
                        "[ERROR] Disk full",
                        "[ERROR] Null pointer exception",
                        "[WARN] Low memory",
                        "[ERROR] Disk full",
                        "[ERROR] Disk full",
                        "[ERROR] Timeout",
                        "[DEBUG] Checkpoint reached"
                },
                {
                        "[ERROR] Connection refused",
                        "[ERROR] Connection refused",
                        "[ERROR] Auth failed",
                        "[ERROR] Auth failed",
                        "[INFO] Request processed",
                        "[ERROR] Timeout"
                }
        };

        int[] n = {2,3};
        List<String> logList;
        for(int i = 0 ; i < logsList.length; i++){
            logList = new ArrayList<>();
            for(String log : logsList[i]) {
                logList.add(log);
            }
            System.out.println(topNFrequentErrors(logList, n[i]));
        }
    }

    public static List<String> topNFrequentErrors(List<String> logs, int n) {
        String errorLogCause = "";
        List<String> result = new ArrayList<>();
        Map<String, Integer> errorLogFrequencyMap = new TreeMap<>();
        PriorityQueue<String[]> errorLogFrequencyMaxHeap = new PriorityQueue<>((a, b)->{
            int wordFrequencyComparator = Integer.compare(Integer.parseInt(b[1]),Integer.parseInt(a[1]));
            if(wordFrequencyComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return wordFrequencyComparator;
        });

        for(String log : logs){
            if(log.indexOf("[ERROR]") >= 0) {
                errorLogCause = log.replace("[ERROR]", "").trim();
                if (errorLogFrequencyMap.containsKey(errorLogCause)) {
                    errorLogFrequencyMap.put(errorLogCause, errorLogFrequencyMap.get(errorLogCause) + 1);
                } else {
                    errorLogFrequencyMap.put(errorLogCause, 1);
                }
            }
        }

        for(Map.Entry<String, Integer> entry : errorLogFrequencyMap.entrySet()){
            errorLogFrequencyMaxHeap.offer(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
        }

        while(!errorLogFrequencyMaxHeap.isEmpty()){
            if(n > 0) {
                result.add(errorLogFrequencyMaxHeap.poll()[0]);
            }else{
                break;
            }
            n--;
        }

        return result;
    }
}
