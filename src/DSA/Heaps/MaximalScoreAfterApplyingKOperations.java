package Heaps;

import java.util.PriorityQueue;

public class MaximalScoreAfterApplyingKOperations {
    public static void main(String[] args) {
        int[][] numsArray = {
                {10,10,10,10,10},
                {1,10,3,3,3},
                {999999999,999999998,999999997}
        };

        int[] k = {5,3,10};

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println(maxKelements(numsArray[i], k[i]));
        }
    }

    public static long maxKelements(int[] nums, int k) {
        PriorityQueue<int[]> numsMaxHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(b[0],a[0]);
        });
        long maxScore = 0l;
        int index = 0;
        int[] heapElement = new int[2];

        for(int i = 0 ; i < nums.length; i++){
            numsMaxHeap.offer(new int[]{nums[i], i});
        }

        while(k > 0){
            heapElement = numsMaxHeap.poll();
            maxScore += heapElement[0];
            index = heapElement[1];
            nums[index] = (int) Math.ceil(((double) nums[index] / 3));
            numsMaxHeap.offer(new int[]{nums[index], index});
            k--;
        }

        return maxScore;
    }
}
