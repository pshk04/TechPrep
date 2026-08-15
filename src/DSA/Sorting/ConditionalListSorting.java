package Sorting;

import java.util.Arrays;

public class ConditionalListSorting {

    public static void main(String[] args) {
        int[][] numsList = {
                {3,1,2},
                {3,1,4,2}
        };

        for(int i = 0 ; i < numsList.length; i++){
            System.out.println("Conditional Sorting results in: "+Arrays.toString(sortConditionally(numsList[i])));
            System.out.println("Conditional Sorting results in: "+Arrays.toString(sortConditionallyUsingQuickSort(numsList[i])));
        }
    }

    public static int[] sortConditionallyUsingQuickSort(int[] nums){

        quickSort(nums, 0, nums.length - 1);

        if(nums.length % 2 == 0){
            return nums;
        }else{
            int i = 0, j = nums.length - 1;
            while(i < j){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j--;
            }
            return nums;
        }
    }

    public static void quickSort(int[] nums, int low, int high){
        if(low < high){
            int pivotIndex = partition(nums, low, high);
            quickSort(nums, low, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] nums, int low, int high){
        int i = low - 1;
        int pivot = nums[high];

        for(int j = low; j < nums.length; j++){
            if(nums[j] < pivot){
                i++;
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[high];
        nums[high] = temp;
        return i + 1;
    }
    /**
     * Implemented using Count Sort => O(n + k)
     * Works for positive values
     * @param nums
     * @return
     */
    public static int[] sortConditionally(int[] nums){

        int max = Integer.MIN_VALUE;
        int[] count;
        int[] result = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
        }

        count = new int[max + 1];
        Arrays.fill(count, 0);

        for(int i = 0; i < nums.length; i++){
            count[nums[i]]++;
        }

        for(int i = 1; i <= max; i++){
            count[i] = count[i] + count[i - 1];
        }

        for(int i = nums.length - 1; i >= 0; i--){
            result[count[nums[i]] - 1] = nums[i];
            count[nums[i]]--;
        }

        if(nums.length % 2 == 0){
            return result;
        }else{
            int i = 0, j = result.length - 1;
            while(i < j){
                int temp = result[j];
                result[j] = result[i];
                result[i] = temp;
                i++;
                j--;
            }
            return result;
        }

    }
}
