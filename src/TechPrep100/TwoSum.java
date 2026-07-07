package TechPrep100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[][] numsArray = {
                {1,2,3,4},
                {0,-1,2,-3,-1},
                {6,7,8,9},
                {0,-1,2,-3,1}
        };
        int[] targets = {5,-2,20,-2};
        int[] result;

        for(int i = 0; i < numsArray.length; i++) {
            result = twoSum(numsArray[i], targets[i]);
            System.out.println("The indices of two numbers are: " + Arrays.toString(result));
        }
    }

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> indexMap = new HashMap<>();
        int requiredNumber = 0, secondIndex = Integer.MIN_VALUE;

        for(int i = 0 ; i < nums.length; i++){
            indexMap.put(nums[i], i);
        }

        for(int i = 0 ; i < nums.length; i++){
            requiredNumber = target - nums[i];
            if(indexMap.containsKey(requiredNumber)){
                secondIndex = indexMap.get(requiredNumber);
                if(i != secondIndex) {
                    return new int[]{i,secondIndex};
                }
            }
        }
        return new int[]{};
    }


    public static void twoSumWithSorting(int[] nums, int target){
        Arrays.sort(nums);
        int[] result = new int[2];
        int first = 0, last = nums.length - 1;

        while(first < last){
            if((nums[first] + nums[last]) == target){
                result = new int[]{first, last};
                break;
            }else{
                first++;
                last--;
            }
        }
        System.out.println("The indices of two numbers are: " + Arrays.toString(result));
    }
}
