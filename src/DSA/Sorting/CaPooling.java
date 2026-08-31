package Sorting;

import java.util.Arrays;

public class CaPooling {

    public static void main() {
        int[][][] tripsList = {
                {
                        {2,1,5},
                        {3,3,7}
                },
                {
                        {2,1,5},
                        {3,3,7}
                },
                {
                        {2,1,5},
                        {3,5,7}
                }
        };

        int[] capacities = {4,5,3};

        for(int i = 0 ; i < capacities.length; i++){
            System.out.println("It is "+((carPooling(tripsList[i],capacities[i])) ? "POSSIBLE" : "NOT POSSIBLE")+" to include all the passengers in the trip");
        }
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        boolean isPossibleToInclude = true;
        Arrays.sort(trips, (a, b) ->Integer.compare(a[1], b[1]));
        int previousStart = 0, previousEnd = 0, previousCapacity = 0;

        if(trips.length == 1){
            if(capacity < trips[0][0]){
                return false;
            }else{
                return true;
            }
        }

        for(int i = 0 ; i < trips.length; i++){
            int numberOfPassengers = trips[i][0];
            int currentStart = trips[i][1];
            int currentEnd = trips[i][2];

            if(i == 0 && numberOfPassengers <= capacity){
                previousStart = currentStart;
                previousEnd = currentEnd;
                previousCapacity = numberOfPassengers;
            }else{
                if(currentStart >= previousStart && currentStart < previousEnd){
                    if((numberOfPassengers + previousCapacity) > capacity){
                        isPossibleToInclude = false;
                        break;
                    }
                }
            }
        }

        return isPossibleToInclude;
    }
}
