package DynamicProgramming;

public class LongestCommonSubsequence {

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
            System.out.println("The length of the longest common subsequence: " + longestCommonSubsequence(text1[i], text2[i]));
        }
    }

    public static int longestCommonSubsequence(String s1, String s2){

        if(s1.length() == 0 || s1 == null || s2.length() == 0 || s2 == null){
            return 0;
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i < s1.length() + 1; i++){
            for(int j = 0; j < s2.length() + 1; j++){
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < s1.length() + 1; i++){
            for(int j = 1; j < s2.length() + 1; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for(int i = 0; i < s1.length() + 1; i++){
            for(int j = 0; j < s2.length() + 1; j++){
                System.out.print(dp[i][j] +" ");
            }
            System.out.println();
        }

        return dp[s1.length()][s2.length()];

    }
}
