package LLD.Bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, BankAccount> bankAccountMap;

    public Bank() {
        this.bankAccountMap = new HashMap<>();
    }

    public boolean createAccount(int accountId, int initialBalance) {
        if(initialBalance >= 0) {
            if (!this.bankAccountMap.containsKey(accountId)) {
                BankAccount bankAccount = new BankAccount(initialBalance, accountId);
                this.bankAccountMap.put(accountId, bankAccount);
                return true;
            }
        }
        return false;
    }

    public boolean deposit(int accountId, int amount) {
        if(amount > 0) {
            if (this.bankAccountMap.containsKey(accountId)) {
                BankAccount bankAccount = this.bankAccountMap.get(accountId);
                bankAccount.setBalance(bankAccount.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(int accountId, int amount) {

        if(amount > 0) {
            if (this.bankAccountMap.containsKey(accountId)) {
                BankAccount bankAccount = this.bankAccountMap.get(accountId);
                if(bankAccount.getBalance() >= amount) {
                    bankAccount.setBalance(bankAccount.getBalance() - amount);
                    return true;
                }else{
                    return false;
                }
            }
        }

        return false;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        if(amount > 0) {
            if (this.bankAccountMap.containsKey(fromId) && this.bankAccountMap.containsKey(toId)
            && (fromId != toId)
            ) {
                BankAccount sourceBankAccount = this.bankAccountMap.get(fromId);
                BankAccount targetBankAccount = this.bankAccountMap.get(toId);
                if(sourceBankAccount.getBalance() >= amount) {
                    sourceBankAccount.setBalance(sourceBankAccount.getBalance() - amount);
                    targetBankAccount.setBalance(targetBankAccount.getBalance() + amount);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public int getBalance(int accountId) {
        if (this.bankAccountMap.containsKey(accountId)) {
            return bankAccountMap.get(accountId).getBalance();
        }
        return -1;
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println(bank.createAccount(1, 100));
        System.out.println(bank.createAccount(2, 50));
        System.out.println(bank.deposit(1, 25));
        System.out.println(bank.withdraw(2, 10));
        System.out.println(bank.transfer(1, 2, 30));
        System.out.println(bank.getBalance(1));
        System.out.println(bank.getBalance(2));

        System.out.println();

        bank = new Bank();

        System.out.println(bank.createAccount(10, 100));
        System.out.println(bank.createAccount(10, 200));
        System.out.println(bank.withdraw(10, 500));
        System.out.println(bank.transfer(10, 99, 20));
        System.out.println(bank.deposit(99, 50));
        System.out.println(bank.getBalance(10));
        System.out.println(bank.getBalance(99));

        System.out.println();
    }
}
