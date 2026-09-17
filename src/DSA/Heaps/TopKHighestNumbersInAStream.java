package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TopKHighestNumbersInAStream {

    public static void main(String[] args) {
        int[][] numsArray = {
                {5,2,9,1,7,3},
                {4,4,4,4}
        };
        int[] k = {2,3};

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println(Arrays.toString(topKHighestStream(numsArray[i], k[i])));
        }
    }

    public static int[] topKHighestStream(int[] nums, int k) {
        PriorityQueue<Integer> numsMinHeap = new PriorityQueue<>();
        int[] result = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            numsMinHeap.offer(nums[i]);
            if(i < (k - 1)){
                result[i] = -1;
            }else{
                if(numsMinHeap.size() > k){
                    numsMinHeap.poll();
                }
                result[i] = numsMinHeap.peek();
            }
        }
        return result;
    }
}
