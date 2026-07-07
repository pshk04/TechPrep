package TechPrep100;

import java.util.*;

public class GroupingAnagrams {
    public static void main(String[] args) {
        String[] words = {"race", "care", "acre", "hello", "world", "dworl"};

        for(List<String> anagramGroup : groupAnagrams(words)){
            System.out.println(anagramGroup);
        }
    }

    public static List<List<String>> groupAnagrams(String[] words){
        Map<String, List<String>> anagramsMap = new HashMap<>();
        List<String> anagramsList;

        for(String word : words){
            int[] chars = new int[26];
            for(int i = 0 ; i < word.length(); i++){
                chars[word.charAt(i) - 'a']++;
            }

            if(anagramsMap.containsKey(Arrays.toString(chars))){
                anagramsList = anagramsMap.get(Arrays.toString(chars));
            }else{
                anagramsList = new ArrayList<>();
            }
            anagramsList.add(word);
            anagramsMap.put(Arrays.toString(chars), anagramsList);
        }

        System.out.println("Size: "+anagramsMap.size());

        return new ArrayList<>(anagramsMap.values());
    }
}
