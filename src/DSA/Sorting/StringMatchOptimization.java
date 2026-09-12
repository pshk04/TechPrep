package Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMatchOptimization {

    public static void main(String[] args) {
        String[][] words1Array = {
                {
                        "apple",
                        "banana",
                        "cherry",
                        "date"
                },
                {
                        "hello","world","hello","foo"
                }
        };
        String[][] words2Array = {
                {
                        "banana",
                        "date",
                        "fig",
                        "grape"
                },
                {
                        "foo","bar","hello","hello"
                }
        };

        for(int i = 0 ; i < words1Array.length; i++){
            System.out.println("Common Words are: "+findMatches(words1Array[i], words2Array[i]));
        }
    }

    public static List<String> findMatches(String[] words1, String[] words2) {
        Map<String, Integer> wordCountForArray1Map = new HashMap<>();
        Map<String, Integer> wordCountForArray2Map = new HashMap<>();
        List<String> commonWordsList = new ArrayList<>();

        for(String word : words1){
            if(wordCountForArray1Map.containsKey(word)){
                wordCountForArray1Map.put(word, wordCountForArray1Map.get(word) + 1);
            }else{
                wordCountForArray1Map.put(word, 1);
            }
        }

        for(String word : words2){
            if(wordCountForArray2Map.containsKey(word)){
                wordCountForArray2Map.put(word, wordCountForArray2Map.get(word) + 1);
            }else{
                wordCountForArray2Map.put(word, 1);
            }
        }

        for(Map.Entry<String, Integer> entry : wordCountForArray1Map.entrySet()){
            String word = entry.getKey();
            if(wordCountForArray2Map.containsKey(word)){
                commonWordsList.add(word);
            }
        }

        return commonWordsList;

    }
}
