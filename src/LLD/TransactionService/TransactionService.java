package LLD.TransactionService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionService {

    private int transactionId;
    private Map<Integer, Transaction> transactionsMap;

    public TransactionService() {
        this.transactionsMap = new HashMap<>();
        this.transactionId = 1;
    }

    public int createTransaction(long amount, long timestamp) {
        int transactionId = 0;
        Transaction newTransaction = new Transaction(amount, this.transactionId, timestamp);
        this.transactionsMap.put(this.transactionId, newTransaction);
        transactionId = this.transactionId;
        this.transactionId++;
        return transactionId;
    }

    public Map<String, Long> getTransaction(int transactionId) {

        Map<String, Long> currentTransactionMap = null;

        if(this.transactionsMap.containsKey(transactionId)) {
            currentTransactionMap = new LinkedHashMap<>();
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            currentTransactionMap.put("id", Long.valueOf(currentTransaction.getId()));
            currentTransactionMap.put("amount", Long.valueOf(currentTransaction.getAmount()));
            currentTransactionMap.put("timestamp", Long.valueOf(currentTransaction.getTimestamp()));
        }

        return currentTransactionMap;
    }

    public static void main(String[] args) {

        TransactionService svc = new TransactionService();

        System.out.println(svc.createTransaction(100, 1000));
        System.out.println(svc.createTransaction(250, 1500));
        System.out.println(svc.getTransaction(1));
        System.out.println(svc.getTransaction(2));

        System.out.println();

        svc = new TransactionService();

        System.out.println(svc.getTransaction(42));
        System.out.println(svc.createTransaction(500, 2000));
        System.out.println(svc.getTransaction(1));
        System.out.println(svc.getTransaction(42));
    }
}
