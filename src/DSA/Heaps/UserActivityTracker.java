package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class UserActivityTracker {

    public static void main(String[] args) {
        int[][][] logsList = {
                {
                        {1,1},
                        {2,1},
                        {1,2},
                        {3,1},
                        {1,3},
                        {2,2},
                        {3,2},
                        {3,3},
                        {3,4}
                },
                {
                        {5,1},
                        {5,2},
                        {3,1},
                        {3,2},
                        {7,1}
                },
        };
        int[] k = {2,3};
        for(int i = 0 ; i < logsList.length; i++){
            System.out.println(Arrays.toString(topKActiveUsers(logsList[i], k[i])));
        }

    }

    public static int[] topKActiveUsers(int[][] logs, int k) {
        int userId = 0;
        int[] result = new int[k];
        Map<Integer, Integer> userActivityFrequencyMap = new TreeMap<>();
        PriorityQueue<int[]> userActivityFrequencyMaxHeap = new PriorityQueue<>((a, b)->{
            int wordFrequencyComparator = Integer.compare(b[1],a[1]);
            if(wordFrequencyComparator == 0){
                return Integer.compare(a[0],b[0]);
            }
            return wordFrequencyComparator;
        });

        for(int[] log : logs){
            userId = log[0];
            if (userActivityFrequencyMap.containsKey(userId)) {
                userActivityFrequencyMap.put(userId, userActivityFrequencyMap.get(userId) + 1);
            } else {
                userActivityFrequencyMap.put(userId, 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : userActivityFrequencyMap.entrySet()){
            userActivityFrequencyMaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int index = 0;

        while(!userActivityFrequencyMaxHeap.isEmpty()){
            if(index < k) {
                result[index] = userActivityFrequencyMaxHeap.poll()[0];
            }else{
                break;
            }
            index++;
        }

        return result;
    }
}
