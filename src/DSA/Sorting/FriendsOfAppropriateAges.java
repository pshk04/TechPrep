package Sorting;

public class FriendsOfAppropriateAges {
    public static void main() {
        int[][] numsArrays = {
                {16,16},
                {16,17,18}
        };

        for(int i = 0 ; i < numsArrays.length; i++){
            System.out.println("The target indices are located at: "+numFriendRequests(numsArrays[i]));
        }
    }

    public static int numFriendRequests(int[] ages) {
        int totalFriendRequests = 0;

        for(int i = 0 ; i < ages.length; i++){
            for(int j = 0; j < ages.length; j++){
                if(i != j){
                    if((ages[j] <= ((0.5 * ages[i]) + 7)) || (ages[j] > ages[i]) || (ages[j] > 100 && ages[i] < 100)){
                        continue;
                    }else{
                        totalFriendRequests++;
                    }
                }
            }
        }
        return totalFriendRequests;
    }
}
