package TechPrep100;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BestTimeToBuyAndSellStocks {

    public static void main(String[] args) {
        int[][] stockPrices = {
                {8, 2, 5, 4, 11, 1},
                {5, 4, 3, 2, 1},
                {1,2,3,4,5},
                {7,1,5,3,6,4}
        };

        for(int[] prices : stockPrices) {
            System.out.println("The maximum profit that can be made is: " + maxProfit(prices));
        }
    }

    public static int maxProfit(int[] prices) {
        PriorityQueue<int[]> maxHeapForStockPrices = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0], a[0])
        );

        PriorityQueue<int[]> minHeapForStockPrices = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] maxHeapTopValue = new int[2];
        int[] minHeapTopValue = new int[2];

        for (int i = 0; i < prices.length; i++) {
            maxHeapForStockPrices.offer(new int[]{prices[i], i});
            minHeapForStockPrices.offer(new int[]{prices[i], i});
        }

        while (!maxHeapForStockPrices.isEmpty() && !minHeapForStockPrices.isEmpty()) {
            maxHeapTopValue = maxHeapForStockPrices.poll(); // {5,4}
            minHeapTopValue = minHeapForStockPrices.poll(); // {1,0}

            if(maxHeapTopValue[1] == 0 ){
                maxHeapTopValue = maxHeapForStockPrices.poll();
            }
            if(minHeapTopValue[1] > maxHeapTopValue[1]) {
                while (!minHeapForStockPrices.isEmpty() && minHeapTopValue[1] > maxHeapTopValue[1]) {
                    minHeapTopValue = minHeapForStockPrices.poll();
                    if(minHeapTopValue[1] < maxHeapTopValue[1]){
                        return (maxHeapTopValue[0] - minHeapTopValue[0]);
                    }
                }
            }else{
                if((maxHeapTopValue[0] - minHeapTopValue[0]) < 0){
                    return 0;
                }
                return (maxHeapTopValue[0] - minHeapTopValue[0]);
            }
        }
        return 0;
    }
}
