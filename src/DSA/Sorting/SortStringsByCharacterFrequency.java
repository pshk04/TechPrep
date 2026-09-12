package Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortStringsByCharacterFrequency {

    public static void main(String[] args) {
        String[][] wordsArray = {
                {"banana","apple","kiwi","fig","watermelon"},
                {"cat","dog","bird","ox","elephant"}
        };

        for(String[] words : wordsArray){
            System.out.println("Sorted by length: "+"\n"+Arrays.toString(sortByCharFrequency(words)));
        }
    }

    public static String[] sortByCharFrequency(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        return words;
    }
}
