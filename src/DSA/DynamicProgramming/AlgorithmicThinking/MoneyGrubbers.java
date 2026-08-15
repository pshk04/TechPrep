package DynamicProgramming.AlgorithmicThinking;

import java.text.DecimalFormat;
import java.util.Arrays;

public class MoneyGrubbers {
    public static long totalCalls;
    public static int totalApples;


    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        totalApples = 0;
        String finalCount = "";
        double[] memos = new double[100];

        for(int i = 0 ; i < 100; i++){
            memos[i] = -1;
        }
        int[][] nums = {
//                {3,2},
                {4,2}
        };
        double[][] prices = {
//                {4.00, 2.50}
                {3.00, 2.00}
        };

        int[] schemes = {2};

        double[] unitPrices = {1.75};

        int[] numberOfApples = {3};
        totalCalls = 0l;
//        for(int i = 0 ; i < nums.length; i++){
//            System.out.println("Case "+(i + 1)+":");
//            finalCount = df.format(findAtLeastKApples(nums[i], prices[i], schemes[i], unitPrices[i], numberOfApples[i], memos));
//            System.out.println("Buy "+ MoneyGrubbers.totalApples + " for $"+finalCount);
//        }
//
//        System.out.println("totalCalls made: "+totalCalls);
//        totalCalls = 0;

        for(int i = 0 ; i < nums.length; i++){
            System.out.println("Case "+(i + 1)+":");
            finalCount = df.format(findAtLeastKApplesForDP(nums[i], prices[i], schemes[i], unitPrices[i], numberOfApples[i]));
            System.out.println("Buy "+ MoneyGrubbers.totalApples + " for $"+finalCount);
        }
//        System.out.println("totalCalls made: "+totalCalls);
    }

    public static double findAtLeastKApples(int[] num, double[] prices, int numberOfSchemes, double unitPrice, int numberOfApples, double[] memos){
        double best = 0.0d, currentBest = 0.0d;

        if(memos[numberOfApples] != -1){
            return memos[numberOfApples];
        }
        best = findMaxApples(num, prices, numberOfSchemes, unitPrice, numberOfApples, memos);
        memos[numberOfApples] = best;
        totalApples = numberOfApples;

        for(int i = numberOfApples + 1; i < 100; i++){
            currentBest = Math.min(best, findMaxApples(num, prices, numberOfSchemes, unitPrice, i, memos));
            if(currentBest < best){
                best = currentBest;
                totalApples = i;
            }
        }
        return best;
    }


    public static double findMaxApples(int[] num, double[] prices, int numberOfSchemes, double unitPrice, int numberOfApples, double[] memos){
        totalCalls++;
        double best, result;

        if(memos[numberOfApples] != -1){
            return memos[numberOfApples];
        }
        if(numberOfApples == 0){
            memos[numberOfApples] = 0;
            return 0;
        }else{
            result = findMaxApples(num, prices, numberOfSchemes, unitPrice, numberOfApples - 1, memos);
            best = result + unitPrice;

            for(int i = 0; i < numberOfSchemes; i++){
                if((numberOfApples - num[i]) >= 0){
                    result = findMaxApples(num, prices, numberOfSchemes, unitPrice, numberOfApples - num[i], memos);
                    best = Math.min(best, result + prices[i]);
                }
                memos[numberOfApples] = best;
            }
            return best;
        }
    }

    public static double[] findMaxApplesDP(int[] num, double[] prices, int numberOfSchemes, double unitPrice, int numberOfApples, double[] dp){

        double best = 0.0d, result = 0.0d;

        if(numberOfApples == 0){
            dp[0] = 0;
            return dp;
        }else{
            for(int i = 1; i <= numberOfApples; i++) {
                result = dp[i - 1];
                best = result + unitPrice;

                for (int j = 0; j < numberOfSchemes; j++) {
                    if ((i - num[j]) >= 0) {
                        result = dp[i - num[j]];
                        best = Math.min(best, result + prices[j]);
                    }
                    dp[i] = best;
                }
            }
            return dp;
        }
    }

    public static double findAtLeastKApplesForDP(int[] num, double[] prices, int numberOfSchemes, double unitPrice, int numberOfApples){
        double best = 0.0d, currentBest = 0.0d;
        double[] dp = new double[100 + 1];
        dp[0] = 0.0d;

        dp = findMaxApplesDP(num, prices, numberOfSchemes, unitPrice, numberOfApples, dp);
        best = dp[numberOfApples];
        totalApples = numberOfApples;

        for(int i = numberOfApples + 1; i < 100; i++){
            currentBest = findMaxApplesDP(num, prices, numberOfSchemes, unitPrice, i, dp)[i];
            if(currentBest < best){
                best = currentBest;
                totalApples = i;
            }
        }

        System.out.println(Arrays.toString(dp));
        return best;
    }
}
