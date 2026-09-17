package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][][] pointsList = {
                {
                        {1,3},
                        {-2,2}
                },

                {
                        {3,3},
                        {5,-1},
                        {-2,4}
                },
                {
                        {-5,4},
                        {-6,-5},
                        {4,6}
                }
        };
        int[] k = {1,2,2};

        for(int i = 0 ; i < pointsList.length; i++){
            for(int[] closestPoint : kClosest(pointsList[i], k[i])){
                System.out.println(Arrays.toString(closestPoint));
            }
            System.out.println();
        }
    }

    public static int[][] kClosest(int[][] points, int k) {
        int[][] mostFrequentPoints = new int[k][0];
        Map<String, Integer> pointsCountMap = new TreeMap<>();

        PriorityQueue<int[]> tokensContMaxHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(distanceFromOrigin(a[0],a[1]), distanceFromOrigin(b[0],b[1]));
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
