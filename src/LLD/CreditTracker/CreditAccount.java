package LLD.CreditTracker;

public class CreditAccount {

    private String id;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreditAccount(int balance, String id) {
        this.balance = balance;
        this.id = id;
    }
}
