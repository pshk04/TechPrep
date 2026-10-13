package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPUWithPriority {

    public static void main(String[] args) {
        int[][][] tasks = {
                {
                        {1, 2, 3},
                        {2, 4, 1},
                        {3, 2, 2},
                        {4, 1, 5}
                },
                {
                        {7, 10, 2},
                        {7, 12, 1},
                        {7, 5, 3},
                        {7, 4, 4},
                        {7, 2, 5}
                }
        };
        for(int i = 0 ; i < tasks.length; i++) {
            System.out.println(Arrays.toString(getOrder(tasks[i])));
        }
    }

    public static int[] getOrder(int[][] tasks) {
        int[] tasksCompletionOrder = new int[tasks.length];
        int index = 0, taskIndex = 0, n = tasks.length;
        long currentTime = 0;
        int[][] indexedTasks = new int[n][4];

        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0]; // enqueueTime
            indexedTasks[i][1] = tasks[i][1]; // processingTime
            indexedTasks[i][2] = tasks[i][2]; // priority
            indexedTasks[i][3] = i;           // original index
        }
        Arrays.sort(indexedTasks, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            return Integer.compare(a[3], b[3]);
        });

        PriorityQueue<int[]> sortedTasksMinHeap = new PriorityQueue<>((a, b) -> {
            int processingTimeComparator = Integer.compare(a[0], b[0]);
            if(processingTimeComparator == 0){
                int priorityComparator = Integer.compare(a[1], b[1]);
                if(priorityComparator == 0){
                    return Integer.compare(a[2], b[2]);
                }
                return priorityComparator;
            }
            return processingTimeComparator;
        });

        while(taskIndex < tasks.length || !sortedTasksMinHeap.isEmpty()){
            if(sortedTasksMinHeap.isEmpty() && taskIndex < tasks.length && currentTime < indexedTasks[taskIndex][0]){
                currentTime = indexedTasks[taskIndex][0];
            }

            while(taskIndex < tasks.length && indexedTasks[taskIndex][0] <= currentTime) {
                sortedTasksMinHeap.offer(new int[]{indexedTasks[taskIndex][1], indexedTasks[taskIndex][2], indexedTasks[taskIndex][3]});
                taskIndex++;
            }

            int[] currentTask = sortedTasksMinHeap.poll();
            tasksCompletionOrder[index++] = currentTask[2];
            currentTime += currentTask[0];
        }


        return tasksCompletionOrder;
    }
}
