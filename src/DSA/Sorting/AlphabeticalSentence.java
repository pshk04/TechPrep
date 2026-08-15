package Sorting;

import java.util.*;

public class AlphabeticalSentence {

    public static void main(String[] args) {
        String[] sentences = {
                "the quick brown fox jumps over the lazy dog",
                "Banana apple Cherry"
        };

        for(String sentence : sentences){
            System.out.println(sortTheSentence(sentence));
            System.out.println(alphabeticalSentence(sentence));
        }
    }

    /**
     * Time Complexity: O(n + n log n) where n is number of words and (n log n) for sorting => O(n log n)
     * @param sentence
     * @return
     */
    public static String alphabeticalSentence(String sentence) {
        String[] words = sentence.split(" ");
        Arrays.sort(words, (a,b)->(a.toLowerCase().compareTo(b.toLowerCase())));
        StringBuilder result = new StringBuilder();

        for(String word : words){
            result.append(word).append(" ");
        }

        return result.toString().trim();
    }

    /**
     * Time Complexity: O(n + m) // n is number of words and m is number of unique words => O(n)
     * Space Complexity: O(n + m) // n is for String array and m for HashMap => O(n)
     * @param sentence
     * @return
     */
    public static String sortTheSentence(String sentence){
        String[] words = sentence.split("\s");
        String lowerCaseWord = "";
        TreeMap<String, List<String>> wordsMap = new TreeMap<>();
        List<String> wordsList;
        StringBuilder result = new StringBuilder();

        for(String word : words){
            lowerCaseWord = word.toLowerCase();
            if(wordsMap.containsKey(lowerCaseWord)){
                wordsList = wordsMap.get(lowerCaseWord);
            }else{
                wordsList = new ArrayList<>();
            }
            wordsList.add(word);
            wordsMap.put(lowerCaseWord, wordsList);
        }

        for(Map.Entry<String, List<String>> entry : wordsMap.entrySet()){
            wordsList = entry.getValue();
            for(String currentWord : wordsList){
                result.append(currentWord).append(" ");
            }
        }
        return result.toString().trim();
    }
}
