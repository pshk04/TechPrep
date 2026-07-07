package TechPrep100;

import java.util.Arrays;

public class ProductOfArrayExceptItself {

    public static void main(String[] args) {
        int[][] nums = {
                {1,3,5,7},
                {2,4,6,8}
        };
        for(int i = 0 ; i < nums.length; i++) {
            System.out.println("The product of the array elements except itself: "+ Arrays.toString(productExceptItself(nums[i])));
        }
    }

    public static int[] productExceptItself(int[] nums){

        int[] leftproduct = new int[nums.length];
        int[] rightproduct = new int[nums.length];
        int[] finalproduct = new int[nums.length];
        leftproduct[0] = 1;
        rightproduct[nums.length - 1] = 1;

        for(int i = 1 ; i < nums.length; i++){
            leftproduct[i] = nums[i - 1] * leftproduct[i - 1];
        }

        for(int i = nums.length - 2; i >= 0; i--){
            rightproduct[i] = nums[i + 1] * rightproduct[i + 1];
        }

        for(int i = 0 ; i < nums.length; i++){
            finalproduct[i] = leftproduct[i] * rightproduct[i];
        }

        return finalproduct;
    }
}
