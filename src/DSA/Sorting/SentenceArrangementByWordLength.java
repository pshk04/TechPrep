package Sorting;

import java.util.*;

public class SentenceArrangementByWordLength {
    public static void main(String[] args) {
        String[] sentences = {
                "The quick brown fox jumps over the lazy dog",
                "I love programming in Python!"
        };

        for(String sentence : sentences){
            System.out.println("The sorted sentence based on the length of words: "+sortOnWordLengthOptimized(sentence));
        }
    }

    public static String sortOnWordLengthOptimized(String sentence){
        String[] words = sentence.split(" ");
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        StringBuilder result = new StringBuilder();

        for(String word : words){
            result.append(word).append(" ");
        }
        return result.toString().trim();
    }


    public static String sortOnWordLength(String sentence){
        Map<Integer, List<String>> wordCountMap = new TreeMap<>();
        String[] words = sentence.split(" ");
        List<String> wordsOfSameLength;
        StringBuilder result = new StringBuilder();

        for(String word : words){
            int length = word.length();
            if(wordCountMap.containsKey(length)){
                wordsOfSameLength = wordCountMap.get(length);
            }else{
                wordsOfSameLength = new ArrayList<>();
            }
            wordsOfSameLength.add(word);
            wordCountMap.put(length, wordsOfSameLength);
        }

        for(Map.Entry<Integer, List<String>> entry : wordCountMap.entrySet()){
            wordsOfSameLength = entry.getValue();
            for(String word : wordsOfSameLength) {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }
}
