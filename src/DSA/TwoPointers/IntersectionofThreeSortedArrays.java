import java.util.*;

public class IntersectionofThreeSortedArrays {

    public static void main(String[] args) {

        int[][] nums1Array = {
                {1,2,3,4,5},
                {197,418,523,876,1356}

        };
        int[][] nums2Array = {
                {1,2,5,7,9},
                {501,880,1593,1710,1870}
        };

        int[][] nums3Array = {
                {1,3,4,5,8},
                {521,682,1337,1395,1764}
        };

        for(int i = 0 ; i < nums1Array.length; i++) {
            System.out.println("The intersection of 3 arrays are: " + Arrays.toString(arraysIntersection(nums1Array[i], nums2Array[i], nums3Array[i])));
        }

    }

    public static int[] arraysIntersection(int[] nums1, int[] nums2, int[] nums3){

        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> commonNumsList = new ArrayList<>();

        for(int i = 0; i < nums1.length; i++){
            if(countMap.containsKey(nums1[i])){
                countMap.put(nums1[i], countMap.get(nums1[i]) + 1);
            }else{
                countMap.put(nums1[i], 1);
            }
        }

        for(int i = 0; i < nums2.length; i++){
            if(countMap.containsKey(nums2[i])){
                countMap.put(nums2[i], countMap.get(nums2[i]) + 1);
            }else{
                countMap.put(nums2[i], 1);
            }
        }

        for(int i = 0; i < nums3.length; i++){
            if(countMap.containsKey(nums3[i])){
                countMap.put(nums3[i], countMap.get(nums3[i]) + 1);
            }else{
                countMap.put(nums3[i], 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()){
            if(entry.getValue() >= 3){
                commonNumsList.add(entry.getKey());
            }
        }

        int[] result = new int[commonNumsList.size()];
        for(int i = 0 ; i < commonNumsList.size(); i++){
            result[i] = commonNumsList.get(i);
        }
        Arrays.sort(result);
        return result;

    }
}
