package Heaps;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    public static void main(String[] args) {
        int[][] sticksArray = {
                {2,4,3},
                {1,8,3,5}
        };

        for(int i = 0 ; i < sticksArray.length; i++){
            System.out.println(connectSticks(sticksArray[i]));
        }
    }

    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> sticksConnectCostMinHeap= new PriorityQueue<>();
        int minCost = 0, a = 0, b = 0;

        for(int i = 0 ; i < sticks.length; i++){
            sticksConnectCostMinHeap.offer(sticks[i]);
        }

        while(sticksConnectCostMinHeap.size() > 1){
            a = sticksConnectCostMinHeap.poll();
            b = sticksConnectCostMinHeap.poll();
            int combined = a + b;
            minCost += combined;
            sticksConnectCostMinHeap.offer(combined);
        }

        return minCost;
    }
}
