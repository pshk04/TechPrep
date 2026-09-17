package Heaps;

import java.util.*;

public class MinHeapNodeFiltering {

    public static void main(String[] args) {
        int[][] numsArray = {
                {1,3,5,7,9,8,10},
                {2,4,6,8,10,12,14},
                {0,0,0,0,0}
        };
        int[] x = {6,10,1};

        for(int i = 0; i < numsArray.length; i++){
            System.out.println(Arrays.toString(filterHeapNodesOptimized(numsArray[i], x[i])));
        }
    }

    public static int[] filterHeapNodesOptimized(int[] heap, int x) {

        int leftChild = 0, rightChild = 0, index = 0;
        List<Integer> result = new ArrayList<>();
        int[] resultArray;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()){
            index = queue.poll();

            if(index > heap.length){
                break;
            }

            if(heap[index] >= x){
                break;
            }

            result.add(heap[index]);
            leftChild = (2 * index) + 1;
            rightChild = (2 * index) + 2;

            if(leftChild < heap.length){
                queue.add(leftChild);
            }

            if(rightChild < heap.length){
                queue.add(rightChild);
            }
        }

        Collections.sort(result);
        resultArray = new int[result.size()];

        for(int j = 0 ; j < result.size(); j++){
            resultArray[j] = result.get(j);
        }

        return resultArray;
    }



    public static int[] filterHeapNodes(int[] heap, int x) {
        List<Integer> nodesLesserThanXList = new ArrayList<>();

        for(int num : heap){
            if(num < x) {
                nodesLesserThanXList.add(num);
            }
        }

        int[] result = new int[nodesLesserThanXList.size()];

        for(int i = 0; i < nodesLesserThanXList.size(); i++){
            result[i] = nodesLesserThanXList.get(i);
        }

        return result;
    }
}
