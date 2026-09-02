package Sorting;

import java.util.Arrays;
import java.util.Collections;

public class MaxValueWithParitySwaps {

    public static void main(String[] args) {
        int[][] numsArray = {
                {3,5,1,4,2,8},
                {1,2,3,4,5},
                {2,4,6,8,0}
        };

        for(int i = 0 ; i < numsArray.length; i++) {
            System.out.println("The sorted nums array as per parity: "+ Arrays.toString(maxValueWithParitySwaps(numsArray[i])));
        }
    }

    public static int[] maxValueWithParitySwaps(int[] nums) {
        int index = 0;

        while(index < nums.length){
            nums = parityBasedSort(nums);
            index++;
        }
        return nums;
    }

    public static int[] parityBasedSort(int[] nums) {

        for (int i = 1; i < nums.length - 1; i++) {
            if ((nums[i] % 2 == 0 && nums[i + 1] % 2 == 0 && nums[i - 1] % 2 == 0) || (nums[i] % 2 != 0 && nums[i + 1] % 2 != 0 && nums[i - 1] % 2 != 0)) {
                int[] subNumsArray = Arrays.copyOfRange(nums, i - 1, i + 2);
                Arrays.sort(subNumsArray);
                System.out.println("Sub array ascending order: "+Arrays.toString(subNumsArray));
                int index = i - 1;

                for (int j = 0; j < subNumsArray.length / 2; j++) {
                    int temp = subNumsArray[j];
                    subNumsArray[j] = subNumsArray[subNumsArray.length - 1 - j];
                    subNumsArray[subNumsArray.length - 1 - j] = temp;
                }
                System.out.println("Sub array descending order: "+Arrays.toString(subNumsArray));

                for (int k = 0; k < subNumsArray.length; k++) {
                    nums[index] = subNumsArray[k];
                    index++;
                }
                System.out.println("sub array assembled in descending order: " + Arrays.toString(nums));
            }
        }
        return nums;
    }
}
