package Sorting;

import java.util.*;

public class H_Index {
    public static void main() {
        int[][] numsArrays = {
                {3,0,6,1,5},
                {1,3,1},
                {10,10,10,10,10}
        };

        for(int i = 0 ; i < numsArrays.length; i++){
            System.out.println("The h-Index is: "+hIndex(numsArrays[i]));
        }
    }

    public static int hIndex(int[] citations) {
        List<Integer> citationsList = new ArrayList<>();
        int hIndex = 0, index = 1, previousCitationValue = 0;

        if(citations.length == 1 && citations[0] == 0){
            return 0;
        }
        for(int i = 0 ; i < citations.length; i++){
            citationsList.add(citations[i]);
        }
        Collections.sort(citationsList, Collections.reverseOrder());

        for(index = 1; index < citationsList.size(); index++){
            if(citationsList.get(index - 1) <= index){
                hIndex = citationsList.get(index - 1);
                break;
            }
        }
        if(hIndex == 0 && index == citations.length){
            hIndex = index;
        }
        return hIndex;
    }
}
