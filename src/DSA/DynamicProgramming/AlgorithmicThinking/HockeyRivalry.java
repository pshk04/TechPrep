package DynamicProgramming.AlgorithmicThinking;

public class HockeyRivalry {
    public static long totalCalls;

    public static void main(String[] args) {
        char[][] outcomes_gesse = {
                {'O','W','W','W'},
                {'O','W','W'},
                {'O','W','L','W','W'}
        };

        char[][] outcomes_hawks = {
                {'O','L','L','L'},
                {'O','L','L'},
                {'O','W','L','L','L'}
        };

        int[][] goals_gesse = {
                {0,2,5,1},
                {0,6,2},
                {0,3,4,1,8}
        };

        int[][] goals_hawks = {
                {0,4,7,8},
                {0,8,1},
                {0,5,1,2,3}
        };
        totalCalls = 0;

//        for(int i = 0 ; i < outcomes_gesse.length; i++){
//            System.out.println("The maximum goals scored during the rivalry games are: "+
//                    findMaxGoals(outcomes_gesse[i], outcomes_hawks[i], goals_gesse[i], goals_hawks[i],
//                    outcomes_gesse[i].length - 1,
//                    outcomes_hawks[i].length - 1)
//            );
//            System.out.println("The total calls: "+totalCalls);
//            totalCalls = 0;
//        }
//
//
//
//        totalCalls = 0;
//        System.out.println("Memoization...");
//
//        for(int index = 0 ; index < outcomes_gesse.length; index++){
//            int[][] memos = new int[outcomes_gesse[index].length + 1][outcomes_hawks[index].length + 1];
//
//            for(int i = 0 ; i <= outcomes_gesse[index].length; i++){
//                for(int j = 0; j <= outcomes_hawks[index].length; j++){
//                    memos[i][j] = -1;
//                }
//            }
//            System.out.println("The maximum goals scored during the rivalry games are: "+
//                    findMaxGoalsWithMemoization(outcomes_gesse[index], outcomes_hawks[index], goals_gesse[index], goals_hawks[index],
//                            outcomes_gesse[index].length - 1,
//                            outcomes_hawks[index].length - 1,
//                            memos
//                    )
//            );
//            System.out.println("The total calls: "+totalCalls);
//            totalCalls = 0;
//        }

        totalCalls = 0;
        System.out.println("DP...");


        for(int index = 0 ; index < outcomes_gesse.length; index++){

            int[][] dp = new int[outcomes_gesse[index].length + 1][outcomes_hawks[index].length + 1];

            for(int i = 0 ; i <= outcomes_gesse[index].length; i++){
                for(int j = 0; j <= outcomes_hawks[index].length; j++){
                    if(i == 0 || j == 0){
                        dp[i][j] = 0;
                    }
                }
            }
//            System.out.println("The maximum goals scored during the rivalry games with DP: "+
//                    findMaxGoalsWithDP(outcomes_gesse[index], outcomes_hawks[index], goals_gesse[index], goals_hawks[index],
//                            outcomes_gesse[index].length - 1,
//                            outcomes_hawks[index].length - 1,
//                            dp
//                    )
//            );
//            System.out.println("The total calls: "+totalCalls);
//            totalCalls = 0;

            System.out.println("The maximum goals scored during the rivalry games DP & Space Optimized: "+
                    findMaxGoalsWithDPSpaceOptimized(outcomes_gesse[index], outcomes_hawks[index], goals_gesse[index], goals_hawks[index],
                            outcomes_gesse[index].length - 1
                    )
            );
//            System.out.println("The total calls: "+totalCalls);
//            totalCalls = 0;
        }
    }

    public static int findMaxGoals(char[] gesse, char[] hawks, int[] gesse_goals, int[] hawks_goals, int totalGesseGames, int totalHawksGames){

        int first, second, third, fourth = 0;
        totalCalls++;

        if(totalGesseGames == 0 || totalHawksGames == 0){
            return 0;
        }


        if(((gesse[totalGesseGames] == 'W' && hawks[totalHawksGames] == 'L')
                && (gesse_goals[totalGesseGames] > hawks_goals[totalHawksGames]))
        ||
                ((gesse[totalGesseGames] == 'L' && hawks[totalHawksGames] == 'W') && (gesse_goals[totalGesseGames] < hawks_goals[totalHawksGames]))
        ){
            first = findMaxGoals(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames - 1)
            + gesse_goals[totalGesseGames] + hawks_goals[totalHawksGames];
        }else{
            first = 0;
        }

        second = findMaxGoals(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames - 1);
        third = findMaxGoals(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames);
        fourth = findMaxGoals(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames, totalHawksGames - 1);

        return Math.max(first, Math.max(second, Math.max(third, fourth)));
    }

