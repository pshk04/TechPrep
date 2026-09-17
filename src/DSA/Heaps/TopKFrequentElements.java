package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[][][] points = {
//                {
//                        {1,2},
//                        {3,4},
//                        {1,2},
//                        {3,4},
//                        {1,2},
//                        {5,6}
//                },
//                {
//                        {1,0},
//                        {0,1},
//                        {1,0},
//                        {0,1},
//                        {2,2},
//                        {2,2}
//                },
                {
                        {-1,0},
                        {1,0},
                        {0,1},
                        {0,-1}
                }
        };
        int[] k = {4}; //2,2,

        for(int i = 0 ; i < points.length; i++){
            for(int[] closestPoint : topKFrequent(points[i], k[i])){
                System.out.println(Arrays.toString(closestPoint));
            }
            System.out.println();
        }
    }

    public static int[][] topKFrequent(int[][] points, int k) {
        int[][] result = new int[k][0];
        Map<String, Integer> frequencyMap = new TreeMap<>();
        PriorityQueue<int[]> distanceFromOriginMinHeap = new PriorityQueue<>((a,b) -> {
           int frequencyComparator = Integer.compare(b[2], a[2]);
           if(frequencyComparator == 0){
               int distanceFromOrigin =  Integer.compare(a[3], b[3]);
               if(distanceFromOrigin == 0){
                   return Integer.compare(a[0], b[0]);
               }
               return distanceFromOrigin;
           }
           return frequencyComparator;
        });

        for(int[] point : points){
            String pointString = Arrays.toString(point);
            if(frequencyMap.containsKey(pointString)){
                frequencyMap.put(pointString, frequencyMap.get(pointString) + 1);
            }else{
                frequencyMap.put(pointString, 1);
            }
        }
        System.out.println(frequencyMap);

        for(Map.Entry<String, Integer> entry : frequencyMap.entrySet()){
            int[] pointArray = convertPointStringToArray(entry.getKey());
            int distanceFromOrigin = distanceFromOrigin(pointArray[0], pointArray[1]);
            System.out.println(Arrays.toString(pointArray)+" Distance from Origin: "+distanceFromOrigin);
            distanceFromOriginMinHeap.offer(new int[]{pointArray[0], pointArray[1], entry.getValue(), distanceFromOrigin});
        }
        int index = 0;
        int[] point;

        while(!distanceFromOriginMinHeap.isEmpty()){
            if(index < k) {
                point = distanceFromOriginMinHeap.poll();
                result[index] = new int[]{point[0], point[1]};
            }else{
                break;
            }
            index++;
        }
        return result;
    }

    public static int[] convertPointStringToArray(String pointString){
        String[] pointStrArray = pointString.split(",");
        int begin = Integer.parseInt(pointStrArray[0].replace("[","").trim());
        int end = Integer.parseInt(pointStrArray[1].replace("]","").trim());

        return new int[]{begin, end};
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
