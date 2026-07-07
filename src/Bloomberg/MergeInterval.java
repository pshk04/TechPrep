package Bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeInterval {

    public static void main(String[] args) {
        int[][] intervals = {{2,5}, {3,7}, {10,12}, {15,18}};

        for(int[] interval : merge(intervals)){
            System.out.println(Arrays.toString(interval));
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int[] lastInterval = merged.get(merged.size() - 1);
            // Check for overlap and merge if necessary
            if (currentInterval[0] > lastInterval[1]) {
                merged.add(currentInterval);
            } else {
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            }
        }

        return merged.toArray(new int[0][]);
    }
}
