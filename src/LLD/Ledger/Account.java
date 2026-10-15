package LLD.Ledger;

import java.util.List;

public class Account {

    private String id;
    private int balance;
    private List<List<Object>> transactionsList;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        this.balance = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<Object>> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<List<Object>> transList) {
        this.transactionsList.addAll(transList);
    }

    public Account(int amount, String id, List<List<Object>> transactionsList) {
        this.balance = amount;
        this.id = id;
        this.transactionsList = transactionsList;
    }
}
