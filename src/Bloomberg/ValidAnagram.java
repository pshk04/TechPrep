package Bloomberg;

import java.util.Arrays;

public class ValidAnagram {

    public static void main(String[] args) {
        String s = "god", t = "dogs";

        System.out.println("Are both strings anagram?: "+isAnagram(s,t));
    }

    public static boolean isAnagram(String s, String t) {

        int[] sCharsArray = new int[26];
        int[] tCharsArray = new int[26];

        for(int i = 0 ; i < s.length(); i++){
            sCharsArray[s.charAt(i) - 'a']++;
        }

        for(int i = 0 ; i < t.length(); i++){
            tCharsArray[t.charAt(i) - 'a']++;
        }

        return Arrays.equals(sCharsArray, tCharsArray);
    }

}
