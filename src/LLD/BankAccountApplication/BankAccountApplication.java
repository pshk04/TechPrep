package LLD.BankAccountApplication;

import java.util.HashMap;
import java.util.Map;

public class BankAccountApplication {

    private Map<String, BankAccount> accountsMap;
    private String accountName;

    public BankAccountApplication(String name, int initialBalance) {
        this.accountName = name;
        this.accountsMap = new HashMap<>();
        BankAccount newAccount = new BankAccount(initialBalance, name);
        this.accountsMap.put(name, newAccount);
    }

    public boolean deposit(int amount) {

        if(amount > 0) {
            BankAccount account = this.accountsMap.get(this.accountName);
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(int amount) {
        if(amount > 0) {
            BankAccount account = this.accountsMap.get(this.accountName);
            if(account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean transfer(BankAccount other, int amount) {

        if(amount > 0){
            BankAccount account = this.accountsMap.get(this.accountName);
            if(account.getBalance() >= amount){
                account.setBalance(account.getBalance() - amount);
                other.setBalance(other.getBalance() + amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public int getBalance() {
        return this.accountsMap.get(this.accountName).getBalance();
    }

    public String getName() {
        return this.accountsMap.get(this.accountName).getName();
    }

    public static void main(String[] args) {

        BankAccountApplication acct = new BankAccountApplication("Alice", 100);

        System.out.println(acct.deposit(50));
        System.out.println(acct.withdraw(30));
        System.out.println(acct.getBalance());
        System.out.println(acct.withdraw(200));
        System.out.println(acct.getBalance());
        System.out.println(acct.getName());

        System.out.println();

        acct = new BankAccountApplication("Bob", 0);

        System.out.println(acct.deposit(0));
        System.out.println(acct.deposit(100));
        System.out.println(acct.withdraw(-10));
        System.out.println(acct.getBalance());

    }
}
