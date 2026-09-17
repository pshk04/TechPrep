package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    public static void main(String[] args) {
        int[][][] arrays = {
                {
                        {1,4,5},
                        {1,3,4},
                        {2,6}
                },

                {
                        {-5,-2,0},
                        {-3,1,4},
                        {2,3}
                }
        };

        for(int[][] nums : arrays){
            System.out.println("Merged and sorted arrays: "+ Arrays.toString(mergeKSortedArrays(nums)));
        }

    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (Integer.compare(a[0], b[0])));
        int totalSize = 0, smallestElement = 0, arrayIndex = 0, elementIndex = 0, index = 0;
        int[] currentHeapElement, result;

        for(int i = 0 ; i < arrays.length; i++){
            totalSize += arrays[i].length;
            if(arrays[i].length > 0){
                minHeap.offer(new int[]{arrays[i][0], i, 0});
            }
        }
        result = new int[totalSize];

        while(!minHeap.isEmpty()){
            currentHeapElement = minHeap.poll();
            smallestElement = currentHeapElement[0];
            arrayIndex = currentHeapElement[1];
            elementIndex = currentHeapElement[2];
            result[index] = smallestElement;

            if(arrays[arrayIndex].length > (elementIndex + 1)) {
                minHeap.offer(new int[]{arrays[arrayIndex][elementIndex + 1], arrayIndex, (elementIndex + 1)});
            }
            index++;
        }

        return result;
    }
}
