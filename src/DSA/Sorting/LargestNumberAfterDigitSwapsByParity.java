package Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestNumberAfterDigitSwapsByParity {

    public static void main(String[] args) {
        int[] nums = {
                1234,
                65875
        };

        for(int i = 0 ; i < nums.length; i++){
            System.out.println("Largest number after digit swaps: "+largestInteger(nums[i]));
        }
    }

    public static int largestInteger(int num) {
        String numString = String.valueOf(num);
        int[] digits = new int[numString.length()];

        for(int i = 0 ; i < numString.length(); i++){
            digits[i] = numString.charAt(i) - '0';
        }

        List<Integer> evenDigitsList = new ArrayList<>();
        List<Integer> oddDigitsList = new ArrayList<>();

        for(int digit : digits){
            if(digit % 2 == 0){
                evenDigitsList.add(digit);
            }else{
                oddDigitsList.add(digit);
            }
        }
        Collections.sort(evenDigitsList, Collections.reverseOrder());
        Collections.sort(oddDigitsList, Collections.reverseOrder());
        StringBuilder result = new StringBuilder();
        int evenIndex = 0, oddIndex = 0;

        for(int digit : digits){
            if(digit % 2 == 0){
                result.append(evenDigitsList.get(evenIndex));
                evenIndex++;
            }else{
                result.append(oddDigitsList.get(oddIndex));
                oddIndex++;
            }
        }
        return Integer.valueOf(result.toString());
    }

}
