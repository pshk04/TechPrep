package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindScoreOfArrayAfterMarking {

    public static void main() {
        int[][] nums = {
                {2,1,3,4,5,2},
                {2,3,5,1,3,2}
        };

        for(int i = 0 ; i < nums.length; i++){
            System.out.println("the score of an array is: "+findScore(nums[i]));
        }

    }

    public static long findScore(int[] nums) {
        Map<Integer, List<Integer>> numsMap = new TreeMap<>();
        List<Integer> indicesList;
        List<Integer> markedIndicesList = new ArrayList<>();
        int score = 0;

        for(int i = 0 ; i < nums.length; i++){
            if(numsMap.containsKey(nums[i])){
                indicesList = numsMap.get(nums[i]);

            }else{
                indicesList = new ArrayList<>();
            }
            indicesList.add(i);
            numsMap.put(nums[i], indicesList);
        }

        for(Map.Entry<Integer, List<Integer>> entry : numsMap.entrySet()){
            indicesList = entry.getValue();
            for(int index : indicesList){
                if(!markedIndicesList.contains(index)){
                    score += nums[index];
                    if((index - 1) >= 0){
                        markedIndicesList.add((index - 1));
                    }
                    if((index + 1) <= nums.length - 1){
                        markedIndicesList.add((index + 1));
                    }
                }
            }
        }
        return score;
    }
}
