package LLD.TransactionAggregator;

import java.util.*;

public class TransactionAggregator {

    private Map<Integer, List<Transaction>> transactionMap;

    public TransactionAggregator() {
        this.transactionMap = new TreeMap<>();
    }

    public void addTransaction(String merchant, int amount, int timestamp) {
        Transaction transaction = new Transaction(amount, merchant);
        List<Transaction> transList = null;
        if(this.transactionMap.containsKey(timestamp)){
            transList = this.transactionMap.get(timestamp);
        }else {
            transList = new ArrayList<>();
        }
        transList.add(transaction);
        this.transactionMap.put(timestamp, transList);
    }

    public String getTopMerchant(int queryTime) {

        Map<String, Integer> merchantVolumeMap = new HashMap<>();
        int startTime = (queryTime < 86400) ? 0 : (queryTime - 86400);
        int currentTimeStamp = 0, transactionAmount = 0, totalTransactionAmount = 0;
        String merchant = "", topVolumeMerchant = "";

        PriorityQueue<String[]> merchantMaxHeap = new PriorityQueue<>((a,b) -> {
            int merchantVolumeComparator = Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1]));
            if(merchantVolumeComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return merchantVolumeComparator;
        });

        for(Map.Entry<Integer, List<Transaction>> entry : this.transactionMap.entrySet()){
            currentTimeStamp = entry.getKey();
            List<Transaction> transList = entry.getValue();

            for(Transaction transaction : transList) {
                merchant = transaction.getMerchant();
                transactionAmount = transaction.getAmount();

                if ((queryTime != startTime) && (currentTimeStamp > startTime && currentTimeStamp <= queryTime)) {
                    if (merchantVolumeMap.containsKey(merchant)) {
                        merchantVolumeMap.put(merchant, merchantVolumeMap.get(merchant) + transactionAmount);
                    } else {
                        merchantVolumeMap.put(merchant, transactionAmount);
                    }
                } else if ((queryTime == startTime ) && (currentTimeStamp >= startTime && currentTimeStamp <= queryTime)){
                    if (merchantVolumeMap.containsKey(merchant)) {
                        merchantVolumeMap.put(merchant, merchantVolumeMap.get(merchant) + transactionAmount);
                    } else {
                        merchantVolumeMap.put(merchant, transactionAmount);
                    }
                }
            }
        }

//        System.out.println(merchantVolumeMap);

        for(Map.Entry<String, Integer> entry : merchantVolumeMap.entrySet()){
            merchantMaxHeap.offer(new String[]{entry.getKey(),String.valueOf(entry.getValue())});
        }

        if(!merchantMaxHeap.isEmpty()) {
            topVolumeMerchant = merchantMaxHeap.poll()[0];
        }

        return topVolumeMerchant;
    }

    public static void main(String[] args) {
        TransactionAggregator agg = new TransactionAggregator();

        agg.addTransaction("amazon", 500, 1000);
        agg.addTransaction("google", 300, 2000);
        agg.addTransaction("amazon", 200, 3000);
        System.out.println(agg.getTopMerchant(3000));
        agg.addTransaction("google", 500, 4000);
        System.out.println(agg.getTopMerchant(4000));
        System.out.println(agg.getTopMerchant(90000));

        System.out.println();

        agg = new TransactionAggregator();

        System.out.println(agg.getTopMerchant(100));
        agg.addTransaction("shop", 100, 200);
        System.out.println(agg.getTopMerchant(200));
        System.out.println(agg.getTopMerchant(86700));

        System.out.println();

        agg = new TransactionAggregator();
        agg.addTransaction("alpha", 100, 1000);
        agg.addTransaction("beta", 100, 1000);

        System.out.println(agg.getTopMerchant(1000));

        System.out.println();

        agg = new TransactionAggregator();
        agg.addTransaction("aaa", 1000, 0);
        agg.addTransaction("bbb", 500, 43200);
        agg.addTransaction("ccc", 2000, 86000);

        System.out.println(agg.getTopMerchant(86400));
        System.out.println(agg.getTopMerchant(172400));

        System.out.println();

        agg = new TransactionAggregator();
        agg.addTransaction("store", 999999, 0);
        System.out.println(agg.getTopMerchant(0));
        agg.addTransaction("store", 1, 86400);
        System.out.println(agg.getTopMerchant(86400));
        agg.addTransaction("other", 100001, 86401);
        System.out.println(agg.getTopMerchant(86401));

    }
}
