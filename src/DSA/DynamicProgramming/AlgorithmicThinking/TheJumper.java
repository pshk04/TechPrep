package DynamicProgramming.AlgorithmicThinking;

import java.util.Arrays;

public class TheJumper {

    public static void main(String[] args) {
        int[][] costs = {
                {0,3,5,1,9,7,2,3}
        };
        int[] n = {7};
        int[][] memos = new int[11][11];

        for(int k = 1; k <= 10; k++){
            for(int l = 1; l <= 10; l++){
                memos[k][l] = -2;
            }
        }

        for(int i = 0 ; i < costs.length; i++){
//            System.out.println("The minimum cost required for Nikola to reach the final square using BF is: "+ findMinCost(costs[i], n[i]));
            System.out.println("The minimum cost required for Nikola to reach the final square using FF is: "+
                    (costs[i][2] + findMinCostForwardFormulation(costs[i], n[i], 2, 1, memos)));

            for(int k = 0; k <= 10; k++){
                for(int l = 0; l <= 10; l++){
                    System.out.print(memos[k][l]+"   ");
                }
                System.out.println();
            }
            System.out.println("The minimum cost required for Nikola to reach the final square using DP is: "+
                    (costs[i][2] + findMinCostForwardFormulationDP(costs[i], n[i], 2, 1))
            );
        }


    }

    public static int findMinCost(int[] cost, int n){
        int[][] memos = new int[11][11];
        int best = -1, result = -1;

        for(int k = 0; k <= 10; k++){
            for(int l = 0; l <= 10; l++){
                if(k == 0 || l == 0){
                    memos[k][l] = 0;
                }else {
                    memos[k][l] = -2;
                }
            }
        }

        for(int j = 1; j <= n; j++){
            result = findMinCostBackwardFormulation(cost, n, n, j, memos);
            if(result != -1){
                if(best == -1){
                    best = cost[2] + result;
                }else{
                    best = Math.min(best, (cost[2] + result));
                }
            }
        }

        for(int k = 0; k <= 10; k++){
            for(int l = 0; l <= 10; l++){
                System.out.print(memos[k][l]+"   ");
            }
            System.out.println();
        }

        return best;
    }

    public static int findMinCostBackwardFormulation(int[] cost, int n, int i, int j, int[][] memos){

        int first = -3, second = -3;

        if(memos[i][j] != -2){
            return memos[i][j];
        }
        if(i == 2 && j == 1){
            memos[i][j] = 0;
            return 0;
        }

        if((i - j) >= 1 && (j >= 2)){
            first = findMinCostBackwardFormulation(cost, n, (i - j), (j - 1), memos);
        }else{
            first = -1;
        }

        if((i + j) <= n){
            second = findMinCostBackwardFormulation(cost, n, (i + j), j, memos);
        }else{
            second = -1;
        }

        if(first == -1 && second == -1){
            memos[i][j] = -1;
            return -1;
        }else if(second == -1){
            memos[i][j] = first + cost[i];
            return memos[i][j];
        }else if(first == -1){
            memos[i][j] = second + cost[i];
            return memos[i][j];
        }else{
            memos[i][j] = Math.min(first, second) + cost[i];
            return memos[i][j];
        }
    }

    public static int findMinCostForwardFormulation(int[] cost, int n, int i, int j, int[][] memos){
        int first = -3, second = -3;

        if(memos[i][j] != -2){
            return memos[i][j];
        }
        if(i == n){
            memos[i][j] = 0;
            return 0;
        }

        if((i + j + 1) <= n){
            first = findMinCostForwardFormulation(cost, n, (i + j + 1), (j + 1), memos);
        }else{
            first = -1;
        }

        if((i - j) >= 1){
            second = findMinCostForwardFormulation(cost, n, (i - j), j, memos);
        }else{
            second = -1;
        }

        if(first == -1 && second == -1){
            memos[i][j] = -1;
            return -1;
        }else if(second == -1){
            memos[i][j] = first + cost[i + j + 1];
            return memos[i][j];
        }else if(first == -1){
            memos[i][j] = second + cost[i - j];
            return memos[i][j];
        }else{
            memos[i][j] = Math.min(first + cost[i + j + 1], second + cost[i - j]);
            System.out.println("i: "+i+" j: "+j+" "+memos[i][j]);
            return memos[i][j];
        }
    }

    public static int findMinCostForwardFormulationDP(int[] cost, int n, int index1, int index2) {
        int first = -3, second = -3;
        int[][] dp = new int[n + 1][n + 1];

        for (int j = n; j >= 1; j--) {
            for (int i = 1; i < n; i++) {

                if ((i + j + 1) <= n) {
                    first = dp[i + j + 1][j + 1];
                } else {
                    first = -1;
                }

                if ((i - j) >= 1) {
                    second = dp[i - j][j];
                } else {
                    second = -1;
                }

                if (first == -1 && second == -1) {
                    dp[i][j] = -1;
                } else if (second == -1) {
                    dp[i][j] = first + cost[i + j + 1];
                } else if (first == -1) {
                    dp[i][j] = second + cost[i - j];
                } else {
                    dp[i][j] = Math.min(first + cost[i + j + 1], second + cost[i - j]);
                    System.out.println("i: " + i + " j: " + j + " " + dp[i][j]);
                }
            }
        }
        for(int k = 0; k <= n; k++){
            for(int l = 0; l <= n; l++){
                System.out.print(dp[k][l]+"   ");
            }
            System.out.println();
        }
        return dp[index1][index2];
    }
}
