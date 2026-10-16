package LLD.TransactionHistoryService;

import java.util.*;

public class TransactionHistoryService {

    private Map<Integer, Account> accountsMap;

    public TransactionHistoryService() {
        this.accountsMap = new HashMap<>();
    }

    public void recordTransaction(int transactionId, int accountId, int timestamp, String type, int amount) {
        List<Transaction> accountsTransactionsList = null;

        if(this.accountsMap.containsKey(accountId)){
            Account account = this.accountsMap.get(accountId);

            if(type.equals("DEPOSIT")){
                account.setBalance(account.getBalance() + amount);
            } else if (type.equals("WITHDRAWAL")) {
                account.setBalance(account.getBalance() - amount);
            }
            Transaction transaction = new Transaction(amount, transactionId, timestamp, type);
            accountsTransactionsList = account.getTransactionList();
            accountsTransactionsList.add(transaction);
        }else{
            Transaction transaction = new Transaction(amount, transactionId, timestamp, type);
            accountsTransactionsList = new ArrayList<>();
            accountsTransactionsList.add(transaction);
            Account account = null;

            if(type.equals("DEPOSIT")){
                account = new Account(amount, accountId, accountsTransactionsList);
            } else if (type.equals("WITHDRAWAL")) {
                account = new Account(-amount, accountId, accountsTransactionsList);
            }

            this.accountsMap.put(accountId, account);
        }
    }

    public int getBalance(int accountId) {
        if(this.accountsMap.containsKey(accountId)) {
            return this.accountsMap.get(accountId).getBalance();
        }
        return 0;
    }

    public List<Integer> getRecentTransactions(int accountId, int k) {
        List<Transaction> transactionsList;
        List<Integer> accountTransactionsList = new ArrayList<>();
        Transaction transaction = null;

        if(this.accountsMap.containsKey(accountId)){
            Account account = this.accountsMap.get(accountId);
            transactionsList = account.getTransactionList();

            for(int i = transactionsList.size() - 1; i >= 0 && k > 0; i--, k--){
                transaction = transactionsList.get(i);
                accountTransactionsList.add(transaction.getId());
            }
        }
        return accountTransactionsList;
    }

    public List<Integer> getTransactionsInRange(int accountId, int startTime, int endTime) {
        List<Integer> accountTransactionsList = new ArrayList<>();
        List<Transaction> transactionsList = null;

        if(this.accountsMap.containsKey(accountId)){
            Account account = this.accountsMap.get(accountId);
            transactionsList = account.getTransactionList();

            for(Transaction transaction : transactionsList){
                if(transaction.getTimestamp() >= startTime && transaction.getTimestamp() <= endTime){
                    accountTransactionsList.add(transaction.getId());
                }
            }
        }
        return accountTransactionsList;
    }

    public int getTotalInRange(int accountId, String type, int startTime, int endTime) {

        List<Transaction> transactionsList = null;
        int totalAmount = 0;

        if(this.accountsMap.containsKey(accountId)) {
            Account account = this.accountsMap.get(accountId);
            transactionsList = account.getTransactionList();
            for (Transaction transaction : transactionsList) {
                if (transaction.getTimestamp() >= startTime && transaction.getTimestamp() <= endTime && transaction.getType().equals(type)) {
                    totalAmount += transaction.getAmount();
                }
            }
        }
        return totalAmount;
    }

    public static void main(String[] args) {
        TransactionHistoryService svc = new TransactionHistoryService();

        svc.recordTransaction(101, 1, 100, "DEPOSIT", 500);
        svc.recordTransaction(102, 1, 200, "WITHDRAWAL", 150);
        svc.recordTransaction(103, 1, 300, "DEPOSIT", 200);
        System.out.println(svc.getBalance(1));
        System.out.println(svc.getRecentTransactions(1, 2));
        System.out.println(svc.getTransactionsInRange(1, 150, 300));
        System.out.println(svc.getTotalInRange(1, "DEPOSIT", 1, 300));

        System.out.println();

        svc = new TransactionHistoryService();

        System.out.println(svc.getBalance(7));
        svc.recordTransaction(501, 7, 10, "DEPOSIT", 1000);
        svc.recordTransaction(502, 7, 20, "WITHDRAWAL", 1500);;
        System.out.println(svc.getRecentTransactions(7, 5));
        System.out.println(svc.getTotalInRange(7, "WITHDRAWAL", 0, 100));
        System.out.println(svc.getTransactionsInRange(7, 25, 30));

        System.out.println();
    }
}
