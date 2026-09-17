package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TopKFrequentElementsInALogFile {

    public static void main(String[] args) {
        String[][] logsList = {
                {
                        "the quick brown fox",
                        "the lazy dog",
                        "the quick fox"
                },
                {
                        "apple banana apple",
                        "banana cherry",
                        "apple cherry cherry"
                }
        };

        int[] k = {2,3};

        for(int i = 0 ; i < logsList.length; i++){
            System.out.println(Arrays.toString(topKFrequentWords(logsList[i], k[i])));
        }
    }

    public static String[] topKFrequentWords(String[] logs, int k) {
        String[] result = new String[k];
        Map<String, Integer> wordsFrequencyMap = new TreeMap<>();
        PriorityQueue<String[]> wordsCountMaxHeap = new PriorityQueue<>((a,b)->{
            int wordFrequencyComparator = Integer.compare(Integer.parseInt(b[1]),Integer.parseInt(a[1]));
            if(wordFrequencyComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return wordFrequencyComparator;
        });

        for(String log : logs){
            for(String word : log.split(" ")) {
                if (wordsFrequencyMap.containsKey(word)) {
                    wordsFrequencyMap.put(word, wordsFrequencyMap.get(word) + 1);
                } else {
                    wordsFrequencyMap.put(word, 1);
                }
            }
        }

        for(Map.Entry<String, Integer> entry : wordsFrequencyMap.entrySet()){
            wordsCountMaxHeap.offer(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
        }
        int index = 0;

        while(!wordsCountMaxHeap.isEmpty()){
            if(index < k) {
                result[index] = wordsCountMaxHeap.poll()[0];
            }else{
                break;
            }
            index++;
        }

        return result;
    }
}
