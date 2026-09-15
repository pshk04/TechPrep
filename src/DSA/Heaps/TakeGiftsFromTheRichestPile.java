package Heaps;

import java.util.PriorityQueue;

public class TakeGiftsFromTheRichestPile {

    public static void main(String[] args) {
        int[][] gifts = {
                {25,64,9,4,100},
                {1,1,1,1}
        };

        int[] k = {4,4};

        for(int i = 0 ; i < gifts.length; i++){
            System.out.println("total gifts after "+k[i]+" seconds: "+pickGifts(gifts[i], k[i]));
        }
    }

    public static long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> giftsMaxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        int totalRemainingGifts = 0;
        double maxGifts = 0;

        for(int i = 0; i < gifts.length; i++){
            giftsMaxHeap.offer(gifts[i]);
        }

        while(k > 0){
            maxGifts = (double) giftsMaxHeap.poll();
            maxGifts = Math.floor(Math.sqrt(maxGifts));
            giftsMaxHeap.offer((int)maxGifts);
            k--;
        }

        while(!giftsMaxHeap.isEmpty()){
            totalRemainingGifts += giftsMaxHeap.poll();
        }

        return totalRemainingGifts;
    }
}
