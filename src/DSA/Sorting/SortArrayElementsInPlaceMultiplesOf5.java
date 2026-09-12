package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortArrayElementsInPlaceMultiplesOf5 {

    public static void main(String[] args) {
        int[][] nums = {
                {3,10,7,20,5,2,15},
                {25,1,30,2,10,3}
        };

        for(int i = 0; i < nums.length; i++){
            System.out.println("Sorting the multiples of 5 in array: "+ Arrays.toString(sortMultiplesOf5(nums[i])));
        }
    }

    public static int[] sortMultiplesOf5(int[] nums) {
        List<Integer> multiplesOf5Indices = new ArrayList<>();
        List<Integer> multiplesOf5Nums = new ArrayList<>();

        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] % 5 == 0){
                multiplesOf5Indices.add(i);
                multiplesOf5Nums.add(nums[i]);
            }
        }

        Collections.sort(multiplesOf5Nums, (a, b) -> Integer.compare(b,a));

        for(int i = 0; i < multiplesOf5Indices.size(); i++){
            nums[multiplesOf5Indices.get(i)] = multiplesOf5Nums.get(i);
        }

        return nums;
    }
}
