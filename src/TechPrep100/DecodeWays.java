package TechPrep100;

public class DecodeWays {

    public static void main(String[] args) {
        String[] s = {
                "13",
                "126",
                "1223",
                "06"
        };
        for(int i = 0; i < s.length; i++) {
            System.out.println("The number of ways we can decode the strings: " + numDecode(s[i]));
        }
    }

    public static int numDecode(String s){

        if(s == null || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        int[] dp = new int[s.length() + 1];

        for(int i = 0 ; i < s.length(); i++){
            dp[i] = 0;
        }

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < s.length() + 1; i++){
            if(s.charAt(i - 1) != '0'){
                dp[i] = dp[i] + dp[i - 1];
            }

            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if(twoDigit >= 10 && twoDigit <= 26){
                dp[i] = dp[i] + dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
