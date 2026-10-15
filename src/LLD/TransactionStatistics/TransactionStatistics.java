package LLD.TransactionStatistics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TransactionStatistics {

    private Map<Long, Double> transactionsMap;

    public TransactionStatistics() {
        this.transactionsMap = new TreeMap<>();
    }

    public void addTransaction(double amount, long timestamp) {

        this.transactionsMap.put(timestamp, amount);
    }

    public Map<String, Double> getStatistics(long currentTime) {
        long transactionTimeStamp = 0l,  count = 0l;
        double transactionAmount = 0, sum = 0, average = 0;
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        Map<String, Double> statisticsMap = new LinkedHashMap<>();

        for(Map.Entry<Long, Double> entry : this.transactionsMap.entrySet()){
            transactionTimeStamp = entry.getKey();
            transactionAmount = entry.getValue();

            if(transactionTimeStamp >= (currentTime - 60000) && transactionTimeStamp <= currentTime){
                sum += transactionAmount;
                count++;
                max = Math.max(max, transactionAmount);
                min = Math.min(min, transactionAmount);
            }
        }
        if(sum == 0){
            average = 0;
        }else {
            average = (double) (sum / (double) count);
        }
        if(count == 0){
            min = 0;
            max = 0;
        }
        statisticsMap.put("sum", sum);
        statisticsMap.put("avg", average);
        statisticsMap.put("max", max);
        statisticsMap.put("min", min);
        statisticsMap.put("count", Double.valueOf(count));

        return statisticsMap;
    }

    public int getCount(long currentTime) {
        int count = 0;
        long transactionTimeStamp = 0l;

        for(Map.Entry<Long, Double> entry : this.transactionsMap.entrySet()){
            transactionTimeStamp = entry.getKey();

            if(transactionTimeStamp >= (currentTime - 60000) && transactionTimeStamp <= currentTime){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        TransactionStatistics stats = new TransactionStatistics();

        stats.addTransaction(100.0, 1000);
        stats.addTransaction(200.0, 30000);
        stats.addTransaction(300.0, 50000);
        System.out.println(stats.getStatistics(60000));
        System.out.println(stats.getCount(60000));

        System.out.println();

        stats = new TransactionStatistics();

        stats.addTransaction(50.0, 1000);
        stats.addTransaction(150.0, 5000);
        System.out.println(stats.getStatistics(70000));
        System.out.println(stats.getCount(70000));
        System.out.println(stats.getStatistics(120000));

    }
}
