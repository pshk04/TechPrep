package Sorting;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class RelativeSort {

    public static void main(String[] args) {
        int[][] arr1 = {
                {2,3,1,3,2,4,6,7,9,2,19},
                {28,6,22,8,44,17}
        };
        int[][] arr2 = {
                {2,1,4,3,9,6},
                {22,28,8,6}
        };

        for(int i = 0 ; i < arr1.length; i++){
            System.out.println("After relative sorting: "+ Arrays.toString(relativeSorting(arr1[i], arr2[i])));
        }
    }

    public static int[] relativeSorting(int[] arr1, int[] arr2){
        Map<Integer, Integer> countMap = new TreeMap<>();
        int[] result = new int[arr1.length];
        int index = 0, resultIndex = 0, number = 0, count = 0;

        for(int i = 0 ; i < arr1.length; i++){
            if(countMap.containsKey(arr1[i])){
                countMap.put(arr1[i], countMap.get(arr1[i]) + 1);
            }else{
                countMap.put(arr1[i], 1);
            }
        }

        for(int num : arr2){
            if(countMap.containsKey(num)){
                count = countMap.get(num);
                while(resultIndex < count){
                    result[index] = num;
                    index++;
                    resultIndex++;
                }
                resultIndex = 0;
                countMap.remove(num);
            }
        }
        if(countMap.size()> 0){
            for(Map.Entry<Integer, Integer> entry : countMap.entrySet()){
                count = entry.getValue();
                number = entry.getKey();
                while(count > 0) {
                    result[index] = number;
                    index++;
                    count--;
                }
            }
        }
        return result;
    }
}
