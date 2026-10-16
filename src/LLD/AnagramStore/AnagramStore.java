package LLD.AnagramStore;

import java.util.*;

public class AnagramStore {

    private Map<String, List<String>> anagramsMap;

    public AnagramStore() {
        this.anagramsMap = new HashMap<>();
    }

    public void addWord(String word) {
        List<String> anagramsList = null;

        String anagramString = findAnagramString(word);

        if(this.anagramsMap.containsKey(anagramString)){
            anagramsList = this.anagramsMap.get(anagramString);
        }else{
            anagramsList = new ArrayList<>();
        }
        anagramsList.add(word);
        Collections.sort(anagramsList);
        this.anagramsMap.put(anagramString, anagramsList);
    }

    public List<String> getAnagrams(String query) {
        List<String> anagramsList = new ArrayList<>();

        String anagramString = findAnagramString(query);
        if(this.anagramsMap.containsKey(anagramString)){
            anagramsList = this.anagramsMap.get(anagramString);
        }

        return anagramsList;
    }

    public static String findAnagramString(String word){
        int[] wordArray = new int[26];
        Arrays.fill(wordArray,0);
        char[] charsArray = word.toCharArray();

        for(char currentChar : charsArray){
            wordArray[currentChar - 'a']++;
        }

        return Arrays.toString(wordArray);
    }

    public static void main(String[] args) {

        AnagramStore store = new AnagramStore();

        store.addWord("eat");
        store.addWord("tea");
        store.addWord("tan");
        store.addWord("ate");
        System.out.println(store.getAnagrams("eat"));
        System.out.println(store.getAnagrams("nat"));
        System.out.println(store.getAnagrams("bat"));

        System.out.println();

        store = new AnagramStore();

        System.out.println(store.getAnagrams("abc"));
        store.addWord("abc");
        System.out.println(store.getAnagrams("cab"));
        store.addWord("bca");
        System.out.println(store.getAnagrams("bac"));

        System.out.println();

        store = new AnagramStore();

        System.out.println(store.getAnagrams("abc"));
        store.addWord("abc");
        System.out.println(store.getAnagrams("cab"));
        store.addWord("bca");
        System.out.println(store.getAnagrams("bac"));

    }
}
