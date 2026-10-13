package Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem {

    public static void main(String[] args) {
        int[][][] skylineList = {
                {
                        {2,9,10},
                        {3,7,15},
                        {5,12,12},
                        {15,20,10},
                        {19,24,8}
                },
                {
                        {0,2,3},
                        {2,5,3}
                },
                {
                        {0,2,4},
                        {2,5,3}
                },
                {
                        {1,10,5},
                        {2,6,10},
                        {3,8,7}
                },
                {
                        {1,2,1},
                        {1,2,2},
                        {1,2,3}
                },
                {
                        {1,5,10},
                        {2,7,15},
                        {3,9,12},
                        {4,11,8}
                },
                {
                        {0,3,3},
                        {1,5,3},
                        {2,4,3},
                        {3,7,3}
                },
                {
                        {1,100,5},
                        {10,20,15},
                        {30,40,15},
                        {50,60,20},
                        {70,80,10}
                }
        };

        for(int i = 0 ; i < skylineList.length; i++){
            for(List<Integer> building : getSkyline(skylineList[i])){
                System.out.print(building+",");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> buildingskylinePointsListList = new ArrayList<>();
        List<Integer> skylinePointList;
        Arrays.sort(buildings, (a,b) -> {
            int startingPointComparator = Integer.compare(a[0], b[0]);
            if(startingPointComparator == 0){
                return Integer.compare(b[2], a[2]);
            }
            return startingPointComparator;
        });
        PriorityQueue<int[]> skylineMinHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        int currentStartingPoint = 0, previousStartingPoint = 0, currentEndingPoint = 0, previousEndoingPoint = 0, maxEndingPoint = 0;
        int currentHeight = 0, previousHeight = 0, maxHeight = Integer.MIN_VALUE, maxWidth = Integer.MIN_VALUE, maxWidthsMaxHeight = 0;
        int[] currentPoint;

        for(int[] skyline : buildings){
            currentStartingPoint = skyline[0];
            currentEndingPoint = skyline[1];
            currentHeight = skyline[2];
            if(skylineMinHeap.size() == 0){
                skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
            }else{
                if(currentStartingPoint >= previousStartingPoint && currentStartingPoint < previousEndoingPoint){
                    if(currentEndingPoint <= maxWidth) {
                        if (currentHeight < previousHeight && currentEndingPoint > previousEndoingPoint) {
                            skylineMinHeap.offer(new int[]{previousEndoingPoint, currentHeight});
                        }else if (currentHeight > maxWidthsMaxHeight) {
                            skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
                        }
                    }else{
                        if(currentHeight > previousHeight){
                            skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
                        }else if(currentHeight < previousHeight){
                            skylineMinHeap.offer(new int[]{previousEndoingPoint, currentHeight});
                        }
                    }
                } else if (currentStartingPoint >= previousStartingPoint && currentStartingPoint <= previousEndoingPoint) {
                    if(currentEndingPoint <= maxWidth) {
                        if (currentHeight < maxWidthsMaxHeight) {
                            skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
                        }
                    }
                    else{
                        if (currentHeight < maxWidthsMaxHeight) {
                            skylineMinHeap.offer(new int[]{previousEndoingPoint, currentHeight});
                        }
                    }
                } else{
                    if(currentStartingPoint > maxWidth){
                        maxWidth = currentEndingPoint;
                        maxWidthsMaxHeight = currentHeight;
                        skylineMinHeap.offer(new int[]{previousEndoingPoint, 0});
                        skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
                    }else {
                        skylineMinHeap.offer(new int[]{previousEndoingPoint, maxWidthsMaxHeight});
                        skylineMinHeap.offer(new int[]{currentStartingPoint, currentHeight});
                    }
                }
            }
            previousStartingPoint = currentStartingPoint;
            previousEndoingPoint = currentEndingPoint;
            previousHeight = currentHeight;

            if(currentEndingPoint >  maxWidth){
                maxWidth = currentEndingPoint;
                maxWidthsMaxHeight = currentHeight;
            }
        }
        if(maxWidth > currentEndingPoint){
            skylineMinHeap.offer(new int[]{currentEndingPoint, maxWidthsMaxHeight});
        }
        skylineMinHeap.offer(new int[]{maxWidth, 0});

        while(!skylineMinHeap.isEmpty()){
            skylinePointList = new ArrayList<>();
            currentPoint = skylineMinHeap.poll();
            skylinePointList.add(currentPoint[0]);
            skylinePointList.add(currentPoint[1]);
            buildingskylinePointsListList.add(skylinePointList);
        }

        return buildingskylinePointsListList;
    }
}
