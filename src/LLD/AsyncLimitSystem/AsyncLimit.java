package LLD.AsyncLimitSystem;

import java.util.*;

class AsyncLimit {

    private int tasksLimit;
    private List<Integer> runningTasksList;
    private ArrayDeque<Integer> waitingTasksQueue;

    public AsyncLimit(int limit) {
        this.tasksLimit = limit;
        this.runningTasksList = new ArrayList<Integer>();
        this.waitingTasksQueue = new ArrayDeque<Integer>();
    }

    public boolean submit(int taskId) {
        if(this.runningTasksList.size() < this.tasksLimit){
            this.runningTasksList.add(taskId);
            return true;
        }
        this.waitingTasksQueue.add(taskId);
        return false;
    }

    public void complete(int taskId) {
        if(this.runningTasksList.contains(taskId)){
            this.runningTasksList.remove(removeObject(this.runningTasksList, taskId));
            if(!this.waitingTasksQueue.isEmpty()){
                this.runningTasksList.add(this.waitingTasksQueue.poll());
            }
        }
    }

    public List<Integer> runningTasks() {
        Collections.sort(this.runningTasksList);
        return this.runningTasksList;
    }

    public int pendingCount() {
        return this.waitingTasksQueue.size();
    }

    public static int removeObject(List<Integer> taskList, int task){
        int index = -1;
        for(int i = 0 ; i < taskList.size(); i++){
            if(taskList.get(i).equals(task)){
                index = i;
                break;
            }
        }
        return index;
    }
}

