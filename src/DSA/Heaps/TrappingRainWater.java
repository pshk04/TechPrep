package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[][][] heightMap =
                {
                        {
                                {1, 4, 3, 1, 3, 2},
                                {3, 2, 1, 3, 2, 4},
                                {2, 3, 3, 2, 3, 1}
                        },
                        {
                                {3, 4, 3},
                                {3, 1, 2},
                                {3, 3, 3}
                        },
                        {
                                {3, 3, 3, 3, 3},
                                {3, 2, 2, 2, 3},
                                {3, 2, 1, 2, 3},
                                {3, 2, 2, 2, 3},
                                {3, 3, 3, 3, 3}
                        }
                };

        for(int i = 0 ; i < heightMap.length; i++){
            System.out.println(trapRainWater(heightMap[i]));
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;

        if (m < 3 || n < 3) {
            return 0;
        }

        boolean[][] visited = new boolean[m][n];
        // Min-heap: [height, row, col]
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> {
            int heightComparator = Integer.compare(a[0], b[0]);
            if(heightComparator == 0){
                int rowComparator = Integer.compare(a[1], b[1]);
                if(rowComparator == 0){
                    return Integer.compare(a[2], b[2]);
                }
                return rowComparator;
            }
            return heightComparator;
        });

        // Push all boundary cells into the min-heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    System.out.println("Adding: "+Arrays.toString(new int[]{heightMap[i][j], i, j}));
                    heap.offer(new int[]{heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }

        int water = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!heap.isEmpty()) {
            int[] cell = heap.poll();
            System.out.println("Processing cell: "+Arrays.toString(cell));
            int h = cell[0];
            int r = cell[1];
            int c = cell[2];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    System.out.println("Visited neighbour: "+ Arrays.toString(new int[]{nr,nc}));
                    visited[nr][nc] = true;
                    // Water trapped at this cell is max(0, current_level - cell_height)
                    water += Math.max(0, h - heightMap[nr][nc]);
                    System.out.println("water level: "+water);
                    // Push with the effective height (max of cell height and water level)
                    heap.offer(new int[]{Math.max(h, heightMap[nr][nc]), nr, nc});
                    System.out.println("Adding: "+Arrays.toString(new int[]{Math.max(h, heightMap[nr][nc]), nr, nc}));
                }
            }
        }

        return water;
    }
}
