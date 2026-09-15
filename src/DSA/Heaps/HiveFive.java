package Heaps;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HiveFive {

    public static void main(String[] args) {
        int[][][] studentScores = {
                {
                        {1,91},
                        {1,92},
                        {2,93},
                        {2,97},
                        {1,60},
                        {2,77},
                        {1,65},
                        {1,87},
                        {1,100},
                        {2,100},
                        {2,76}
                },
                {
                        {1,100},
                        {7,100},
                        {1,100},
                        {7,100},
                        {1,100},
                        {7,100},
                        {1,100},
                        {7,100},
                        {1,100},
                        {7,100}
                }
        };

        for(int i = 0 ; i < studentScores.length; i++){
            System.out.println("The average of top 5 scores for all the students: ");
            for(int[] studentScore : highFive(studentScores[i])){
                System.out.println(Arrays.toString(studentScore));
            }
        }
    }

    public static int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<int[]>> studentScoreMaxHeapMap = new TreeMap<>();
        PriorityQueue<int[]> studentScoreMaxHeap;

        for(int i = 0 ; i < items.length; i++){
            if(studentScoreMaxHeapMap.containsKey(items[i][0])){
                studentScoreMaxHeap = studentScoreMaxHeapMap.get(items[i][0]);
                studentScoreMaxHeap.offer(new int[]{items[i][0], items[i][1]});
                studentScoreMaxHeapMap.put(items[i][0], studentScoreMaxHeap);
            }else{
                studentScoreMaxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b[1], a[1]));
                studentScoreMaxHeap.offer(new int[]{items[i][0], items[i][1]});
                studentScoreMaxHeapMap.put(items[i][0], studentScoreMaxHeap);
            }
        }


        int count = 5, totalScore = 0, index = 0;
        int[][] studentsAverageScores = new int[studentScoreMaxHeapMap.size()][];

        for(int studentId : studentScoreMaxHeapMap.keySet()){
            studentScoreMaxHeap = studentScoreMaxHeapMap.get(studentId);

            while(count > 0){
                totalScore += studentScoreMaxHeap.poll()[1];
                count--;
            }
            studentsAverageScores[index] = new int[]{studentId, (totalScore / 5)};
            totalScore = 0;
            count = 5;
            index++;
        }

        return studentsAverageScores;
    }
}
