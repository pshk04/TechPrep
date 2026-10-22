package LLD.BatchingUtility;

import java.util.ArrayList;
import java.util.List;

public class BatchingUtility {

    private int currentTime;
    private int batchSize;
    private int timeoutPerBatch;
    private List<Integer> batchItemsList;
    private List<List<Integer>> dispatchedBatchList;

    public BatchingUtility(int batchSize, int timeoutMs) {
        this.currentTime = 0;
        this.batchSize = batchSize;
        this.timeoutPerBatch = timeoutMs;
        this.batchItemsList = new ArrayList<>();
        this.dispatchedBatchList = new ArrayList<>();
    }

    public int add_item(int item) {
        this.batchItemsList.add(item);

        if(this.batchItemsList.size() < this.batchSize) {
            return 0;
        }else{
            if(this.batchItemsList.size() > 0) {
                this.dispatchedBatchList.add(this.batchItemsList);
                this.batchItemsList = new ArrayList<>();
            }
            return 1;
        }
    }

    public int tick(int ms) {
        this.currentTime += ms;

        if(this.currentTime >= this.timeoutPerBatch && this.batchItemsList.size() > 0){
            if(this.batchItemsList.size() > 0) {
                this.dispatchedBatchList.add(this.batchItemsList);
                this.batchItemsList = new ArrayList<>();
            }
            return 1;
        }
        return 0;
    }

    public int flush() {

        if(this.batchItemsList.size() > 0){
            this.dispatchedBatchList.add(this.batchItemsList);
            this.batchItemsList = new ArrayList<>();
            return 1;
        }
        return 0;
    }

    public List<List<Integer>> get_dispatched_batches() {
        List<List<Integer>> dispatchedBatchsList = this.dispatchedBatchList;

        return dispatchedBatchsList;
    }

    public List<Integer> get_current_batch() {
        List<Integer> bufferedBatchList = this.batchItemsList;
        return bufferedBatchList;
    }

    public int get_current_time() {
        int currentTimeInMs = this.currentTime;
        return currentTimeInMs;
    }

    public static void main(String[] args) {

        BatchingUtility batcher = new BatchingUtility(3, 1000);

        System.out.println(batcher.add_item(10));
        System.out.println(batcher.add_item(20));
        System.out.println(batcher.get_current_batch());
        System.out.println(batcher.add_item(30));
        System.out.println(batcher.get_dispatched_batches());
        System.out.println(batcher.get_current_batch());
        System.out.println(batcher.add_item(40));
        System.out.println(batcher.tick(500));
        System.out.println(batcher.get_dispatched_batches());

        System.out.println();

        batcher = new BatchingUtility(5, 500);

        System.out.println(batcher.add_item(1));
        System.out.println(batcher.add_item(2));
        System.out.println(batcher.tick(500));
        System.out.println(batcher.get_dispatched_batches());
        System.out.println(batcher.get_current_batch());
        System.out.println(batcher.get_current_time());

        System.out.println();
    }
}
