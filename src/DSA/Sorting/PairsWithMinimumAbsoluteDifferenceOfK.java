package Sorting;

import java.util.*;

public class PairsWithMinimumAbsoluteDifferenceOfK {

    public static void main(String[] args) {
        int[][] nums = {
                {4,2,1,3}
        };

        for(int i = 0 ; i < nums.length; i++){
            for(int[] pair : minimumAbsDifference(nums[i])) {
                System.out.println(Arrays.toString(pair));
            }
        }
    }

    public static int[][] minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        TreeMap<Integer, List<int[]>> pairsWithADifferenceMap = new TreeMap<>();
        int difference = 0;
        List<int[]> minDifferencePairsList;


        for(int i = 0 ; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                difference = arr[j] - arr[i];
                if(pairsWithADifferenceMap.containsKey(difference)){
                    minDifferencePairsList = pairsWithADifferenceMap.get(difference);
                }else{
                    minDifferencePairsList = new ArrayList<>();
                }
                minDifferencePairsList.add(new int[]{arr[i], arr[j]});
                pairsWithADifferenceMap.put(difference, minDifferencePairsList);
            }
        }

        Integer pairWithMinimumDifference = pairsWithADifferenceMap.firstKey();
        List<int[]> pairs = pairsWithADifferenceMap.get(pairWithMinimumDifference);
        int[][] result = new int[pairs.size()][];
        for(int i = 0; i < pairs.size(); i++){
            result[i] = pairs.get(i);
        }
        return result;
    }
}
