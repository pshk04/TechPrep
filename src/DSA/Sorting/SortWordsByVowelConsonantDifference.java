package Sorting;

import java.util.Arrays;

public class SortWordsByVowelConsonantDifference {

    public static void main(String[] args) {
        String[][] wordsArray = {
                {
                        "hello",
                        "ai",
                        "sky",
                        "ocean",
                        "rhythm"
                },
                {
                        "apple",
                        "banana",
                        "cherry",
                        "date"
                }
        };

        for(String[] words : wordsArray){
            System.out.println("Sorted Ascending Vowel - Consonants diff words order : "+ Arrays.toString(sortWordsByVowelConsonantDifference(words)));
        }

    }

    public static String[] sortWordsByVowelConsonantDifference(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(countVowelConsonantsDifference(a), countVowelConsonantsDifference(b)));
        return words;
    }

    public static int countVowelConsonantsDifference(String word){
        int vowelCount = 0, consonantsCount = 0;

        for(int i = 0 ; i < word.length(); i++){
            if("aeiou".indexOf(word.toLowerCase().charAt(i)) >= 0){
                vowelCount++;
            }else{
                consonantsCount++;
            }
        }
        return vowelCount - consonantsCount;
    }
}
