package Heaps;

import java.util.PriorityQueue;

public class NumberOfOrdersInTheBacklog {

    public static void main(String[] args) {
        int[][][] ordersList = {
                {
                        {7,1000000000,1},
                        {15,3,0},
                        {5,999999995,0},
                        {5,1,1}
                },
                {
                        {10,5,0},
                        {15,2,1},
                        {25,1,1},
                        {30,4,0}
                }
        };

        for(int i = 0 ; i < ordersList.length; i++){
            System.out.println(getNumberOfBacklogOrders(ordersList[i]));
        }
    }

    public static int getNumberOfBacklogOrders(int[][] orders) {
        int buyOrderPrice = 0, buyOrderAmount = 0, sellOrderPrice = 0, sellOrderAmount = 0, MOD = 1_000_000_007;
        int[] sellOrder, buyOrder;
        long totalBackLogOrder = 0l;
        boolean orderMovedToBackLog = true;
        PriorityQueue<int[]> buyOrdersMaxHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(b[0], a[0]);
        });

        PriorityQueue<int[]> sellOrdersMinHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(a[0], b[0]);
        });

        for(int[] order : orders){
            orderMovedToBackLog = true;
            if(order[2] == 0){
                buyOrderPrice = order[0];
                buyOrderAmount = order[1];
                if(buyOrdersMaxHeap.isEmpty() && sellOrdersMinHeap.isEmpty()){
                    buyOrdersMaxHeap.offer(new int[]{buyOrderPrice, buyOrderAmount});
                }else if((!buyOrdersMaxHeap.isEmpty() || buyOrdersMaxHeap.isEmpty()) && !sellOrdersMinHeap.isEmpty()){
                    while(!sellOrdersMinHeap.isEmpty() &&
                            sellOrdersMinHeap.peek()[0] <= buyOrderPrice && buyOrderAmount > 0)
                    {
                        sellOrder = sellOrdersMinHeap.poll();
                        sellOrderPrice = sellOrder[0];
                        sellOrderAmount = sellOrder[1];
                        if(buyOrderAmount >= sellOrderAmount) {
                            buyOrderAmount -= sellOrderAmount;
                        }else{
                            sellOrderAmount -= buyOrderAmount;
                            buyOrderAmount = 0;
                            sellOrdersMinHeap.offer(new int[]{sellOrderPrice, sellOrderAmount});
                        }
                        orderMovedToBackLog = false;
                    }
                    if(buyOrderAmount > 0 || orderMovedToBackLog) {
                        buyOrdersMaxHeap.offer(new int[]{buyOrderPrice, buyOrderAmount});
                    }
                }else if(!buyOrdersMaxHeap.isEmpty() && sellOrdersMinHeap.isEmpty()){
                    buyOrdersMaxHeap.offer(new int[]{buyOrderPrice, buyOrderAmount});
                }
            }else{
                sellOrderPrice = order[0];
                sellOrderAmount = order[1];
                if(buyOrdersMaxHeap.isEmpty() && sellOrdersMinHeap.isEmpty()){
                    sellOrdersMinHeap.offer(new int[]{sellOrderPrice, sellOrderAmount});
                }else if((!sellOrdersMinHeap.isEmpty() || sellOrdersMinHeap.isEmpty()) && !buyOrdersMaxHeap.isEmpty()){
                    while(!buyOrdersMaxHeap.isEmpty() &&
                            buyOrdersMaxHeap.peek()[0] >= sellOrderPrice && sellOrderAmount > 0)
                    {
                        buyOrder = buyOrdersMaxHeap.poll();
                        buyOrderPrice = buyOrder[0];
                        buyOrderAmount = buyOrder[1];
                        if(sellOrderAmount >= buyOrderAmount) {
                            sellOrderAmount -= buyOrderAmount;
                        }else{
                            buyOrderAmount -= sellOrderAmount;
                            sellOrderAmount = 0;
                            buyOrdersMaxHeap.offer(new int[]{buyOrderPrice, buyOrderAmount});
                        }
                        orderMovedToBackLog = false;
                    }
                    if(sellOrderAmount > 1 || orderMovedToBackLog) {
                        sellOrdersMinHeap.offer(new int[]{sellOrderPrice, sellOrderAmount});
                    }
                }else if(buyOrdersMaxHeap.isEmpty() && !sellOrdersMinHeap.isEmpty()){
                    sellOrdersMinHeap.offer(new int[]{sellOrderPrice, sellOrderAmount});
                }
            }
        }

        while(!buyOrdersMaxHeap.isEmpty()){
            totalBackLogOrder += buyOrdersMaxHeap.poll()[1];
        }

        while(!sellOrdersMinHeap.isEmpty()){
            totalBackLogOrder += sellOrdersMinHeap.poll()[1];
        }

        return (int)(totalBackLogOrder % MOD);
    }
}
