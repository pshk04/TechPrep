package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimalWindowSpanningArrays {

    public static void main() {
        int[][][] arrays = {
                {
                        {4,10,15,24,26},
                        {0,9,12,20},
                        {5,18,22,30}
                },
                {
                        {1,2,3},
                        {1,2,3},
                        {1,2,3}
                }
        };

        for(int i = 0 ; i < arrays.length; i++){
            System.out.println(Arrays.toString(minimalSpan(arrays[i])));
        }
    }

    public static int[] minimalSpan(int[][] arrays) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(a[0], b[0]));
        int currentMax = Integer.MIN_VALUE, currentBestMin = 0, currentBestMax = 0, currentMin = 0, arrayIndex = 0, elementIndex = 0;
        int minimumValue = 0, nextValue = 0;
        int[] heapsTopElement;

        for(int i = 0 ; i < arrays.length; i++){
            minHeap.offer(new int[]{arrays[i][0], i, 0});
            if(arrays[i][0] > currentMax){
                currentMax = arrays[i][0];
            }
        }

        currentBestMin = minHeap.peek()[0];
        currentBestMax = currentMax;

        while(true){
            heapsTopElement = minHeap.poll();
            minimumValue = heapsTopElement[0];
            arrayIndex = heapsTopElement[1];
            elementIndex = heapsTopElement[2];

            if((currentMax - minimumValue) < (currentBestMax - currentBestMin) ||
                    ((currentMax - minimumValue) == (currentBestMax - currentBestMin) && (minimumValue < currentBestMin)
                    )
            ){
                currentBestMin = minimumValue;
                currentBestMax = currentMax;
            }

            if((elementIndex + 1) == arrays[arrayIndex].length){
                break;
            }

            nextValue = arrays[arrayIndex][elementIndex + 1];
            if(nextValue > currentMax){
                currentMax = nextValue;
            }
            minHeap.offer(new int[]{nextValue, arrayIndex, elementIndex + 1});
        }

        return new int[]{currentBestMin, currentBestMax};
    }
}
