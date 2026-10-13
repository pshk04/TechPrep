package Heaps;

import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        this.minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if(this.getSize() == 0){
            this.maxHeap.offer(num);
        }else {

            if(num <= this.maxHeap.peek()){
                this.maxHeap.offer(num);
            }else{
                this.minHeap.offer(num);
            }
        }
    }
    public double findMedian() {

        if(this.getSize() % 2 == 0){
            return ((((double)this.minHeap.peek()) + ((double) this.maxHeap.peek())) / 2.0);
        }else{
            return (double) this.minHeap.peek();
        }
    }

    public int getSize(){
        if (this.maxHeap.size() > (this.minHeap.size() + 1)) {
            this.minHeap.offer(this.maxHeap.poll());
        }else if(this.minHeap.size() > (this.maxHeap.size() + 1)){
            this.maxHeap.offer(this.minHeap.poll());
        }
        return this.maxHeap.size() + this.minHeap.size();
    }

    public static void main(String[] args) {
        String[][] operations = {
//                {"MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"},
//                {"MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"},
//                {"MedianFinder", "addNum", "addNum", "findMedian"},
                {"MedianFinder","addNum","addNum","addNum","addNum","findMedian"}
        };

        int[][] nums = {
//                {0, 10, 20, 0, 15, 0},
//                {0, 3, 5, 0, 7, 0},
//                {0, 3, 5},
                {0,10,20,30,40,0}
        };
        MedianFinder medianFinder = null;

        for(int i = 0; i < operations.length; i++){
            for(int j = 0; j < operations[i].length; j++) {
                if (operations[i][j].equals("MedianFinder")) {
                    System.out.println("instantiation");
                    medianFinder = new MedianFinder();
                } else if (operations[i][j].equals("addNum")) {
                    System.out.println("adding: " + nums[i][j]);
                    medianFinder.addNum(nums[i][j]);
                } else if (operations[i][j].equals("findMedian")) {
                    System.out.println("find Median");
                    System.out.println("Median is: " + medianFinder.findMedian());
                }
            }
        }
    }
}
