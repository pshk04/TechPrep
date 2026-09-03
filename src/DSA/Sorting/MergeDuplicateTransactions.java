package Sorting;

import java.util.*;

public class MergeDuplicateTransactions {

    public static void main(String[] args) {
        String[][][] transactions = {
//                {
//                        {"alice", "50", "amazon", "100"},
//                        {"alice", "50", "amazon", "140"},
//                        {"alice", "50", "amazon", "300"},
//                        {"bob", "20", "uber", "100"}
//                },
//                {
//                        {"u1","10","starbucks","1000"},
//                        {"u1","10","starbucks","1050"},
//                        {"u1","10","starbucks","1100"},
//                        {"u1","10","starbucks","1200"}
//                },
                {
                        {"a","5","m1","100"},
                        {"a","5","m2","100"},
                        {"a","6","m1","100"},
                        {"b","5","m1","100"}
                }
        };

        for(int i = 0 ; i < transactions.length; i++){
            System.out.println("Post merging the transactions: ");
            for(String[] transaction : mergeTransactions(transactions[i])){
                System.out.println(Arrays.toString(transaction));
            }
        }
    }

    public static String[][] mergeTransactions(String[][] transactions) {
        Map<String, List<Integer>> transactionMap = new TreeMap<>();
        PriorityQueue<String[]> transactionMinHeap = new PriorityQueue<>((a,b) ->{

           int priorityCompare = Integer.compare(Integer.parseInt(a[3]),Integer.parseInt(b[3]));

           if(priorityCompare != 0) {
                return priorityCompare;
           }
           int userIdCompare = a[0].compareTo(b[0]);
           if(userIdCompare != 0){
               return userIdCompare;
           }
           return a[2].compareTo(b[2]);
        });

        String transactionKey = "";
        int transactionTimeStamp = 0;
        List<Integer> timestampsList;

        for(String[] transaction : transactions){
            transactionKey = transaction[0]+" "+transaction[1]+" "+transaction[2];
            transactionTimeStamp = Integer.parseInt(transaction[3]);

            if(transactionMap.containsKey(transactionKey)){
                timestampsList = transactionMap.get(transactionKey);
            }else{
                timestampsList = new ArrayList<>();
            }
            timestampsList.add(transactionTimeStamp);
            Collections.sort(timestampsList);
            transactionMap.put(transactionKey, timestampsList);
        }

        for(Map.Entry<String, List<Integer>> entry : transactionMap.entrySet()){
            List<Integer> transactionstimestampList = entry.getValue();
            String currentTransactionKey = entry.getKey();
            timestampsList = new ArrayList<>();
            timestampsList.add(transactionstimestampList.get(0));
            if(transactionstimestampList.size() > 1) {
                for (int i = 1; i < transactionstimestampList.size(); i++) {
                    if ((transactionstimestampList.get(i) - transactionstimestampList.get(i - 1)) > 60) {
                        timestampsList.add(transactionstimestampList.get(i));
                    }
                }
            }
            for(int timestamp : timestampsList){
                String[] duplicateTransaction = currentTransactionKey.split(" ");
                System.out.println("Duplicate: "+Arrays.toString(duplicateTransaction));
                transactionMinHeap.offer(new String[]{duplicateTransaction[0], duplicateTransaction[1], duplicateTransaction[2], timestamp+""});
            }
        }

        String[][] mergedTransactions = new String[transactionMinHeap.size()][];
        int index = 0;

        while(!transactionMinHeap.isEmpty()){
            mergedTransactions[index] = transactionMinHeap.poll();
            index++;
        }

        return mergedTransactions;
    }
}
