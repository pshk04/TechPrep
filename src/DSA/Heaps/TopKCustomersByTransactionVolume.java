package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TopKCustomersByTransactionVolume {

    public static void main(String[] args) {
        int[][][] transactionsList = {
                {
                        {1,500},
                        {2,300},
                        {3,700},
                        {1,200},
                        {4,100},
                        {5,600},
                        {2,400},
                        {6,50}
                },
                {
                        {10,1000},
                        {20,2000},
                        {30,3000}
                }
        };

        for(int[][] transactions : transactionsList){
            System.out.println("Top volume customers: "+ Arrays.toString(topKCustomers(transactions)));
        }
    }

    public static int[] topKCustomers(int[][] transactions) {
        Map<Integer, Integer> customerTransactionVolumeMap = new TreeMap<>();
        PriorityQueue<int[]> customerTransactionsMaxHeap = new PriorityQueue<>((a,b) -> {
            int transactionsVolumeComparator = Integer.compare(b[1], a[1]);
            if(transactionsVolumeComparator == 0){
                return Integer.compare(a[0], b[0]);
            }
            return transactionsVolumeComparator;
        });

        for(int[] transaction : transactions){
            if(customerTransactionVolumeMap.containsKey(transaction[0])){
                customerTransactionVolumeMap.put(transaction[0], customerTransactionVolumeMap.get(transaction[0]) + transaction[1]);
            }else{
                customerTransactionVolumeMap.put(transaction[0], transaction[1]);
            }
        }

        for(Map.Entry<Integer, Integer> entry : customerTransactionVolumeMap.entrySet()){
            customerTransactionsMaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int index = 0, count = 0, size = (customerTransactionVolumeMap.size() >= 5) ? 5 : customerTransactionVolumeMap.size();
        int[] result = new int[size];

        while(!customerTransactionsMaxHeap.isEmpty()){
            if(count < 5) {
                result[index] = customerTransactionsMaxHeap.poll()[0];
                count++;
            }else{
                break;
            }
            index++;
        }

        return result;
    }
}
