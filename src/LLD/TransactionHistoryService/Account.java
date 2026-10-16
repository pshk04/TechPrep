package LLD.TransactionHistoryService;

import java.util.List;

public class Account {
    private int id;
    private int balance;
    private List<Transaction> transactionList;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Account(int balance, int id, List<Transaction> transactionList) {
        this.balance = balance;
        this.id = id;
        this.transactionList = transactionList;
    }
}
