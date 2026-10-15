package LLD.Ledger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {
    private int transactionId;
    private Map<String, Account> transactionsMap;

    public Ledger() {
        this.transactionId = 1;
        this.transactionsMap = new HashMap<>();
    }

    public int deposit(String accountId, int amount) {
        int accountBalance = 0, currentTransactionId = this.transactionId;
        List<List<Object>> accountsTransactionsList;
        List<Object> transactionItemsList;

        if(this.transactionsMap.containsKey(accountId)){
            Account account = this.transactionsMap.get(accountId);
            accountBalance = account.getBalance() + amount;
            account.setBalance(accountBalance);
            accountsTransactionsList = account.getTransactionsList();
            transactionItemsList  = new ArrayList<>();
            transactionItemsList.add(this.transactionId);
            transactionItemsList.add("deposit");
            transactionItemsList.add(amount);
            transactionItemsList.add("");
            accountsTransactionsList.add(transactionItemsList);
            this.transactionId++;
            return currentTransactionId;
        }else{
            accountsTransactionsList = new ArrayList<>();
            transactionItemsList  = new ArrayList<>();
            transactionItemsList.add(this.transactionId);
            transactionItemsList.add("deposit");
            transactionItemsList.add(amount);
            transactionItemsList.add("");
            accountsTransactionsList.add(transactionItemsList);
            Account account = new Account(amount, accountId, accountsTransactionsList);
            this.transactionsMap.put(accountId, account);
            this.transactionId++;
            return currentTransactionId;
        }
    }

    public int withdraw(String accountId, int amount) {
        int accountBalance = 0, currentTransactionId = this.transactionId;
        List<List<Object>> accountsTransactionsList;
        List<Object> transactionItemsList;


        if(this.transactionsMap.containsKey(accountId)){
            Account account = this.transactionsMap.get(accountId);
            accountBalance = account.getBalance();
            if(accountBalance < amount){
                return -1;
            }else{
                transactionItemsList = new ArrayList<>();
                accountBalance = account.getBalance() - amount;
                account.setBalance(accountBalance);
                accountsTransactionsList = account.getTransactionsList();
                transactionItemsList.add(this.transactionId);
                transactionItemsList.add("withdraw");
                transactionItemsList.add(amount);
                transactionItemsList.add("");
                accountsTransactionsList.add(transactionItemsList);
                this.transactionsMap.put(accountId, account);
                this.transactionId++;
                return currentTransactionId;
            }
        }
        return -1;
    }

    public int transfer(String fromId, String toId, int amount) {
        int currentTransactionId = this.transactionId;
        List<List<Object>> accountsTransactionsList;
        List<Object> transactionItemsList;

        if(this.transactionsMap.containsKey(fromId) && this.transactionsMap.containsKey(toId)){
            if(!fromId.equals(toId)){
                Account sourceAccount = this.transactionsMap.get(fromId);
                if(sourceAccount.getBalance() < amount){
                    return -1;
                }else{
                    Account destinationAccount = this.transactionsMap.get(toId);
                    sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                    destinationAccount.setBalance(destinationAccount.getBalance() + amount);
                    transactionItemsList = new ArrayList<>();
                    transactionItemsList.add(this.transactionId);
                    transactionItemsList.add("transfer_out");
                    transactionItemsList.add(amount);
                    transactionItemsList.add(toId);
                    accountsTransactionsList = sourceAccount.getTransactionsList();
                    accountsTransactionsList.add(transactionItemsList);

                    accountsTransactionsList = destinationAccount.getTransactionsList();
                    transactionItemsList = new ArrayList<>();
                    transactionItemsList.add(this.transactionId);
                    transactionItemsList.add("transfer_in");
                    transactionItemsList.add(amount);
                    transactionItemsList.add(fromId);
                    accountsTransactionsList.add(transactionItemsList);
                    this.transactionId++;
                    return currentTransactionId;
                }
            }else{
                return -1;
            }
        }else if(this.transactionsMap.containsKey(fromId) && !this.transactionsMap.containsKey(toId)){
            Account sourceAccount = this.transactionsMap.get(fromId);
            transactionItemsList = new ArrayList<>();
            transactionItemsList.add(this.transactionId);
            transactionItemsList.add("transfer_out");
            transactionItemsList.add(amount);
            transactionItemsList.add(toId);
            accountsTransactionsList = sourceAccount.getTransactionsList();
            accountsTransactionsList.add(transactionItemsList);
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);

            accountsTransactionsList = new ArrayList<>();
            transactionItemsList = new ArrayList<>();
            transactionItemsList.add(this.transactionId);
            transactionItemsList.add("transfer_in");
            transactionItemsList.add(amount);
            transactionItemsList.add(fromId);
            accountsTransactionsList.add(transactionItemsList);
            Account destinationAccount = new Account(amount, toId, accountsTransactionsList);
            this.transactionsMap.put(toId, destinationAccount);
            this.transactionId++;
            return currentTransactionId;
        }
        return -1;
    }

    public int getBalance(String accountId) {
        if(this.transactionsMap.containsKey(accountId)){
            return this.transactionsMap.get(accountId).getBalance();
        }
        return 0;
    }

    public List<List<Object>> getHistory(String accountId) {
        List<List<Object>> accountTransactions = new ArrayList<>();

        if(this.transactionsMap.containsKey(accountId)) {
            Account account = this.transactionsMap.get(accountId);
            accountTransactions = account.getTransactionsList();
        }
        return accountTransactions;
    }

    public static void main(String[] args) {

        Ledger ledger = new Ledger();

        System.out.println(ledger.deposit("alice", 100));
        System.out.println(ledger.deposit("bob", 50));
        System.out.println(ledger.withdraw("alice", 30));
        System.out.println(ledger.transfer("alice", "bob", 40));
        System.out.println(ledger.getBalance("alice"));
        System.out.println(ledger.getBalance("bob"));
        System.out.println(ledger.withdraw("alice", 500));
        System.out.println(ledger.getHistory("alice"));

        System.out.println();

        ledger = new Ledger();

        System.out.println(ledger.getBalance("ghost"));
        System.out.println(ledger.transfer("x", "x", 10));
        System.out.println(ledger.deposit("x", 20));
        System.out.println(ledger.transfer("x", "y", 5));
        System.out.println(ledger.getBalance("y"));
        System.out.println(ledger.getHistory("y"));

    }
}
