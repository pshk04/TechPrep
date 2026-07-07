package TechPrep100;

public class RottenOranges {

    public static void main(String[] args) {
        int[][] orangeGrid = {{2,2},{1,1},{0,0}};
        System.out.println("The minimum number of minutes for all the oranges to rot: "+findMinimumTimeForRangesToRot(orangeGrid));
    }

    public static int findMinimumTimeForRangesToRot(int[][] grid){
        int totalMinutes = 0;
        boolean isFresh = false;

        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 2){
                    if((j + 1) < grid[i].length && grid[i][j + 1] == 1){
                        grid[i][j + 1] = 2;
                        isFresh = true;
                    }
                    if((i + 1) < grid.length && grid[i + 1][j] == 1){
                        grid[i + 1][j] = 2;
                        isFresh = true;
                    }
                    if((j - 1) >= 0 && grid[i][j - 1] == 1){
                        grid[i][j - 1] = 2;
                        isFresh = true;
                    }
                    if((i - 1) >= 0 && grid[i - 1][j] == 1){
                        grid[i - 1][j] = 2;
                        isFresh = true;
                    }
                    if(isFresh) {
                        totalMinutes++;
                    }
                }
                isFresh = false;
            }
        }

        for(int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return totalMinutes;
    }
}
