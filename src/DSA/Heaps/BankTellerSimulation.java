package Heaps;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BankTellerSimulation {

    public static void main(String[] args) {
        int[] customers = {5,3};
        int[][] serviceTimes = {
                {3,5},
                {1,2,3}
        };

        for(int i = 0 ; i < customers.length; i++){
            System.out.println("Last customer finishes at time: "+lastCustomerTime(customers[i], serviceTimes[i]));
        }
    }

    public static int lastCustomerTime(int customers, int[] serviceTimes) {

        PriorityQueue<int[]> tellerTimeMinHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        PriorityQueue<Integer> finishingTimeMaxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        int startTime = 0, endTime = 0, tellerId = 0;
        int[] availableTeller;

        for(int i = 0; i < customers; i++){
            if(i < serviceTimes.length) {
                endTime = startTime + serviceTimes[tellerId];
                tellerTimeMinHeap.offer(new int[]{tellerId, endTime});
                tellerId++;
            }else{
                availableTeller = tellerTimeMinHeap.poll();
                tellerId = availableTeller[0];
                endTime = availableTeller[1] + serviceTimes[tellerId];
                tellerTimeMinHeap.offer(new int[]{tellerId, endTime});
            }
            finishingTimeMaxHeap.offer(endTime);
        }
        return finishingTimeMaxHeap.poll();
    }


}
