public class BestValueForMoney {
    public static void main(String[] args) {
        int[] prices = {10,10,20,30,40};
        int[] ratings = {50,60,120,90,200};
        int budget = 35;

        System.out.println("the maximum value-for-money is: "+findMaxValueForMoney(prices, ratings, budget));
    }

    public static int findMaxValueForMoney(int[] prices, int[] ratings, int budget){
        int maxValueForMoney = Integer.MIN_VALUE;
        int index = -1, currentValueForMoney = 0;

        for(int i = 0; i < ratings.length; i++){
            if(prices[i] <= budget){
                currentValueForMoney = ratings[i] / prices[i];
                if(currentValueForMoney > maxValueForMoney) {
                    maxValueForMoney = currentValueForMoney;
                    index = i;
                }
            }
        }
        return index;
    }
}
