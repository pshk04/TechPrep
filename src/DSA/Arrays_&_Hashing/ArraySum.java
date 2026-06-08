public class ArraySum {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};

        System.out.println("The sum of all elements is: "+findSum(nums));
    }

    public static int findSum(int[] nums){
        int i = 0, j = nums.length - 1, totalSum = 0;

        if(i == j){
            return nums[i];
        }
        while(i < j){
            totalSum += nums[i] + nums[j];
            i++;
            j--;
            if(i == j){
                totalSum += nums[i];
                break;
            }
        }
        return totalSum;
    }
}
