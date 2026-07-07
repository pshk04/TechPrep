package TechPrep100;

import java.util.Arrays;

public class MedianOftwoSortedArrays {

    public static void main(String[] args) {
        int[][] nums1Array = {
                {1,3},
                {1,2}
        };

        int[][] nums2Array = {
                {2},
                {3,4}
        };

        for(int i = 0 ; i < nums1Array.length; i++) {
            System.out.println("The median of two sorted arrays: " + findMedianSortedArrays(nums1Array[i], nums2Array[i]));
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2){

        int[] sortedArray = new int[nums1.length + nums2.length];
        int totalLength = nums1.length + nums2.length;
        int index = 0;

        for(int i = 0; i < nums1.length; i++){
            sortedArray[index] = nums1[i];
            index++;
        }
        for(int i = 0; i < nums2.length; i++) {
            sortedArray[index] = nums2[i];
            index++;
        }
        Arrays.sort(sortedArray);
        int medianIndex = totalLength/2;
        System.out.println(Arrays.toString(sortedArray));


        if(totalLength % 2 > 0){
            return (double) (sortedArray[medianIndex]);
        }else{
            return ((double)(sortedArray[medianIndex] + sortedArray[medianIndex - 1])/2);
        }
    }
}
