package LLD.BankingSystem;

import LLD.Bank.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingSystem {
    private Map<Integer, User> usersMap;
    private Map<Integer, Account> accountsMap;

    public BankingSystem() {
        this.usersMap = new HashMap<>();
        this.accountsMap = new HashMap<>();
    }

    public boolean createUser(int userId, String name) {

        if(!this.usersMap.containsKey(userId)){
            List<Account> userAccountsList = new ArrayList<>();
            User newUser = new User(userAccountsList, userId, name);
            this.usersMap.put(userId, newUser);
            return true;
        }
        return false;
    }

    public boolean openAccount(int accountId, int userId) {

        if(this.usersMap.containsKey(userId) && !this.accountsMap.containsKey(accountId)) {
            User existingUser = this.usersMap.get(userId);
            List<Account> userAccountsList = existingUser.getUserAccountsList();
            Account account = new Account(0, accountId, 0, userId);
            userAccountsList.add(account);
            this.accountsMap.put(accountId, account);
            return true;
        }
        return false;
    }

    public boolean deposit(int accountId, int amount) {

        if(amount > 0) {
            if (this.accountsMap.containsKey(accountId)) {
                Account account = this.accountsMap.get(accountId);
                account.setBalance(account.getBalance() + amount);
                account.setTotalTransactions(account.getTotalTransactions() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(int accountId, int amount) {

        if(amount > 0){
            if(this.accountsMap.containsKey(accountId)){
                Account account = this.accountsMap.get(accountId);
                if(account.getBalance() >= amount){
                    account.setBalance(account.getBalance() - amount);
                    account.setTotalTransactions(account.getTotalTransactions() + 1);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        if(amount > 0){
            if(this.accountsMap.containsKey(fromId) && this.accountsMap.containsKey(toId) && fromId != toId){
                Account sourceAccount = this.accountsMap.get(fromId);
                Account targetAccount = this.accountsMap.get(toId);
                if(sourceAccount.getBalance() >= amount){
                    sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                    sourceAccount.setTotalTransactions(sourceAccount.getTotalTransactions() + 1);
                    targetAccount.setBalance(targetAccount.getBalance() + amount);
                    targetAccount.setTotalTransactions(targetAccount.getTotalTransactions() + 1);
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    public int getBalance(int accountId) {

        if(this.accountsMap.containsKey(accountId)){
            return this.accountsMap.get(accountId).getBalance();
        }
        return -1;
    }

    public int getTransactionCount(int accountId) {
        if(this.accountsMap.containsKey(accountId)){
            return this.accountsMap.get(accountId).getTotalTransactions();
        }
        return -1;
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        System.out.println(bank.createUser(1, "Alice"));
        System.out.println(bank.createUser(2, "Bob"));
        System.out.println(bank.openAccount(100, 1));
        System.out.println(bank.openAccount(200, 2));
        System.out.println(bank.deposit(100, 500));
        System.out.println(bank.transfer(100, 200, 150));
        System.out.println(bank.getBalance(100));
        System.out.println(bank.getBalance(200));
        System.out.println(bank.getTransactionCount(100));

        System.out.println();

        bank = new BankingSystem();

        System.out.println(bank.createUser(1, "Alice"));
        System.out.println(bank.openAccount(10, 1));
        System.out.println(bank.openAccount(20, 1));
        System.out.println(bank.deposit(10, 100));
        System.out.println(bank.transfer(10, 20, 200));
        System.out.println(bank.transfer(10, 99, 50));
        System.out.println(bank.transfer(10, 20, 0));
        System.out.println(bank.getBalance(10));

        System.out.println();
    }
}
