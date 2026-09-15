package Sorting;

public class ReversePairs {

    public static void main(String[] args) {
        int[][] numsArray = {
                {1,3,2,3,1},
                {2,4,3,5,1},
                {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647},
                {-5,-5},
                {-2147483648,-2147483648,2147483647}
        };

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println("The total number of reverse pair are: "+reversePairs(numsArray[i]));
        }
    }

    public static int reversePairs(int[] nums) {
        int totalReversePairs = 0;

        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] > 0){
                    if(nums[i] != nums[j] && Long.valueOf(nums[i]) > (2 * Long.valueOf(nums[j]))) {
                        totalReversePairs++;
                    }
                }else{
                    if(Long.valueOf(nums[i]) > (2 * Long.valueOf(nums[j]))){
                        totalReversePairs++;
                    }
                }
            }
        }

        return totalReversePairs;
    }
}
