package TechPrep100;

import java.util.*;

public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid =

                {
//                        {'1','1','0','1','0'},
//                        {'1','0','0','1','0'},
//                        {'0','0','0','1','0'},
//                        {'0','0','0','0','0'}

                        {'1','1','0','0','0'},
                        {'0','1','0','0','0'},
                        {'0','0','1','0','0'},
                        {'0','0','0','1','0'}
                };

        System.out.println("The number of islands in the above grid: "+numberOfIslands(grid));

    }

    public static int numberOfIslands(char[][] grid){
        Map<String, String> leaderCoordinateMap = new HashMap<>();
        Set<String> leaderOfIslands = new HashSet<>();

        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == '1'){
                    if(i == 0 && j == 0){
                        leaderCoordinateMap.put(i+"_"+j, i+"_"+j);
                    }else{
                        if((((j - 1) >= 0) && grid[i][j - 1] == '1')){
                            if(leaderCoordinateMap.containsKey(i+"_"+(j - 1))){
                                leaderCoordinateMap.put(i+"_"+j, leaderCoordinateMap.get(i+"_"+(j - 1)));
                            }
                        }else if((((i - 1) >= 0)) && (grid[i - 1][j] == '1')){
                            if(leaderCoordinateMap.containsKey((i - 1)+"_"+j)){
                                leaderCoordinateMap.put(i+"_"+j, leaderCoordinateMap.get((i - 1)+"_"+j));
                            }
                        }else{
                            leaderCoordinateMap.put(i+"_"+j, i+"_"+j);
                        }
                    }
                }
            }
        }

        for(Map.Entry<String, String> entry : leaderCoordinateMap.entrySet()){
            String leader = entry.getKey();
            String follower = entry.getValue();
            System.out.println(leader + " : "+follower);
            leaderOfIslands.add(follower);
        }

        return leaderOfIslands.size();
    }
}
