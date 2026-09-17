package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class FrequencyAndDistanceTopKPoints {

    public static void main(String[] args) {
        int[][][] coordinatesList = {
                {
                        {1,2},
                        {3,4},
                        {1,2},
                        {1,2},
                        {3,4},
                        {0,1}
                },

                {
                        {1,0},
                        {0,1},
                        {1,0},
                        {0,1},
                        {-1,0}
                }
        };

        int[] k = {2,2};

        for(int i = 0 ; i < coordinatesList.length; i++){
            System.out.println(k[i]+" most frequent points are: ");
            for(int[] frequentPoint : topKPoints(coordinatesList[i], k[i])){
                System.out.println(Arrays.toString(frequentPoint));
            }
        }
    }

    public static int[][] topKPoints(int[][] points, int k) {
        int[][] mostFrequentPoints = new int[k][0];
        Map<String, Integer> pointsCountMap = new TreeMap<>();
        PriorityQueue<int[]> tokensContMaxHeap = new PriorityQueue<>((a, b) -> {
            int frequencyCountComparator = Integer.compare(b[2], a[2]);

            if(frequencyCountComparator == 0){
                int distanceFromOriginComparator =  Integer.compare(distanceFromOrigin(a[0],a[1]), distanceFromOrigin(b[0],b[1]));
                if(distanceFromOriginComparator == 0){
                    return Integer.compare(a[0], b[0]);
                }
                return distanceFromOriginComparator;
            }
            return frequencyCountComparator;
        });

        for(int[] point : points){
            String pointString = Arrays.toString(point);

            if(pointsCountMap.containsKey(pointString)){
                pointsCountMap.put(pointString, pointsCountMap.get(pointString) + 1);
            }else{
                pointsCountMap.put(pointString, 1);
            }
        }

        for(Map.Entry<String, Integer> pointEntry : pointsCountMap.entrySet()){
            String coordinate = pointEntry.getKey();

            int x_coordinate = Integer.parseInt(coordinate.split(", ")[0].replace("[", ""));
            int y_coordinate = Integer.parseInt(coordinate.split(", ")[1].replace("]", ""));
            tokensContMaxHeap.offer(new int[]{x_coordinate, y_coordinate, pointEntry.getValue()});
        }

        int index = 0;
        int[] coordinate;

        while(index < k){
            coordinate = tokensContMaxHeap.poll();
            mostFrequentPoints[index] = new int[]{coordinate[0], coordinate[1]};
            index++;
        }

        return mostFrequentPoints;
    }

    public static int distanceFromOrigin(int x1, int y1){
        double x_coordinate = (double) x1;
        double y_coordinate = (double) y1;
        double squaredX = (double)((x_coordinate - 0) * (x_coordinate - 0));
        double squaredY = (double)((y_coordinate - 0) * (y_coordinate - 0));

        double distance = Math.sqrt((double)(squaredX + squaredY));

        return (int)distance;
    }
}
