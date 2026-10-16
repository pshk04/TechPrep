package LLD.BankAccountApplication;

public class BankAccount {

    private String name;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankAccount(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}
