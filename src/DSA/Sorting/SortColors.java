package Sorting;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[][] colorsList = {
                {2,0,2,1,1,0},
                {2,0,1}
        };

        for(int[] colors : colorsList){
            System.out.println("Sorted Colors: "+ Arrays.toString(sortColors(colors)));
        }
    }

    public static int[] sortColors(int[] nums) {
        int red = 0, white = 0, blue = nums.length - 1;

        while(white <= blue){
            if(nums[white] == 0){
                int temp = nums[red];
                nums[red] = nums[white];
                nums[white] = temp;
                red++;
                white++;
            }else if(nums[white] == 1){
                white++;
            }else if(nums[white] == 2){
                int temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                blue--;
            }
        }
        return nums;
    }
}
