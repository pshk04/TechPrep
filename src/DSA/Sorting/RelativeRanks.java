package Sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RelativeRanks {

    public static void main(String[] args) {
        int[][] scores = {
                {5,4,3,2,1},
                {10,3,8,9,4}
        };

        for(int i = 0; i < scores.length; i++){
            System.out.println("The placements of the atheletes are: "+ Arrays.toString(findRelativeRanks(scores[i])));
        }
    }

    public static String[] findRelativeRanks(int[] score) {
        int[] originalScore = Arrays.copyOfRange(score, 0, score.length);
        Arrays.sort(score);
        for (int i = 0; i < score.length / 2; i++) {
            int temp = score[i];
            score[i] = score[score.length - 1 - i];
            score[score.length - 1 - i] = temp;
        }

        Map<Integer, String> rankMap = new HashMap<>();

        for(int i = 0 ; i < score.length; i++){
            if(i == 0) {
                rankMap.put(score[i], "Gold Medal");
            }else if(i == 1){
                rankMap.put(score[i], "Silver Medal");
            }else if(i == 2){
                rankMap.put(score[i], "Bronze Medal");
            }else{
                rankMap.put(score[i], (i + 1)+"");
            }
        }
        String[] result = new String[score.length];

        for(int i = 0; i < originalScore.length; i++){
            result[i] = String.valueOf(rankMap.get(originalScore[i]));
        }

        return result;
    }

}
