package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {

    public static void main(String[] args) {
        int[][][] tasks = {
                {
                        {1, 2},
                        {2, 4},
                        {3, 2},
                        {4, 1}
                },

                {
                        {7, 10},
                        {7, 12},
                        {7, 5},
                        {7, 4},
                        {7, 2}
                },
                {
                        {1,3},
                        {2,2},
                        {3,1}
                },
                {
                        {10,1},
                        {5,1},
                        {1,1}
                }
        };

        for (int[][] tasksList : tasks) {
            System.out.println("The order of tasks processed by CPU: " + Arrays.toString(getOrder(tasksList)));
        }
    }

    public static int[] getOrder(int[][] tasks) {
        int[] tasksCompletionOrder = new int[tasks.length];
        int index = 0, taskIndex = 0, n = tasks.length;
        long currentTime = 0;
        int[][] indexedTasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0]; // enqueueTime
            indexedTasks[i][1] = tasks[i][1]; // processingTime
            indexedTasks[i][2] = i;           // original index
        }
        Arrays.sort(indexedTasks, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });
        PriorityQueue<int[]> sortedTasksMinHeap = new PriorityQueue<>((a,b) -> {
            int processingTimeComparator = Integer.compare(a[0], b[0]);
            if(processingTimeComparator == 0){
                return Integer.compare(a[1], b[1]);
            }
            return processingTimeComparator;
        });

        while(taskIndex < tasks.length || !sortedTasksMinHeap.isEmpty()){
            if(sortedTasksMinHeap.isEmpty() && taskIndex < tasks.length && currentTime < indexedTasks[taskIndex][0]){
                currentTime = indexedTasks[taskIndex][0];
            }

            while(taskIndex < tasks.length && indexedTasks[taskIndex][0] <= currentTime) {
                sortedTasksMinHeap.offer(new int[]{indexedTasks[taskIndex][1], indexedTasks[taskIndex][2]});
                taskIndex++;
            }

            int[] currentTask = sortedTasksMinHeap.poll();
            tasksCompletionOrder[index++] = currentTask[1];
            currentTime += currentTask[0];
        }


        return tasksCompletionOrder;
    }
}
