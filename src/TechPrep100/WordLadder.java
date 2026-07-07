package TechPrep100;

import java.util.*;

public class WordLadder {
    // Word Ladder - Find Shortest Transformation Sequence

    public static void main(String[] args) {
        String[] beginWords = {"hit"};
        String[] endWords = {"cog"};
        String[][] wordsArray = {
                {"hot","dot","dog","lot","log","cog"}
        };
        List<String> wordList;

        for(int i = 0 ; i < beginWords.length; i++) {
            wordList = new ArrayList<>(Arrays.asList(wordsArray[i]));
            System.out.println("The number of one letter transformations required: " + ladderLength(beginWords[i], endWords[i], wordList));
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList){
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in wordSet, transformation is impossible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Use Queue for BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginWord, 1));

        // Keep track of visited words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Pair<String, Integer> current = queue.poll();
            String word = current.getKey();
            int length = current.getValue();
            System.out.println(word+" : "+length);
            if (word.equals(endWord)) {
                return length;
            }

            // Try changing each character of the word
            char[] wordArray = word.toCharArray();
            for (int i = 0; i < wordArray.length; i++) {
                char original = wordArray[i];

                // Try all possible characters
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);

                    if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                        visited.add(newWord);
                        queue.offer(new Pair<>(newWord, length + 1));
                        System.out.println("Pushing new word "+ newWord +" with "+(length + 1));
                    }
                }

                // Restore the original character
                wordArray[i] = original;
            }
        }
        return 0;
    }
}
