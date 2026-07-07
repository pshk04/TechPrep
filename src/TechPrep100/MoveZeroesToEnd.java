package TechPrep100;

import java.util.Arrays;

public class MoveZeroesToEnd {

    public static void main(String[] args) {
        int[][] numsArray = {
                {0,1,0,3,12},
                {0,0,1},
                {1,0}
        };

        for(int[] nums : numsArray) {
            System.out.println("Array after re-ordering: "+ Arrays.toString(moveZeroes(nums)));
        }
    }

    public static int[] moveZeroes(int[] nums){

        if(nums.length <= 1){
            return nums;
        }

        int i = 0, j = i + 1;

        while(i < j && j < nums.length){
            if(nums[i] == 0 && nums[j] != 0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j++;
            }else if(nums[i] == 0 && nums[j] == 0){
                j++;
            }else{
                i++;
                j++;
            }
        }
        return nums;
    }
}
