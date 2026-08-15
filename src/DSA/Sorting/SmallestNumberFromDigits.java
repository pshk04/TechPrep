package Sorting;

import java.util.ArrayList;
import java.util.List;

public class SmallestNumberFromDigits {

    public static void main(String[] args) {
        int[] nums = {310,9876,100302}; // 310,9876,100302

        for(int i = 0 ; i < nums.length; i++){
            System.out.println("The smallest number that can be formed without leading zeros is: "+formSmallestNumber(nums[i]));
        }
    }

    public static int formSmallestNumber(int num){
        int[] nums = createArrayWithDigits(num);
        nums = SelectionSort.selectionSort(nums);
        int i = 0, smallestNumber = 0, maxDecimalValue = 1, digitsPlace = 1;

        if(nums.length > 1){
            nums = SelectionSort.selectionSort(nums);

            if(nums[i] == 0) {
                for(int j = 1; j < nums.length; j++){
                    if(nums[j] > 0){
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
            }

            for(int l = nums.length - 1; l >= 0; l--){
                if(l == nums.length - 1){
                    smallestNumber += nums[l] * maxDecimalValue;
                }else {
                    smallestNumber = smallestNumber + (nums[l] * digitsPlace);
                }
                digitsPlace *= 10;
            }
            return smallestNumber;
        }
        return num;
    }

    public static int[] createArrayWithDigits(int num){
        List<Integer> numsList = new ArrayList<>();
        int index = 0;

        while(num >= 1){
            numsList.add(num % 10);
            num = num / 10;
        }
        int[] nums = new int[numsList.size()];

        for(int number : numsList){
            nums[index] = number;
            index++;
        }
        return nums;
    }
}
