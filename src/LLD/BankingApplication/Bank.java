package LLD.BankingApplication;

import java.util.HashMap;
import java.util.Map;

class Bank {
    private Map<Integer, Integer> bankAccountsMap;
    private int accountId = 0;

    public Bank() {
        this.bankAccountsMap = new HashMap<>();
    }

    public int createAccount(int initialBalance) {
        this.accountId += 1;
        this.bankAccountsMap.put(this.accountId, initialBalance);
        return this.accountId;
    }

    public boolean deposit(int accountId, int amount) {
        if(this.bankAccountsMap.containsKey(accountId)){
            int currentBalance = this.bankAccountsMap.get(accountId);
            this.bankAccountsMap.put(accountId, currentBalance + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(int accountId, int amount) {
        if(!this.bankAccountsMap.containsKey(accountId) || this.bankAccountsMap.get(accountId) < amount){
            return false;
        }else{
            int currentBalance = this.bankAccountsMap.get(accountId);
            this.bankAccountsMap.put(accountId, currentBalance - amount);
            return true;
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        if(this.bankAccountsMap.containsKey(fromId) && this.bankAccountsMap.containsKey(toId) && this.bankAccountsMap.get(fromId) >= amount){
            System.out.println("Account 1: "+this.bankAccountsMap.get(1));
            System.out.println("Account 2: "+this.bankAccountsMap.get(2));
            int currentBalance = this.bankAccountsMap.get(fromId);
            this.bankAccountsMap.put(fromId, currentBalance - amount);
            currentBalance = this.bankAccountsMap.get(toId);
            this.bankAccountsMap.put(toId, currentBalance + amount);
            return true;
        }
        return false;
    }

    public long getBalance(int accountId) {
        if(this.bankAccountsMap.containsKey(accountId)){
            return Long.valueOf(this.bankAccountsMap.get(accountId));
        }
        return -1;
    }
}
