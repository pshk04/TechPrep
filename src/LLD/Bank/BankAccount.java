package LLD.Bank;

public class BankAccount {

    private int id;
    private int balance;

    public BankAccount(int balance, int id) {
        this.balance = balance;
        this.id = id;
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
}
