package DynamicProgramming;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[][] nums = {
                {3,10,2,1,20},
                {50,3,10,7,40,80},
                {2,2,2,3,4,2,5}
        };

        for(int i = 0 ; i < nums.length; i++) {
            System.out.println("the length of increasing subsequence is: "+lengthOfLIS(nums[i]));
        }
    }

    public static int lengthOfLIS(int[] nums){

        if(nums == null || nums.length == 0){
            return 0;
        }

        int[][] dp = new int[nums.length][nums.length];

        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++) {
                dp[i][j] = 0;
            }
        }
        int previousMax = 0, currentCount = 0;

        for(int i = 0; i < nums.length; i++){
            previousMax = nums[i];
            for(int j = i; j < nums.length; j++){
                if(nums[i] == nums[j] && i == 0 && j == 0){
                    dp[i][j] = 1;
                }else if(nums[i] == nums[j] && i == 0 && j > 0){
                    dp[i][j] = dp[i][j - 1];
                }else if(nums[i] == nums[j] && i > 0 && j > 0){
                    dp[i][j] = 1;
                } else if (nums[i] < nums[j] && nums[j] > previousMax) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (nums[i] < nums[j] && nums[j] <= previousMax) {
                    dp[i][j] = dp[i][j - 1];
                }  else if (nums[i] > nums[j] && nums[j] <= previousMax) {
                    dp[i][j] = dp[i][j - 1];
                }

                if(nums[j] > previousMax){
                    previousMax = nums[j];
                }
                if(dp[i][j] > currentCount){
                    currentCount = dp[i][j];
                }
            }
        }

        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length; j++){
                System.out.print(dp[i][j] +" ");
            }
            System.out.println();
        }
        return currentCount;

    }
}
