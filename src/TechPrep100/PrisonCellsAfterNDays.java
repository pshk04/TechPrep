package TechPrep100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {

    public static void main(String[] args) {
        int[][] prison = {
                {0,1,0,1,1,0,0,1},
//                {1,0,0,1,0,0,1,0}
        };

        int[] n = {7}; // 1000000000

        System.out.println("Mod: "+(908 % 71));
        for(int i = 0 ; i < prison.length; i++) {
            System.out.println("The prison cells after " + n[i] + " days: "+ Arrays.toString(prisonAfterNdays(prison[i], n[i])));
        }
    }

    public static int[] prisonAfterNdaysOptimized(int[] cells, int n){

        int currentDay = 0;
        Map<String, Integer> seenMap = new HashMap<>();

        while(currentDay < n){
            String stateKey = Arrays.toString(cells);
            if(seenMap.containsKey(stateKey)){
                int cycleLength = currentDay - seenMap.get(stateKey);
                int remaining = (n - currentDay) % cycleLength;
                for(int i = 0 ; i < remaining; i++){
                    cells = nextDayCells(cells);
                }
                return cells;
            }
            seenMap.put(stateKey, currentDay);
            cells = nextDayCells(cells);
            currentDay++;
        }
        return cells;
    }

    public static int[] nextDayCells(int[] cells){
        int[] newCells = new int[8];

        for(int i = 1; i < 7; i++){
            newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        }
        return newCells;
    }

    public static int[] prisonAfterNdays(int[] cells, int n){
        System.out.println("0: "+Arrays.toString(cells));
        int[] newCells = Arrays.copyOfRange(cells, 0, cells.length);

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < cells.length; j++){
                if(j == 0 || j == cells.length - 1){
                    newCells[j] = 0;
                }else{
                    if((cells[j - 1] == 0 && cells[j + 1] == 0) || (cells[j - 1] == 1 && cells[j + 1] == 1)){
                        newCells[j] = 1;
                    }else{
                        newCells[j] = 0;
                    }
                }
            }
            cells = Arrays.copyOfRange(newCells, 0, newCells.length);
            System.out.println((i+1)+": "+Arrays.toString(cells));
        }
        return cells;
    }
}
