package TechPrep100;

import java.util.PriorityQueue;

public class VerifyAnAlienDictionary {
    public static void main(String[] args) {
        String[][] wordsArray =
                {
                        {"hello", "leetcode"},
                        {"word","world","row"}
                };
        String[] order = {
                "hlabcdefgijkmnopqrstuvwxyz",
                "worldabcefghijkmnpqstuvxyz"
        };

        for(int i = 0 ; i < wordsArray.length; i++) {
            System.out.println("The sequence of words are in order: " + isAlienSorted(wordsArray[i], order[i]));
        }
    }

    public static boolean isAlienSorted(String[] words, String order) {

        int wordsIndex = 0;

        PriorityQueue<String> minHeapForWords = new PriorityQueue<>(
                (a, b) -> {
                    int index = 0;
                    int charCompare = Integer.compare(a.charAt(index), b.charAt(index));
                    while (charCompare == 0) {
                        index++;
                        if(index >= a.length() && index < b.length()){
                            return -1;
                        }else if(index >= b.length() && index < a.length()){
                            return 1;
                        }else {
                            charCompare = Integer.compare(a.charAt(index), b.charAt(index));
                        }
                    }
                    return Integer.compare(order.indexOf(a.charAt(index)), order.indexOf(b.charAt(index)));
                });


        for (String word : words) {
            minHeapForWords.offer(word);
        }

        while (!minHeapForWords.isEmpty()) {
            if (minHeapForWords.poll().equals(words[wordsIndex])) {
                wordsIndex++;
            } else {
                return false;
            }
        }
        return true;
    }

}
