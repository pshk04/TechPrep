package Heaps;

import java.util.PriorityQueue;

public class SumOfKFarthestItems {

    public static void main(String[] args) {
        int[][] numsArray = {
                {1,3,5,7,9},
                {2,-1,10,6},
                {-10,-5,0,5,10}
        };
        int[] k = {2,3,3}; //
        int[] targets = {5,3,0}; //

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println("Sum of "+k[i]+ " elements with largest absolute difference: "+sumOfKFarthest(numsArray[i], targets[i], k[i]));

        }
    }

    public static int sumOfKFarthest(int[] nums, int target, int k) {
        PriorityQueue<int[]> sumOfMaxAbsoluteDiffMaxHeap = new PriorityQueue<>((a,b) -> {
            int absoluteDifferenceComparator = Integer.compare(b[1], a[1]);
            if(absoluteDifferenceComparator == 0){
                return Integer.compare(a[0], b[0]);
            }
            return absoluteDifferenceComparator;
        });
        int absoluteDifference = 0, totalSum = 0;

        for(int i = 0 ; i < nums.length; i++){
            absoluteDifference = Math.abs(nums[i] - target);
            sumOfMaxAbsoluteDiffMaxHeap.offer(new int[]{nums[i], absoluteDifference});
        }

        int absDiff = 0;

        while(k > 0){
            absDiff = sumOfMaxAbsoluteDiffMaxHeap.poll()[0];
            totalSum += absDiff;
            k--;
        }
        return totalSum;
    }
}
