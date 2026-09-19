package Heaps;

import java.util.*;

public class TopKFrequentElementsInAStream {

    public static void main(String[] args) {
        int[][] adsIdList = {
                {1,2,1,3,2,1,4,2,3,1},
                {5,5,5,3,3}
        };
        int[] k = {2,1}; // 1
        int[] windowSizeList = {5,3}; // 3

        for(int i = 0; i < windowSizeList.length; i++){
            for(List<Integer> frequentAdsInWindow : topKFrequentAds(adsIdList[i], k[i], windowSizeList[i])) {
                System.out.println(frequentAdsInWindow);
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> topKFrequentAds(int[] ads, int k, int w) {

        PriorityQueue<int[]> frequentAdsMaxHeap;
        int[] nums;
        List<List<Integer>> frequentAdsList = new ArrayList<>();
        List<Integer> result;

        for(int i = 0; i <= ads.length - w; i++){
            result = new ArrayList<>();
            nums = Arrays.copyOfRange(ads, i, (i + w));
            Map<Integer, Integer> adsCountMap = new HashMap<>();
            frequentAdsMaxHeap = new PriorityQueue<>((a,b) ->{
                int frequencyComparator = Integer.compare(b[1], a[1]);
                if(frequencyComparator == 0){
                    return Integer.compare(a[0],b[0]);
                }
                return frequencyComparator;
            });
            for(int j = 0; j < nums.length; j++){
                if(adsCountMap.containsKey(nums[j])){
                    adsCountMap.put(nums[j], adsCountMap.get(nums[j]) + 1);
                }else{
                    adsCountMap.put(nums[j], 1);
                }
            }

            System.out.println(adsCountMap);

            for(Map.Entry<Integer, Integer> entry : adsCountMap.entrySet()){
                frequentAdsMaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            }

            while(!frequentAdsMaxHeap.isEmpty()){
                if(result.size() < k){
                    result.add(frequentAdsMaxHeap.poll()[0]);
                }else{
                    break;
                }
            }
            frequentAdsList.add(result);
        }
        return frequentAdsList;
    }

}
