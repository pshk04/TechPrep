package Sorting;

import java.util.Arrays;

public class ShopperAndOrderWaitTimes {

    public static void main() {
        int[][][] shopperOrders = {
                {
                        {1,2},
                        {2,5},
                        {4,3}
                },
                {
                        {5,2},
                        {5,4},
                        {10,3},
                        {20,1}
                },
                {
                        {1,3},
                        {4,2},
                        {6,1},
                        {8,4},
                        {12,2},
                        {15,3}
                }
        };

        for(int i = 0 ; i < shopperOrders.length; i++){
            System.out.println("The average wait time: "+averageWaitTime(shopperOrders[i]));
        }

    }

    public static int averageWaitTime(int[][] orders) {
        Arrays.sort(orders, (a,b)-> Integer.compare(a[0], b[0]));
        int currentBegin = 0, currentEnd = 0, waitTime =  0, previousEnd = orders[0][1], previousBegin = orders[0][0];
        previousEnd = previousBegin + previousEnd;
        waitTime = previousEnd - previousBegin;

        for(int i = 1 ; i < orders.length; i++){
            currentBegin = orders[i][0];
            currentEnd = orders[i][1];

            if(previousEnd > currentBegin) {
                previousEnd = currentEnd + previousEnd;
                waitTime += previousEnd - currentBegin;
                System.out.println("wait time 1: "+waitTime);
            }else{
                waitTime += (currentBegin + currentEnd) - currentBegin;
                System.out.println("wait time 2: "+waitTime);
            }
        }

        System.out.println("total wait time: "+waitTime);

        return (waitTime / orders.length);
    }
}
