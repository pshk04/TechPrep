package TechPrep100;

import java.util.*;

public class TopkFrequentWords {

    public static void main(String[] args) {
        String[][] words = {
                {"the","day","is","sunny","the","the","the","sunny","is","is"},
                {"i","love","techprep","i","love","coding"}
        };

        int[] k = {4,2};

        for(int i = 0 ; i < words.length; i++) {
            System.out.println("Top K frequent words: " + topKFrequent(words[i], k[i]));
        }
    }

    public static List<String> topKFrequent(String[] words, int k){
        Map<String, Integer> wordsCountMap = new HashMap<>();
        Map<Integer, List<String>> countWordsMap = new TreeMap<>(Collections.reverseOrder());
        int count = 0;
        String currentWord = "";
        List<String> wordsList;

        for(String word : words){
            if(wordsCountMap.containsKey(word)){
                wordsCountMap.put(word, wordsCountMap.get(word) + 1);
            }else{
                wordsCountMap.put(word, 1);
            }
        }

        for(Map.Entry<String, Integer> entry : wordsCountMap.entrySet()){
            currentWord = entry.getKey();
            count = entry.getValue();
            if(countWordsMap.containsKey(count)){
                wordsList = countWordsMap.get(count);
            }else{
                wordsList = new ArrayList<>();
            }
            wordsList.add(currentWord);
            Collections.sort(wordsList);
            countWordsMap.put(count, wordsList);
        }
        wordsList = new ArrayList<>();

        int i = 0;

        for(Map.Entry<Integer, List<String>> entry : countWordsMap.entrySet()){
            if(i < k) {
                wordsList.addAll(entry.getValue());
                i++;
            }else{
                break;
            }
        }
        return wordsList.subList(0, k);
    }
}
