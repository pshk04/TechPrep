package TechPrep100;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeSortedArray {

    public static void main(String[] args) {
        int[][] nums1Array = {
                {1,2,3,0,0,0},
                {1,0},
                {4, 5, 6, 0, 0, 0},
                {0},
                {1,3,5,0,0},
                {0,0,0,0,0}
        };

        int[][] nums2Array = {
                {2,5,6},
                {2},
                {1,2,3},
                {1},
                {2,4},
                {1,2,3,4,5}
        };

        int[] m = {3,1,3,0,3,0};
        int[] n = {3,1,3,1,2,5};

        for(int i = 0 ; i < nums1Array.length; i++) {
//            merge(nums1Array[i],m[i], nums2Array[i], n[i]);
            mergeThroughHeap(nums1Array[i],m[i], nums2Array[i], n[i]);
//            mergeThroughHeap(nums1Array[i],3, nums2Array[i], 2);
        }
    }

    public static void mergeThroughHeap(int[] nums1, int m, int[] nums2, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a,b));

        for(int i = 0 ; i < nums1.length - n; i++){
            minHeap.offer(nums1[i]);
        }

        for(int i = 0 ; i < nums2.length; i++){
            minHeap.offer(nums2[i]);
        }

        nums1 = new int[nums1.length];

        int index = 0;
        while(!minHeap.isEmpty()){
            nums1[index] = minHeap.poll();
            index++;
        }
        System.out.println("The resulting array after the merge through min heap: " +Arrays.toString(nums1));

    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int first = 0, second = 0, third = nums1.length - nums2.length;

        while (first < nums1.length && third < nums1.length && second < nums2.length) {
            if (nums1[first] == 0) {
                for(int i = second; i < nums2.length; i++){
                    nums1[first] = nums2[i];
                    first++;
                }
                break;
            } else if ((second < nums2.length) && (nums1[first] <= nums2[second])) {
                first++;
            } else if ((second < nums2.length) && nums1[first] > nums2[second]) {
                nums1 = swap(nums1, first, third);
                third++;
                nums1[first] = nums2[second];
                first++;
                second++;
            }
        }
        Arrays.sort(nums1);
        System.out.println("The resulting array after the merge: " +Arrays.toString(nums1));
    }

    public static int[] swap(int[] nums, int f, int t){
        int temp = nums[f];
        nums[f] = nums[t];
        nums[t] = temp;

        return nums;
    }
}