    public static int findMaxGoalsWithMemoization(char[] gesse, char[] hawks, int[] gesse_goals, int[] hawks_goals, int totalGesseGames,
                                                  int totalHawksGames, int[][] memos){
        int first, second, third, fourth = 0;
        totalCalls++;

        if(memos[totalGesseGames][totalHawksGames] != -1){
            return memos[totalGesseGames][totalHawksGames];
        }

        if(totalGesseGames == 0 || totalHawksGames == 0){
            memos[totalGesseGames][totalHawksGames] = 0;
            return 0;
        }

        if(((gesse[totalGesseGames] == 'W' && hawks[totalHawksGames] == 'L')
                && (gesse_goals[totalGesseGames] > hawks_goals[totalHawksGames]))
                ||
                ((gesse[totalGesseGames] == 'L' && hawks[totalHawksGames] == 'W') && (gesse_goals[totalGesseGames] < hawks_goals[totalHawksGames]))
        ){
            first = findMaxGoalsWithMemoization(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames - 1, memos)
                    + gesse_goals[totalGesseGames] + hawks_goals[totalHawksGames];
        }else{
            first = 0;
        }

        second = findMaxGoalsWithMemoization(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames - 1, memos);
        third = findMaxGoalsWithMemoization(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames - 1, totalHawksGames, memos);
        fourth = findMaxGoalsWithMemoization(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames, totalHawksGames - 1, memos);

        memos[totalGesseGames][totalHawksGames] = Math.max(first, Math.max(second, Math.max(third, fourth)));
        return memos[totalGesseGames][totalHawksGames];
    }

    public static int findMaxGoalsWithDP(char[] gesse, char[] hawks, int[] gesse_goals, int[] hawks_goals, int totalGesseGames,
                                                  int totalHawksGames, int[][] dp){

        int first, second, third, fourth = 0;

        if(totalGesseGames == 0 || totalHawksGames == 0){
            dp[totalGesseGames][totalHawksGames] = 0;
            return 0;
        }

        for(int i = 1; i < totalGesseGames + 1; i++){
            for(int j = 1; j < totalHawksGames + 1; j++){
                if((gesse[i] == 'W' && hawks[j] == 'L' && gesse_goals[i] > hawks_goals[j]) ||
                        (gesse[i] == 'L' && hawks[j] == 'W' && gesse_goals[i] < hawks_goals[j])){
                            first = dp[i - 1][j - 1] + gesse_goals[i] + hawks_goals[j];
                }else{
                    first = 0;
                }
                second = dp[i - 1][j - 1];
                third = dp[i - 1][j];
                fourth = dp[i][j - 1];
                dp[i][j] = Math.max(first, Math.max(second, Math.max(third, fourth)));
            }
        }
        printOptimalSolution(gesse, hawks, gesse_goals, hawks_goals, totalGesseGames, totalHawksGames, dp);
        return dp[totalGesseGames][totalHawksGames];
    }

    public static void printOptimalSolution(char[] gesse, char[] hawks, int[] gesse_goals, int[] hawks_goals, int totalGesseGames,
                                            int totalHawksGames, int[][] dp){

    }

    public static int findMaxGoalsWithDPSpaceOptimized(char[] gesse, char[] hawks, int[] gesse_goals, int[] hawks_goals, int totalGesseGames){

        int first, second, third, fourth = 0;
        int[] previousRow = new int[totalGesseGames + 1];
        int[] currentRow = new int[totalGesseGames + 1];

        for(int i = 0 ; i < totalGesseGames + 1; i++){
            previousRow[i] = 0;
        }

        for(int i = 1; i < totalGesseGames + 1; i++){
            for(int j = 1; j < totalGesseGames + 1; j++){
                if((gesse[i] == 'W' && hawks[j] == 'L' && gesse_goals[i] > hawks_goals[j]) ||
                        (gesse[i] == 'L' && hawks[j] == 'W' && gesse_goals[i] < hawks_goals[j])){
                    first = previousRow[j - 1] + gesse_goals[i] + hawks_goals[j];
                }else{
                    first = 0;
                }
                second = previousRow[j - 1];
                third = previousRow[j];
                fourth = currentRow[j - 1];
                currentRow[j] = Math.max(first, Math.max(second, Math.max(third, fourth)));
            }
            for(int k = 0; k < totalGesseGames + 1; k++){
                previousRow[k] = currentRow[k];
            }
        }
        return currentRow[totalGesseGames];
    }
}
