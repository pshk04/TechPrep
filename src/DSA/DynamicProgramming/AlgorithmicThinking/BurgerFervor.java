package DynamicProgramming.AlgorithmicThinking;

import java.util.Arrays;

public class BurgerFervor {
    public static long totalCalls;

    public static void main(String[] args) {
        int[] m = {4};
        int[] n = {9};
        int[] t = {15};

        // Memoization
//        for(int i = 0 ; i < n.length; i++) {
//            maximumBurgersWithBeerMemoization(m[i],n[i],t[i]);
//            System.out.println();
//        }

        //DP
        for(int i = 0 ; i < n.length; i++) {
            maximumBurgersWithBeerDP(m[i],n[i],t[i]);
            System.out.println();
        }
    }

    public static int maximumBurgers(int m, int n, int t, int[] memo){
        int first = 0, second = 0;
        totalCalls++;

        if(memo[t] != -2){
            return memo[t];
        }
        if(t == 0){
            memo[t] = 0;
            return 0;
        }
        if(t >= m){
            first = maximumBurgers(m, n, t - m, memo);
        }else{
            first = -1;
        }

        if(t >= n){
            second = maximumBurgers(m, n, t - n, memo);
        }else{
            second = -1;
        }

        if(first == -1 && second == -1){
            memo[t] = -1;
            return -1;
        }else{
            memo[t] = Math.max(first, second) + 1;
            return memo[t];
        }
    }

    public static void maximumBurgersWithBeerMemoization(int m, int n, int t){
        totalCalls = 0l;
        int result = 0, i = 0;
        String minutesString = "";
        int[] memo = new int[100];

        for(int j = 0 ; j <= t; j++){
            memo[j] = -2;
        }

        result = maximumBurgers(m,n,t,memo);
        if(result >= 0){
            System.out.println("Homer can eat exactly "+result+" burgers in "+t+" minutes");
        }else{
            i = t - 1;
            result = maximumBurgers(m,n,i,memo);
            while(result == -1){
                i--;
                result = maximumBurgers(m,n,i,memo);
            }
            minutesString = ((t-i) > 1) ? (t-i) + " minutes" : "a minute";
            System.out.println("Homer can eat "+result +" burgers and drink beer for "+ minutesString + " in "+t+" minutes");
        }
        System.out.println("totalCalls made: "+totalCalls);
    }

    public static void maximumBurgersWithBeerDP(int m, int n, int t){
        totalCalls = 0l;
        int result = 0, first = 0, second = 0, i = 0;
        String minutesString = "";
        int[] dp = new int[100];
        dp[0] = 0;

        for(i = 1 ; i <= t; i++){
            if(i >= m){
                first = dp[i - m];
            }else{
                first = -1;
            }

            if(i >= n){
                second = dp[i - n];
            }else{
                second = -1;
            }

            if(first == -1 && second == -1){
                dp[i] = -1;
            }else{
                dp[i] = Math.max(first, second) + 1;
            }
        }
        System.out.println(Arrays.toString(dp));
        result = dp[t];
        if(result >= 0){
            System.out.println("Homer can eat exactly "+result+" burgers in "+t+" minutes");
            printOptimalSolution(m,n,dp,t);
        }else{
            i = t - 1;
            result = dp[i];
            while(result == -1){
                i--;
                result = dp[i];
            }
            minutesString = ((t-i) > 1) ? (t-i) + " minutes" : "a minute";
            System.out.println("Homer can eat "+result +" burgers and drink beer for "+ minutesString + " in "+t+" minutes");
            printOptimalSolution(m,n,dp,i);
        }
        System.out.println("totalCalls made: "+totalCalls);
    }

    public static void printOptimalSolution(int m, int n, int[] dp, int minutes){
        int first, second;
        while(minutes > 0){
            first = -1;
            second = -1;

            if(minutes >= m){
                first = dp[minutes - m];
            }
            if(minutes >= n){
                second = dp[minutes - n];
            }
            if(first >= second){
                System.out.println("Eat a "+m+" minute burger");
                minutes = minutes - m;
            }else{
                System.out.println("Eat a "+n+" minute burger");
                minutes = minutes - n;
            }
        }
    }
}
