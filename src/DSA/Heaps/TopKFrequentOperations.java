package Heaps;

import java.util.*;

public class TopKFrequentOperations {
    public static void main(String[] args) {
        String[][] operationsList = {
                {
                        "login","logout","login","view","login","logout","view","delete"
                },
                {
                        "a","b","c","a","b","a"
                }
        };

        int[] k = {2,3};

        for(int i = 0 ; i < operationsList.length; i++){
            System.out.println(topKFrequentOperations(operationsList[i], k[i]));
        }
    }

    public static List<String> topKFrequentOperations(String[] operations, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> operationsFrequencyMap = new TreeMap<>();
        PriorityQueue<String[]> operationCountMaxHeap = new PriorityQueue<>((a, b)->{
            int wordFrequencyComparator = Integer.compare(Integer.parseInt(b[1]),Integer.parseInt(a[1]));
            if(wordFrequencyComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return wordFrequencyComparator;
        });

        for(String operation : operations){
            if(operationsFrequencyMap.containsKey(operation)){
                operationsFrequencyMap.put(operation, operationsFrequencyMap.get(operation) + 1);
            }else{
                operationsFrequencyMap.put(operation, 1);
            }
        }

        for(Map.Entry<String, Integer> entry : operationsFrequencyMap.entrySet()){
            operationCountMaxHeap.offer(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
        }

        while(!operationCountMaxHeap.isEmpty()){
            if(k > 0) {
                result.add(operationCountMaxHeap.poll()[0]);
            }else{
                break;
            }
            k--;
        }

        return result;
    }
}
