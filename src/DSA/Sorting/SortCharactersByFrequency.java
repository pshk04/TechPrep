package Sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {

    public static void main(String[] args) {
        String[] words = {
                "tree",
                "cccaaa"
        };

        for(String word : words){
            System.out.println("Descending order of chars according to their frequency: "+frequencySort(word));
        }
    }

    public static String frequencySort(String s) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> charCountMap = new HashMap<>();
        PriorityQueue<int[]> charCountMaxHeap = new PriorityQueue<>((a, b) -> {
            int p1 = Integer.compare(b[1], a[1]);
            if(p1 == 0){
                return Integer.compare(a[0], b[0]);
            }
            return p1;
        });

        for(char currentChar : s.toCharArray()){
            if(charCountMap.containsKey(currentChar)){
                charCountMap.put(currentChar, charCountMap.get(currentChar) + 1);
            }else{
                charCountMap.put(currentChar, 1);
            }
        }

        for(Map.Entry<Character, Integer> entry : charCountMap.entrySet()){
            charCountMaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        while(!charCountMaxHeap.isEmpty()){
            int[] charCount = charCountMaxHeap.poll();
            for(int i = 0; i < charCount[1]; i++){
                result.append((char)charCount[0]);
            }

        }

        return result.toString();
    }
}
