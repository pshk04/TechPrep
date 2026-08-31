package Sorting;

import LLD.BankingApplication2.AccountTransfer;

import java.util.*;

public class CampusBikeAssignment {

    public static void main() {
        int[][][] workers = {
                {
                        {0, 0}, {2, 1}
                },
                {
                        {0,0}, {1,1}, {2,0}
                },
                {
                        {0,0},{1,0},{2,0}
                }
        };

        int[][][] bikes = {
                {
                        {1, 2}, {3, 3}
                },
                {
                        {1,0}, {2,2}, {2,1}
                },
                {
                        {3,0},{4,0},{5,0} // {2,1,0}
                }
        };

        for(int i = 0 ; i < workers.length; i++){
            System.out.println("The bikes at the shortest distance are: "+ Arrays.toString(assignBikes(workers[i], bikes[i])));
        }
    }

    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] assignedBikes = new int[workers.length];
        PriorityQueue<int[]> distanceMinHeap = populateDistance(workers, bikes);
        List<Integer> alreadyAssignedBikesList = new ArrayList<>();
        List<Integer> alreadyAssignedWorkersList = new ArrayList<>();
        int count = bikes.length;
        int currentBike = 0, currentWorker = 0;
        int[] measure;

        while(!distanceMinHeap.isEmpty() || count > 0){
            measure = distanceMinHeap.poll();
            currentWorker = measure[0];
            currentBike =  measure[2];

            if(!alreadyAssignedBikesList.contains(currentBike) && !alreadyAssignedWorkersList.contains(currentWorker)){
                assignedBikes[currentWorker] = currentBike;
                alreadyAssignedBikesList.add(currentBike);
                alreadyAssignedWorkersList.add(currentWorker);
                count--;
            }
            if(distanceMinHeap.size() == 0){
                break;
            }
        }

        return assignedBikes;
    }

    public static PriorityQueue<int[]> populateDistance(int[][] workers, int[][] bikes){

        PriorityQueue<int[]> distanceMinHeap = new PriorityQueue<>((a, b) -> {
            int priorityCompare = Integer.compare(a[1], b[1]);
            if (priorityCompare == 0) {
                return Integer.compare(a[0], b[0]);
            }
            return priorityCompare;
        });

        int currentDistance = 0;

        for(int i = 0 ; i < workers.length; i++){
            int[] currentWorker = workers[i];
            for(int j = 0; j < bikes.length; j++){
                int[] currentBike = bikes[j];
                currentDistance = measureDistance(currentWorker[0],currentBike[0], currentWorker[1], currentBike[1]);
                distanceMinHeap.offer(new int[]{i,currentDistance,j});
            }
        }
        return distanceMinHeap;
    }

    public static int measureDistance(int x1, int x2, int y1, int y2){

        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }
}
