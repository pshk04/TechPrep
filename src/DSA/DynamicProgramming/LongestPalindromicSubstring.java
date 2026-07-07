package DynamicProgramming;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String[] text1 = {
//                "banana",
//                "abacdfgdcaba",
                "madam",
                "prepprep"

        };

        for(int i = 0; i < text1.length; i++) {
            System.out.println("The longest palindromic substring is: " + longestPalindromicSubstring(text1[i]));
        }
    }


    public static String longestPalindromicSubstring(String s1) {

        String s2 = new StringBuilder(s1).reverse().toString();

        if (s1.length() == 0 || s1 == null || s2.length() == 0 || s2 == null) {
            return "";
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = 0;
        String maxLengthPalindromicSubstring = "";

        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if(dp[i][j] > maxLength) {
                        maxLength = Math.max(maxLength, dp[i][j]);
                        System.out.println(maxLength + " i: " + i + " j: " + j);
                        if((i - 2) >= 0) {
                            System.out.println("substring: " + s2.substring(i - 2, i + maxLength - 2));
                            maxLengthPalindromicSubstring = s2.substring(i - 2, i + maxLength - 2);
                        }
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(s1.substring(0, maxLength));
//        return (!isPalindrome(s1.substring(0, maxLength)) ? longestPalindromicSubstring(s2.substring(0, maxLength)) : s1.substring(0, maxLength));
        return (!isPalindrome(maxLengthPalindromicSubstring) ? longestPalindromicSubstring(maxLengthPalindromicSubstring) : maxLengthPalindromicSubstring);
    }

    public static boolean isPalindrome(String s){

        if(s.length() == 1){
            return true;
        }

        int i = 0, j = s.length() - 1;

        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
