package TechPrep100;

import java.util.Arrays;

public class KthLargestElement {
    public static void main(String[] args) {
        int[][] nums = {
                {3,2,1,5,6,4},
                {3,2,3,1,2,4,5,5,6}
        };

        int[] k = {2,4};

        for(int i = 0 ; i < nums.length; i++) {
            System.out.println("The kth largest element is: "+findKthLargest(nums[i], k[i]));
        }
    }

    public static int findKthLargest(int[] nums, int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
