package TechPrep100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MostCommonWord {

    public static void main(String[] args) {
        String[] paragraphs = {
                "Bob hit a ball, the hit BALL flew far after it was hit.",
                "a, a, a, a, b,b,b,c, c"
        };
        String[][] banned = {
                {"hit"},
                {"a"}
        };

        for(int i = 0 ; i < paragraphs.length; i++) {
            System.out.println("The most frequently occuring word in the paragraph: " + mostCommonWord(paragraphs[i], banned[i]));
        }
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();

        System.out.println("Para: "+paragraph);

        Map<String, Integer> wordCountMap = new HashMap<>();

        PriorityQueue<Word> maxHeapForCommonWords = new PriorityQueue<>(
                (a, b) -> {
                    int wordCount = Integer.compare(b.getCount(), a.getCount());

                    if (wordCount == 0) {
                        return a.getWord().compareTo(b.getWord());
                    }
                    return wordCount;
                });

        String[] wordsArray = paragraph.split("[\\p{Punct}\\s]+");

        for (String word : wordsArray) {
            word = word.replaceAll("[^a-zA-Z0-9 ]", "").trim();
            System.out.println("Word: "+word);

            if(!Arrays.asList(banned).contains(word)) {
                if (wordCountMap.containsKey(word)) {
                    wordCountMap.put(word, wordCountMap.get(word) + 1);
                } else {
                    wordCountMap.put(word, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            Word newWord = new Word(entry.getValue(), entry.getKey());
            maxHeapForCommonWords.offer(newWord);
        }

        return maxHeapForCommonWords.poll().getWord();
    }

}
