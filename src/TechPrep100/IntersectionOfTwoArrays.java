package TechPrep100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[][] nums1 = {
                {1,2,2,1},
        };

        int[][] nums2 = {
                {2,2}
        };

        for(int i = 0 ; i < nums1.length; i++) {
            System.out.println("The intersection of two arrays is: " + Arrays.toString(intersection(nums1[i], nums2[i])));
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> uniqueNumsSet = new HashSet<>();
        Set<Integer> intersectionSet = new HashSet<>();
        int[] result;

        for(int i = 0 ; i < nums1.length; i++){
            uniqueNumsSet.add(nums1[i]);
        }

        for(int i = 0 ; i < nums2.length; i++){
            if(uniqueNumsSet.contains(nums2[i])){
                intersectionSet.add(nums2[i]);
            }
        }

        result = new int[intersectionSet.size()];
        int index = 0;

        for(int num : intersectionSet){
            result[index] = num;
            index++;
        }
        return result;
    }

}
