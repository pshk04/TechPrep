package TechPrep100;

public class MonotonicArray {

    public static void main(String[] args) {
        int[][] numsArray = {
                {1,2,2,3},
                {6,5,4,4},
                {3,2,4,1},
                {1,3,2}
        };
        for(int[] nums : numsArray) {
            System.out.println("The given array " + ((isMonotonic(nums)) ? "is" : "is not") + " Monotonic");
        }
    }

    public static boolean isMonotonic(int[] nums){
        boolean isIncreasing = false, isDecreasing = false;

        if(nums.length == 1){
            return true;
        }
        int i = 0, j = 1;

        while(i < j && j < nums.length){
            if(nums[i] < nums[j]){
                isIncreasing = true;
                break;
            }else if(nums[i] > nums[j]){
                isDecreasing = true;
                break;
            }else{
                i++;
                j++;
            }
        }

        i = 0;
        j = 1;

        while(i < j && j < nums.length && isIncreasing){
            if(nums[i] <= nums[j]){
                i++;
                j++;
            }else{
                return false;
            }
        }

        while(i < j && j < nums.length && isDecreasing){
            if(nums[i] >= nums[j]){
                i++;
                j++;
            }else{
                return false;
            }
        }
        return true;
    }
}
