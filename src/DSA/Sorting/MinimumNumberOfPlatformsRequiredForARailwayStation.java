package Sorting;

import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MinimumNumberOfPlatformsRequiredForARailwayStation {

    public static void main(String[] args) {
        int[][] arrivals = {
                {900,940,950,1100,1500,1800},
                {900,1100,1235}
        };
        int[][] departures = {
                {910,1200,1120,1130,1900,2000},
                {1000,1200,1240}
        };

        for(int i = 0 ; i < arrivals.length; i++){
            System.out.println("Total Number of platforms required: "+minPlatforms(arrivals[i], departures[i]));
        }
    }

    public static int minPlatforms(int[] arrival, int[] departure) {
        Map<Integer, Integer> platformTimeMap = new TreeMap<>();
        int previousDeparture = departure[0], currentArrival = 0, currentDeparture = 0, currentPlatform = 1;
        platformTimeMap.put(currentPlatform, previousDeparture);
        System.out.println(platformTimeMap);

        for(int i = 1; i < arrival.length; i++){
            currentArrival = arrival[i];
            currentDeparture = departure[i];
            updatePlatform(currentArrival, currentDeparture, platformTimeMap);
            System.out.println(platformTimeMap);
        }

        return platformTimeMap.size();
    }

    public static void updatePlatform(int currentArrival, int currentDeparture, Map<Integer, Integer> platformTimeMap){
        boolean newPlatformAssigned = false;
        int platform = 0, platformOccupiedTime = 0;

        for(Map.Entry<Integer, Integer> entry : platformTimeMap.entrySet()){
            platformOccupiedTime = entry.getValue();
            platform = entry.getKey();

            if(currentArrival > platformOccupiedTime && !newPlatformAssigned){
                platformTimeMap.put(platform, currentDeparture);
                newPlatformAssigned = true;
            }
            if(newPlatformAssigned){
                break;
            }
        }
        if(!newPlatformAssigned){
            platform++;
            platformTimeMap.put(platform, currentDeparture);
        }
    }
}
