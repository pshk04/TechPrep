package Sorting;

import java.util.Arrays;

public class MinimumIncrementToMakeArrayUnique {

    public static void main(String[] args) {
        int[][] numsArray = {
                {1,2,2},
                {3,2,1,2,1,7}
        };

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println("the unique array: "+ minIncrementForUnique(numsArray[i]));
        }
    }

    public static int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = 1, totalMoves = 0;

        while(left < right && right < nums.length){
            if(nums[left] == nums[right]){
                nums[right]++;
                totalMoves++;
            }else if(nums[left] > nums[right]){
                while(nums[left] >= nums[right]){
                    nums[right]++;
                    totalMoves++;
                }
            }
            left++;
            right++;
        }
        return totalMoves;
    }

}
