package Sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IncreasingDecreasingString {

    public static void main(String[] args) {
        String[] words = {
                "aaaabbbbcccc",
                "rat",
                "aaaabbbbcc"
        };

        for(String word : words){
            System.out.println("The increasing decreasing order of a word: "+sortStringSpaceOptimized(word));
        }
    }

    public static String sortStringSpaceOptimized(String word){
        int[] ascendingChars = new int[26];
        StringBuilder result = new StringBuilder();

        for(int i = 0 ; i < word.length(); i++){
            ascendingChars[word.charAt(i) - 'a']++;
        }
        int index = 0;

        while(result.length() < word.length()) {
            index = 0;
            while (index < 26) {
                if (ascendingChars[index] > 0) {
                    result.append((char)('a' + index));
                    ascendingChars[index]--;
                }
                index++;
            }

            index = 25;

            while (index >= 0) {
                if (ascendingChars[index] > 0) {
                    result.append((char)('a' + index));
                    ascendingChars[index]--;
                }
                index--;
            }
        }
        return result.toString();
    }


    public static String sortString(String word){
        StringBuilder result = new StringBuilder();
        Map<String, Integer> ascendingMap = new TreeMap();
        Map<String, Integer> descendingMap = new TreeMap<>((a,b) -> b.compareTo(a));
        Map<String, Integer> commonCountMap = new HashMap();
        String currentChar = "", firstChar = "";

        for(int i = 0 ; i < word.length(); i++){
            currentChar = word.charAt(i)+"";
            if(commonCountMap.containsKey(currentChar)){
                commonCountMap.put(currentChar, commonCountMap.get(currentChar) + 1);
                ascendingMap.put(currentChar, ascendingMap.get(currentChar) + 1);
                descendingMap.put(currentChar, descendingMap.get(currentChar) + 1);
            }else{
                commonCountMap.put(currentChar, 1);
                ascendingMap.put(currentChar, 1);
                descendingMap.put(currentChar, 1);
            }
        }

        while(!commonCountMap.isEmpty()){

            for(Map.Entry<String, Integer> ascendingMapEntry : ascendingMap.entrySet()){
                String smallChar = ascendingMapEntry.getKey();
                if(commonCountMap.containsKey(smallChar) && commonCountMap.get(smallChar) > 0) {
                    result.append(smallChar);
                    commonCountMap.put(smallChar, commonCountMap.get(smallChar) - 1);
                }
                if(commonCountMap.size() > 0 && commonCountMap.containsKey(smallChar) && commonCountMap.get(smallChar) == 0){
                    commonCountMap.remove(smallChar);
                }
            }

            for(Map.Entry<String, Integer> descendingMapEntry : descendingMap.entrySet()){
                String largeChar = descendingMapEntry.getKey();
                if(commonCountMap.containsKey(largeChar) && commonCountMap.get(largeChar) > 0) {
                    result.append(largeChar);
                    commonCountMap.put(largeChar, commonCountMap.get(largeChar) - 1);
                }
                if(commonCountMap.size() > 0 && commonCountMap.containsKey(largeChar) && commonCountMap.get(largeChar) == 0){
                    commonCountMap.remove(largeChar);
                }
            }
        }
        return result.toString();
    }
}
