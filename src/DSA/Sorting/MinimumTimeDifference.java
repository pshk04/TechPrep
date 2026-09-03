package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {

    public static void main(String[] args) {
        String[][] timePoints = {
                {
                        "23:59",
                        "00:00"
                }, // 1
                {
                        "00:00",
                        "23:59",
                        "00:00"
                }, // 0
                {
                        "05:31",
                        "22:08",
                        "00:35"
                }, // 147
                {
                        "12:00",
                        "00:00"
                } // 720
        };
        List<String> timePointsList;

        for(int i = 0 ; i < timePoints.length; i++){
            timePointsList = new ArrayList<>();
            for(String timePoint : timePoints[i]) {
                timePointsList.add(timePoint);
            }
//            System.out.println(findMinDifference(timePointsList));
            System.out.println(findMinDifferenceOptimized(timePointsList));
        }
    }

    public static int findMinDifferenceOptimized(List<String> timePointsList){
        List<Integer> minMinutesDiffList = new ArrayList<>();

        for(String timepoint : timePointsList){
            String[] timepointElement = timepoint.split(":");
            int hour = Integer.parseInt(timepointElement[0]);
            int minute = Integer.parseInt(timepointElement[1]);
            minMinutesDiffList.add((hour * 60) + minute);
        }
        Collections.sort(minMinutesDiffList);

        int minimumDifference = 1440 - minMinutesDiffList.get(minMinutesDiffList.size() - 1) + minMinutesDiffList.get(0);

        for(int i = 1; i < minMinutesDiffList.size(); i++){
            int minuteDifference = minMinutesDiffList.get(i) - minMinutesDiffList.get(i - 1);
            minimumDifference = Math.min(minimumDifference, minuteDifference);
        }
        return minimumDifference;
    }

    public static int findMinDifference(List<String> timePoints) {
        List<int[]> timePointsIntegerList = new ArrayList<>();
        List<Integer> minMinutesDiffList = new ArrayList<>();

        for (String timepoint : timePoints) {
            int hour = Integer.parseInt(timepoint.split(":")[0]);
            int minute = Integer.parseInt(timepoint.split(":")[1]);
            timePointsIntegerList.add(new int[]{hour, minute});
        }

        Collections.sort(timePointsIntegerList, (a, b) -> {
            int priorityCompare = Integer.compare(b[0], a[0]);
            if (priorityCompare == 0) {
                return Integer.compare(a[1], b[1]);
            }
            return priorityCompare;
        });

        for(int[] timepoint : timePointsIntegerList) {
            System.out.println(Arrays.toString(timepoint));
        }

        int currentHour = 0, currentMinutes = 0, forwardMinutesTo24Hrs = 0, backwardMinutesTo24Hrs = 0;
        List<Integer> before24HrsTimpointsList = new ArrayList<>();
        List<Integer> after24HrsTimpointsList = new ArrayList<>();

        for (int i = 0; i < timePointsIntegerList.size(); i++) {
            currentHour = timePointsIntegerList.get(i)[0];
            currentMinutes = timePointsIntegerList.get(i)[1];
            forwardMinutesTo24Hrs = (currentHour - 0) * 60 + currentMinutes;

            if(currentMinutes > 0){
                backwardMinutesTo24Hrs = (24 - currentHour - 1) * 60 + (60 - currentMinutes);
            }else{
                backwardMinutesTo24Hrs = (currentHour) * 60;
            }

            if(forwardMinutesTo24Hrs < backwardMinutesTo24Hrs){
                after24HrsTimpointsList.add(forwardMinutesTo24Hrs);
            }else{
                before24HrsTimpointsList.add(backwardMinutesTo24Hrs);
            }
        }

        for(int before24HrsMin : before24HrsTimpointsList){
            if(after24HrsTimpointsList.size() > 0) {
                for (int after24HrsMin : after24HrsTimpointsList) {
                    minMinutesDiffList.add(before24HrsMin + after24HrsMin);
                }
            }
        }
        if(after24HrsTimpointsList.size() > 0) {
            Collections.sort(after24HrsTimpointsList);
        }

        if(before24HrsTimpointsList.size() > 0) {
            Collections.sort(before24HrsTimpointsList);
        }

        for(int k = 0; k < after24HrsTimpointsList.size() - 1; k++){
            minMinutesDiffList.add(Math.abs(after24HrsTimpointsList.get(k) - after24HrsTimpointsList.get(k + 1)));
        }

        for(int k = 0; k < before24HrsTimpointsList.size() - 1; k++){
            minMinutesDiffList.add(Math.abs(before24HrsTimpointsList.get(k) - before24HrsTimpointsList.get(k + 1)));
        }

        return Collections.min(minMinutesDiffList);
    }
}
