package Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestPermutationOfAnInteger {

    public static void main() {
        int[] nums = {213, 71092};

        for(int i = 0 ; i < nums.length; i++){
            System.out.println("The largest permutation: "+largestPermutation(nums[i]));
        }
    }

    public static long largestPermutation(long num){

        List<Long> digitsList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        while(num > 0){
            digitsList.add(num % 10);
            num = num / 10;
        }
        Collections.sort(digitsList, Collections.reverseOrder());

        for(long digit : digitsList){
            result.append(digit);
        }

        return Long.parseLong(result.toString());
    }
}
