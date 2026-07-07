package DynamicProgramming;

import java.util.*;

public class PrintLongestCommonSubstring {
    public static void main(String[] args) {
        String[] text1 = {
                "techprep",
                "apple",
                "abcd"
        };

        String[] text2 = {
                "teacher",
                "ample",
                "xyz"
        };

        for(int i = 0; i < text1.length; i++) {
            System.out.println("The length of the longest common subsequence: " + printLongestCommonSubstring(text1[i], text2[i]));
        }
    }

    public static String printLongestCommonSubstring(String s1, String s2) {

        StringBuilder longestCommonSubstring = new StringBuilder();

        PriorityQueue<PalindromeLength> maxHeapForPalindrome = new PriorityQueue<>((d1, d2) -> {
            int priorityCompare = Integer.compare(d2.getPalindromeLength(), d1.getPalindromeLength());

            if (priorityCompare == 0) {
                return d1.getPalindrome().compareTo(d2.getPalindrome());
            }

            return priorityCompare;
        });

        int maxLength = 0;

        if (s1.length() == 0 || s1 == null || s2.length() == 0 || s2 == null) {
            return "";
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

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
                    maxLength = Math.max(maxLength, dp[i][j]);
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

        int i = s1.length();
        int j = s2.length();
        String currentPalindrome = "";
        PalindromeLength palindromeLength;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                longestCommonSubstring.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                currentPalindrome = longestCommonSubstring.reverse().toString();
                palindromeLength = new PalindromeLength(currentPalindrome, currentPalindrome.length());
                maxHeapForPalindrome.offer(palindromeLength);
                longestCommonSubstring = new StringBuilder();
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else if (dp[i][j - 1] == dp[i - 1][j]) {
                    i--;
                    j--;
                } else {
                    i--;
                }
            }
        }

        currentPalindrome = longestCommonSubstring.reverse().toString();
        palindromeLength = new PalindromeLength(currentPalindrome, currentPalindrome.length());
        maxHeapForPalindrome.offer(palindromeLength);
        return maxHeapForPalindrome.poll().getPalindrome();
    }
}
