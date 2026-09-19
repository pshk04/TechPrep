package Heaps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {

    public static void main(String[] args) {
        int[][] qualityArray = {
                {10,20,5},
                {3,1,10,10,1}
        };

        int[][] wageArray = {
                {70,50,30},
                {4,8,2,2,7}
        };

        int[] k = {2,3};

        for(int i = 0 ; i < qualityArray.length; i++){
            double minimumWorkerGroupWage = mincostToHireWorkers(qualityArray[i], wageArray[i], k[i]);

            System.out.println("minimum cost to hire: "+minimumWorkerGroupWage);
        }
    }

    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double wageToQualityRatio = 0.0d, currentWorkerRatio = 0.0d, combinedWageSum = 0.0d, currentWage = 0.0d;
        double currentQuality = 0.0d, otherWorkerQuality = 0.0d, minimumWage = 0.0d;
        PriorityQueue<Double> workersWageMinHeap = new PriorityQueue<>();
        List<Double> workerWageToQualityList;
        List<List<Double>> result = new ArrayList<>();

        for(int i = 0 ; i < wage.length; i++){
            currentWage = (double) wage[i];
            otherWorkerQuality = (double) quality[i];
            wageToQualityRatio = (double) (currentWage / otherWorkerQuality);
            workerWageToQualityList = new ArrayList<>();
            workerWageToQualityList.add(currentWage);

            for(int j = 0; j < quality.length; j++){
                if(i != j){
                    currentWage = (double) wage[j];
                    currentQuality = (double) quality[j];
                    currentWorkerRatio = (double)(wageToQualityRatio * currentQuality);
                    if(currentWorkerRatio >= currentWage){
                        workerWageToQualityList.add(currentWorkerRatio);
                    }
                }
            }
            findCombinations(workerWageToQualityList, k, 0, new ArrayList<>(), result);
            for(List<Double> combinationList : result){
                for(double num : combinationList) {
                    combinedWageSum += num;
                }
                workersWageMinHeap.offer(combinedWageSum);
                combinedWageSum = 0.0d;
            }
        }

        minimumWage = workersWageMinHeap.poll();
        return minimumWage;
    }

    public static void findCombinations(List<Double> list, int k, int start,
                                         List<Double> current, List<List<Double>> results) {
        // Base case: If the combination is the required size, save it
        if (current.size() == k) {
            results.add(new ArrayList<>(current));
            return;
        }

        // Loop through the remaining elements sequentially instead of nesting
        for (int i = start; i < list.size(); i++) {
            current.add(list.get(i));                    // Choose the element
            findCombinations(list, k, i + 1, current, results); // Recurse to next element
            current.remove(current.size() - 1);          // Backtrack (undo choice)
        }
    }
}
