package Searching;

public class LinearSearch {

    public static void main(String[] args) {
        int[][] nums = {
                {4,2,7,1,9,3},
                {5,1,8,1,4}
        };

        int[] k = {7,1};

        for(int i = 0 ; i < nums.length; i++){
            System.out.println(linearSearch(nums[i], k[i]));
        }
    }

    public static int linearSearch(int[] nums, int key) {

        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] == key){
                return i;
            }
        }
        return -1;
    }
}
