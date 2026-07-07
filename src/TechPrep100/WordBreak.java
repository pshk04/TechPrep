package TechPrep100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {

    public static void main(String[] args) {
        String[] s = {
//                "techprep",
//                "programming",
                "university",
//                "applepenapple"
        };
        String[][] wordDict = {
//                {"tech", "prep"},
//                {"pro", "gram", "ming", "program", "ing"},
                {"uni", "vers", "city", "univer", "ity"},
//                {"apple", "pen", "applepen"}
        };
        List<String> wordsList;

        for (int i = 0; i < s.length; i++) {
            wordsList = new ArrayList<>();
            for(String word : wordDict[i]){
                wordsList.add(word);
            }
            System.out.println("The given string " + s[i] + " can be broken in to consecutive words from the wordDict: " + wordBreak(s[i], wordsList));
        }
    }


    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for(int i = 0 ; i < s.length(); i++){
            dp[i] = false;
        }
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = 0; j < wordDict.size(); j++) {
                String word = wordDict.get(j);
                if ((i + word.length()) <= s.length() && s.substring(i, i + word.length()).equals(word)){
                    dp[i] = dp[i + word.length()];
                }
                if(dp[i]){
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }
}
