package Sorting;

import java.util.*;

public class NeighborhoodShuffling {

    public static void main(String[] args) {
        int[][][] blocks = {
                {
                        {3, 1, 2, 3, 1},
                        {5, 5, 4, 3},
                        {7, 8, 7, 9, 8}
                },
                {
                        {1},
                        {2,2,2},
                        {3,1,2}
                }
        };

        for(int i = 0 ; i < blocks.length; i++){
            for(List<Integer> block : shuffleNeighborhood(blocks[i])) {
                System.out.println(block);
            }
        }
    }

    public static List<List<Integer>> shuffleNeighborhood(int[][] blocks) {
        Set<Integer> blockSet;
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < blocks.length; i++){
            blockSet = new TreeSet<>();
            for(int j = 0 ;j < blocks[i].length; j++){
                blockSet.add(blocks[i][j]);
            }
            List<Integer> interimSortedBlock = new ArrayList<>(blockSet);
            result.add(interimSortedBlock);
        }

        return result;
    }
}
