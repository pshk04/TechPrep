package LLD.BankingSystem;

public class Account {

    private int id;
    private int userId;
    private int totalTransactions;
    private int balance;

    public Account(int balance, int id, int totalTransactions, int userId) {
        this.balance = balance;
        this.id = id;
        this.totalTransactions = totalTransactions;
        this.userId = userId;
    }

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

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
