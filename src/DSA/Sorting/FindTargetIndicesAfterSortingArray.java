package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndicesAfterSortingArray {

    public static void main() {
        int[][] numsArrays = {
                {1,2,5,2,3},
                {1,2,5,2,3}
        };

        int[] targets = {2,3};

        for(int i = 0 ; i < targets.length; i++){
            System.out.println("The target indices are located at: "+targetIndices(numsArrays[i], targets[i]));
        }
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> targetIndicesList = new ArrayList<>();

        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] == target){
                targetIndicesList.add(i);
            }
        }
        return targetIndicesList;
    }

}
