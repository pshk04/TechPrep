package Sorting;

import java.util.*;

public class MinimumAbsoluteDifference {
    /**
     * Time Complexity: O(n log n) + O(n log n) + O(n log n)  => O(n log n)
     * Space Complexity: O(n) + O(n) => O(n)
     * @param args
     */
    public static void main(String[] args) {
        int[][] numsArray = {
                {4,2,1,3},
                {1,3,6,10,15}
        };

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println("Pairs with minimum absolute difference are: ");
            for(List<Integer> pair : findPairsWithMinimumAbsDifference(numsArray[i])){
                System.out.print(pair+", ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> findPairsWithMinimumAbsDifference(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        int minimumAbsoluteDifference = 0, differnce = 0;
        Arrays.sort(nums);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        int i = 1;
        int[] numPair;
        List<Integer> pair;

        while(i < nums.length){
            differnce = nums[i] - nums[i - 1];
            minHeap.offer(new int[]{nums[i - 1], nums[i], differnce});
            i++;
        }
        minimumAbsoluteDifference = minHeap.peek()[2];

        while(!minHeap.isEmpty()){
            numPair = minHeap.poll();
            differnce = numPair[2];
            if(differnce == minimumAbsoluteDifference){
                pair = new ArrayList<>();
                pair.add(numPair[0]);
                pair.add(numPair[1]);
                result.add(pair);
            }
        }
        Collections.sort(result, ((a,b) -> Integer.compare(a.get(0), b.get(0))));
        return result;
    }
}
