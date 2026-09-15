package Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInAStream {

    public static void main(String[] args) {
        String[][] operations = {
                {"KthLargest","add","add","add","add","add"},
                {"KthLargest","add","add","add","add"},
        };

        int[][][] numsList = {
                {
                        {3,4,5,8,2},
                        {3},
                        {5},
                        {10},
                        {9},
                        {4}
                },
                {
                        {1},
                        {1},
                        {2},
                        {3},
                        {4}
                }
        };

        for(int i = 0 ; i < numsList.length; i++){
            List<List<Integer>> argumentsList = new ArrayList<>();
            List<String> operationsList = new ArrayList<>();
            for(String operation : operations[i]){
                operationsList.add(operation);
            }
            for(int j = 0 ; j < numsList[i].length; j++) {
                List<Integer> argumentList = new ArrayList<>();
                for(int num : numsList[i][j]){
                    argumentList.add(num);
                }
                argumentsList.add(argumentList);
            }
            System.out.println("The Kth Largest element at each operation is: "+processOperations(operationsList, argumentsList));
        }
    }

    public static List<Integer> processOperations(List<String> operations, List<List<Integer>> arguments) {
        List<Integer> KthLargestElementsList = new ArrayList<>();
        PriorityQueue<Integer> argumentsMaxHeap = new PriorityQueue<>((a,b) -> Integer.compare(a,b));
        int k = 0;
        List<Integer> initialList;

        for(int i = 0 ; i < operations.size(); i++) {
            if (i == 0) {
                initialList = arguments.get(i);
                k = initialList.get(0);
                KthLargestElementsList.add(null);
                for(int j = 1 ; j < initialList.size(); j++){
                    argumentsMaxHeap.offer(initialList.get(j));
                    if (argumentsMaxHeap.size() > k) {
                        argumentsMaxHeap.poll();
                    }
                }
            }else {
                initialList = arguments.get(i);
                for (int num : initialList) {
                    argumentsMaxHeap.offer(num);
                    if (argumentsMaxHeap.size() > k) {
                        argumentsMaxHeap.poll();
                    }
                }
                KthLargestElementsList.add(argumentsMaxHeap.peek());
            }
        }
        return KthLargestElementsList;
    }
}
