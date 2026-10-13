package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ZeroArrayTransformationIII {

    public static void main(String[] args) {
        int[][][] queries = {
                {
                        {0,2},
                        {0,2},
                        {1,1}
                },
                {
                        {1,3},
                        {0,2},
                        {1,3},
                        {1,2}
                },
                {
                        {0,3}
                },
                {
                        {0,2},
                        {1,2},
                        {0,0}
                }
        };

        int[][] numsArray = {
                {2,0,2},
                {1,1,1,1},
                {1,2,3,4},
                {0,0,0}
        };

        for(int i = 0 ; i < numsArray.length; i++){
            System.out.println("The removals required is/are: "+maxRemoval(numsArray[i], queries[i]));
        }
    }

    public static int maxRemoval(int[] nums, int[][] queries) {
        int attemptCount = 0, range = 0;
        int[] currentQuery;
        int[] zeroArray = new int[nums.length];
        boolean attemptMadeToConvert = false;
        Arrays.fill(zeroArray, 0);
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> maxRangeQueriesMaxHeap = new PriorityQueue<>((a,b) -> {
            int maxRangeComparator = Integer.compare(b[2], a[2]);
            if(maxRangeComparator == 0){
                return Integer.compare(a[0], b[0]);
            }
            return maxRangeComparator;
        });

        for(int[] query : queries){
            range = query[1] - query[0] + 1;
            maxRangeQueriesMaxHeap.offer(new int[]{query[0], query[1], range});
        }

        while(!maxRangeQueriesMaxHeap.isEmpty()){
            currentQuery = maxRangeQueriesMaxHeap.poll();

            for(int index = currentQuery[0]; index <= currentQuery[1]; index++){
                if(nums[index] >= 1){
                    nums[index] -= 1;
                    attemptMadeToConvert = true;
                }
            }
            if(attemptMadeToConvert) {
                attemptCount++;
            }

            if(Arrays.toString(zeroArray).equals(Arrays.toString(nums))){
                break;
            }
            attemptMadeToConvert = false;
        }
        if(!Arrays.toString(zeroArray).equals(Arrays.toString(nums))){
            return -1;
        }

        return queries.length - attemptCount;
    }
}
