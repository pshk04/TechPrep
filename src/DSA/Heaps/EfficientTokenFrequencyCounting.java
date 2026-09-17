package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class EfficientTokenFrequencyCounting {

    public static void main(String[] args) {
        String[][] tokens = {
                {"apple","banana","apple","cherry","banana","apple"},
                {"a","b","c","a","b","a"}
        };

        int[] k = {2,1};

        for(int i = 0 ; i < tokens.length; i++){
            System.out.println(+k[i]+" most frequenct tokens: "+ Arrays.toString(topKFrequentTokens(tokens[i], k[i])));
        }
    }

    public static String[] topKFrequentTokens(String[] tokens, int k) {
        String[] kFrequentTokens = new String[k];
        Map<String, Integer> tokenCountMap = new TreeMap<>();
        PriorityQueue<String[]> tokensContMaxHeap = new PriorityQueue<>((a,b) -> {
            int countComparator = Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1]));
            if(countComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return countComparator;
        });

        for(int i = 0 ; i < tokens.length; i++){
            String token = tokens[i];
            if(tokenCountMap.containsKey(token)){
                tokenCountMap.put(token, tokenCountMap.get(token) + 1);
            }else{
                tokenCountMap.put(token, 1);
            }
        }

        for(Map.Entry<String, Integer> tokenCountEntry : tokenCountMap.entrySet()){
            String token = tokenCountEntry.getKey();
            String tokenCount = String.valueOf(tokenCountEntry.getValue());
            tokensContMaxHeap.offer(new String[]{token, tokenCount});
        }

        int index = 0;

        while(index < k){
            kFrequentTokens[index] = tokensContMaxHeap.poll()[0];
            index++;
        }
        return kFrequentTokens;
    }
}
