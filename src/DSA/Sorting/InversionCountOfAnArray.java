package Sorting;

public class InversionCountOfAnArray {

    public static void main(String[] args) {
        int[][] numsArray = {
                {2,4,1,3,5},
                {5,4,3,2,1}
        };

        for(int[] nums : numsArray){
            System.out.println("The total number of inversions are: "+countInversions(nums));
        }
    }

    public static long countInversions(int[] arr) {
        int totalInversions = 0;

        for(int i = 0 ; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    totalInversions++;
                }
            }
        }

        return totalInversions;
    }
}
