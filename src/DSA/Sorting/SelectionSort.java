package Sorting;
import java.util.Arrays;

public class SelectionSort {
   /**
    * Time Complexity:
    *       Best Case (Happy Path): O(n2)
    *       Worst Case & Average Case: O(n2)
    * Space Complexity:
    *       O(1) - in-place swapping
 */

    public static void main(String[] args) {
        int[][] numbersList = {
                {5,1,4,2,8},
                {10,9,8,7,6,5,4,3,2,1},
                {9,1,9,1,1,1,9,9},
                {9,1,1,1,1,1,1,1},
                {43}
        };

        for(int[] nums : numbersList){
            System.out.println("The sorted list: "+ Arrays.toString(selectionSort(nums)));
        }
    }

    public static int[] selectionSort(int[] nums){
        int index = 0, min = Integer.MAX_VALUE, minIndex = nums[index];
        boolean minElementFound = false;

        while(index < nums.length) {
            for (int i = index; i < nums.length; i++) {
                if(nums[i] < min){
                    min = nums[i];
                    minIndex = i;
                    minElementFound = true;
                }
            }
            if(minElementFound) {
                int temp = nums[index];
                nums[index] = min;
                nums[minIndex] = temp;
                min = Integer.MAX_VALUE;
            }
            index++;
            minElementFound = false;
        }

        return nums;
    }
}
