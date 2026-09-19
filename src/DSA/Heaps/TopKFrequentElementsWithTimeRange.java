package Heaps;

import java.util.*;

public class TopKFrequentElementsWithTimeRange {

    public static void main(String[] args) {
        int[][][] logsList = {
                {
                        {1, 101, 0},
                        {2, 102, 0},
                        {3, 101, 0},
                        {4, 103, 0},
                        {5, 101, 0},
                        {6, 102, 0},
                        {7, 104, 0}
                },
                {
                        {10, 201, 0},
                        {20, 202, 0},
                        {30, 201, 0},
                        {40, 203, 0},
                        {50, 202, 0},
                        {60, 203, 0},
                        {70, 203, 0}
                },
        };
        int[] starts = {1, 20};
        int[] ends = {6, 70};
        int[] k = {2, 1};

        for (int i = 0; i < starts.length; i++) {
            System.out.println(topKFrequentAds(logsList[i], starts[i], ends[i], k[i]));
        }
    }

    public static List<Integer> topKFrequentAds(int[][] logs, int start, int end, int k) {
        Map<Integer, Integer> adsFrequencyMap = new HashMap<>();
        PriorityQueue<int[]> adsFrequencyMaxHeap = new PriorityQueue<>((a,b) ->{
           int frequencyComparator = Integer.compare(b[1], a[1]);
           if(frequencyComparator == 0){
               return Integer.compare(a[0], b[0]);
           }
           return frequencyComparator;
        });
        int adId = 0, adTimeStamp = 0;
        List<Integer> adsAsPerFrequencyList = new ArrayList<>();

        for(int[] adLog : logs){
            adTimeStamp = adLog[0];
            if(adTimeStamp >= start && adTimeStamp <= end) {
                adId = adLog[1];
                if (adsFrequencyMap.containsKey(adId)) {
                    adsFrequencyMap.put(adId, adsFrequencyMap.get(adId) + 1);
                } else {
                    adsFrequencyMap.put(adId, 1);
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : adsFrequencyMap.entrySet()){
            adsFrequencyMaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        while(!adsFrequencyMaxHeap.isEmpty()){
            if(k > 0) {
                adsAsPerFrequencyList.add(adsFrequencyMaxHeap.poll()[0]);
            }else{
                break;
            }
            k--;
        }

        return adsAsPerFrequencyList;
    }
}
