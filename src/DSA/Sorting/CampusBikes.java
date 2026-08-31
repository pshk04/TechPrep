package Sorting;

import java.util.*;

public class CampusBikes {
    public static Map<String, String> shortestDistanceMap;

    public static void main() {
        int[][][] workers = {
//                {
//                        {0, 0}, {2, 1}
//                },
//                {
//                        {0,0}, {1,1}, {2,0}
//                },
                {
                    {0,0},{1,0},{2,0}
                }
        };

        int[][][] bikes = {
//                {
//                        {1, 2}, {3, 3}
//                },
//                {
//                        {1,0}, {2,2}, {2,1}
//                },
                {
                    {3,0},{4,0},{5,0}
                }
        };

        for(int i = 0 ; i < workers.length; i++){
            System.out.println("The bikes at the shortest distance are: "+ Arrays.toString(assignBikes(workers[i], bikes[i])));
        }
    }

    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        shortestDistanceMap = new HashMap<>();
        Map<String, Map<String, Integer>> distanceMap = populateDistance(workers, bikes);
        List<String> chosenBikesList = new ArrayList<>(shortestDistanceMap.values());

        Map<String, Integer> distanceMeasuredMap;
        int[] assignedBikes = new int[workers.length];
        int minimumDistance = Integer.MAX_VALUE, bikeId = 0, currentDistance = 0;
        String occupiedBike = "", bike = "";

        for(Map.Entry<String, Map<String, Integer>> entry : distanceMap.entrySet()){
            String worker = entry.getKey();
            int workerId = Integer.parseInt(worker.charAt(1)+"");
            distanceMeasuredMap = entry.getValue();

            for(Map.Entry<String, Integer> distanceEntry : distanceMeasuredMap.entrySet()){
                bike = distanceEntry.getKey();
                currentDistance = distanceEntry.getValue();
                if(shortestDistanceMap.containsKey(worker)){
                    occupiedBike = shortestDistanceMap.get(worker);
                    bikeId = Integer.parseInt(occupiedBike.charAt(1)+"");
                    break;
                }
                if(currentDistance < minimumDistance && !chosenBikesList.contains(bike)){
                    minimumDistance = currentDistance;
                    bikeId = Integer.parseInt(bike.charAt(1)+"");
                    occupiedBike = bike;
                }
            }
            assignedBikes[workerId] = bikeId;
            chosenBikesList.add(occupiedBike);
            minimumDistance = Integer.MAX_VALUE;
        }

        return assignedBikes;
    }

    public static TreeMap<String, Map<String, Integer>> populateDistance(int[][] workers, int[][] bikes){
        Map<String, Map<String, Integer>> distanceMap = new TreeMap<>();
        Map<String, Integer> distanceMeasuredMap;
        int currentDistance = 0, minimumDistance =Integer.MAX_VALUE;
        String shortestDistancebikeId = "", workerWithShorterBikeDistance = "";
        int[] currentWorker, currentBike;

        for(int i = 0 ; i < workers.length; i++){
            String workerId = "W" + i;
            currentWorker = workers[i];
            distanceMeasuredMap = new TreeMap<>();
            for(int j = 0; j < bikes.length; j++){
                String currentBikeId = "B" + j;
                currentBike = bikes[j];
                currentDistance = measureDistance(currentWorker[0],currentBike[0], currentWorker[1], currentBike[1]);
                if(currentDistance < minimumDistance){
                    shortestDistancebikeId = currentBikeId;
                    workerWithShorterBikeDistance = workerId;
                    minimumDistance = currentDistance;
                }
                distanceMeasuredMap.put(currentBikeId, currentDistance);
            }
            distanceMap.put(workerId, distanceMeasuredMap);
        }
        shortestDistanceMap.put(workerWithShorterBikeDistance, shortestDistancebikeId);
        return (TreeMap<String, Map<String, Integer>>)distanceMap;
    }

    public static int measureDistance(int x1, int x2, int y1, int y2){

        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }
}
