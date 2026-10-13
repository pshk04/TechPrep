package Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    public static void main(String[] args) {
        int[][] nums = {
                {1,3,-1,-3,5,3,6,7},
                {1,2,3,4,2,3,1,4,2}
        };

        int[] k = {3,3};

        for(int i = 0 ; i < nums.length; i++){
            System.out.println(Arrays.toString(medianSlidingWindow(nums[i],k[i])));
        }

    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;
        double[] mediansArray;
        List<Double> medianNumsList = new ArrayList<>();
        int i = 0;
        double currentMedianValue = 0.0d;

        while(i <= nums.length - k){
            int j = i;
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));

            while(j < (i + k)) {
                addNum(nums[j], minHeap, maxHeap);
                j++;
            }
            currentMedianValue = findMedian(minHeap, maxHeap);
//            System.out.println("currentMedianValue for array: "+
//            Arrays.toString(Arrays.copyOfRange(nums, i, (i + k)))+" : "+currentMedianValue);
            medianNumsList.add(currentMedianValue);
            i++;
        }
        mediansArray = new double[medianNumsList.size()];
        for(int m = 0 ; m < medianNumsList.size(); m++){
            mediansArray[m] = medianNumsList.get(m);
        }
        return mediansArray;
    }

    public static void addNum(int num,PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {

        if(getSize(minHeap, maxHeap) == 0){
            maxHeap.offer(num);
        }else {

            if(num <= maxHeap.peek()){
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }
        }
    }
    public static double findMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {

        if (getSize(minHeap,maxHeap) % 2 == 0) {
            return ((((double) minHeap.peek()) + ((double) maxHeap.peek())) / 2.0);
        } else {
            if (minHeap.size() == 0) {
                return maxHeap.peek();
            } else {
                if(maxHeap.size() > minHeap.size()) {
                    return (double) maxHeap.peek();
                }else{
                    return (double) minHeap.peek();
                }
            }
        }
    }

    public static int getSize(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){

        if (maxHeap.size() > (minHeap.size() + 1)) {
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() > (maxHeap.size() + 1)){
            maxHeap.offer(minHeap.poll());
        }
        return maxHeap.size() + minHeap.size();
    }

}
