package Sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RewardTopKStudents {

    public static void main(String[] args) {
        String[][] positiveFeedbacks = {
                {
                        "smart",
                        "brilliant",
                        "studious"
                },
                {
                        "smart",
                        "brilliant",
                        "studious"
                },
                {
                        "a",
                        "b"
                }
        };
        String[][] negativeFeedbacks = {
                {
                        "not"
                },
                {
                        "not"
                },
                {
                        "c"
                }
        };

        String[][] reports = {
                {
                        "this student is studious",
                        "the student is smart"
                },
                {
                        "this student is not studious",
                        "the student is smart"
                },
                {
                        "a b c",
                        "a a",
                        "b b b",
                        "c c c"
                }
        };
        int[][] studentIds = {
                {1,2},
                {1,2},
                {4,3,2,1}
        };

        int[] k = {2,2,3};

        for(int i = 0 ; i < k.length; i++){
            System.out.println("Top students are (in-order): "+
                    Arrays.toString(topStudents(positiveFeedbacks[i], negativeFeedbacks[i], reports[i], studentIds[i], k[i])));
        }
    }

    public static int[] topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        int totalScore = 0, index = 0;
        int[] topStudents = new int[k];

        PriorityQueue<int[]> studentsScoremaxHeap = new PriorityQueue<>((a,b) -> {
            int p1 = Integer.compare(b[1], a[1]);
            if (p1 == 0) {
                return Integer.compare(a[0], b[0]);
            }
            return p1;
        });
        Map<Integer, Integer> studentScoreMap = new HashMap<>();

        for(int i = 0 ; i < report.length; i++){
            String studentReport = report[i];
            totalScore = 0;

            for(String feedback : positive_feedback){
                for(String reportWord : studentReport.split(" ")) {
                    if (reportWord.indexOf(feedback) >= 0) {
                        totalScore += 3;
                    }
                }
            }
            for(String feedback : negative_feedback){
                for(String reportWord : studentReport.split(" ")) {
                    if (reportWord.indexOf(feedback) >= 0) {
                        totalScore -= 1;
                    }
                }
            }
            studentScoreMap.put(student_id[i], totalScore);
        }

        System.out.println("studentScoreMap: "+studentScoreMap);

        for(Map.Entry<Integer, Integer> entry : studentScoreMap.entrySet()){
            studentsScoremaxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        while(k > 0){
            topStudents[index] = studentsScoremaxHeap.poll()[0];
            index++;
            k--;
        }

        return topStudents;
    }
}
