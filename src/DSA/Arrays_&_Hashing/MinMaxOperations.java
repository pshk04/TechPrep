import java.util.Arrays;

public class MinMaxOperations {
    public static void main(String[] args) {
        int[][] numsArray = {
                {3,5,1,8,-2,7},
                {10,10,10}
        };
        for(int[] nums: numsArray) {
            System.out.println("The min and max of each array is: " + Arrays.toString(findMinMaxWithoutSorting(nums)));
        }
    }

    public static int[] findMinMax(int[] nums){
        Arrays.sort(nums);

        return new int[]{nums[0], nums[nums.length - 1]};
    }

    public static int[] findMinMaxWithoutSorting(int[] nums){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return new int[]{min, max};
    }
}
