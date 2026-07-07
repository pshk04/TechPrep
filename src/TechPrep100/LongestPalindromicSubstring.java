package TechPrep100;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {

        System.out.println("The longest palindromic substring is: "+longestPalindromicSubstring("a"));
    }

    public static String longestPalindromicSubstring(String s){

        String largestPalindrome = "", subString = "";
        int maxLength = 0;

        if(s.length() == 1){
            return s;
        }
        for(int i = 0; i < s.length() - 1; i++){
            for(int j = i + 1; j <= s.length(); j++){
                subString = s.substring(i, j);
                if(isPalindrome(subString)){
                    if(subString.length() > maxLength){
                        System.out.println("SubString: "+subString+ " " +i+ " "+j);
                        largestPalindrome = subString;
                        maxLength = subString.length();
                    }
                }
            }
        }
        return largestPalindrome;
    }

    public static boolean isPalindrome(String s){

        int i = 0 , j = s.length() - 1;

        if(i == j){
            return true;
        }

        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }
}
