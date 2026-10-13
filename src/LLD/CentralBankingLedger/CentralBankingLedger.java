package LLD.CentralBankingLedger;

import java.util.HashMap;
import java.util.Map;

public class CentralBankingLedger {
    private Map<String, Integer> accountsMap;

    public CentralBankingLedger() {
        this.accountsMap = new HashMap<>();
    }

    public boolean createAccount(String name, int initialDeposit) {
        if(initialDeposit < 0){
            return false;
        }

        if(!this.accountsMap.containsKey(name)){
            this.accountsMap.put(name, initialDeposit);
            return true;
        }
        return false;
    }

    public boolean deposit(String name, int amount) {
        if(amount < 0){
            return false;
        }

        if(!this.accountsMap.containsKey(name)){
            return false;
        }else{
            this.accountsMap.put(name, this.accountsMap.get(name) + amount);
            return true;
        }

    }

    public boolean withdraw(String name, int amount) {
        if(amount < 0){
            return false;
        }
        if(!this.accountsMap.containsKey(name)){
            return false;
        }else{
            if(this.accountsMap.get(name) < amount){
                return false;
            }else {
                this.accountsMap.put(name, this.accountsMap.get(name) - amount);
                return true;
            }
        }
    }

    public boolean transfer(String fromAccount, String toAccount, int amount) {
        if(amount < 0){
            return false;
        }
        if(!this.accountsMap.containsKey(fromAccount) || !this.accountsMap.containsKey(toAccount)){
            return false;
        } else if (fromAccount.equals(toAccount)){
            return false;
        }else{
            if(this.accountsMap.get(fromAccount) < amount){
                return false;
            }else {
                this.accountsMap.put(fromAccount, this.accountsMap.get(fromAccount) - amount);
                this.accountsMap.put(toAccount, this.accountsMap.get(toAccount) + amount);
                return true;
            }
        }
    }

    public int getBalance(String name) {
        if(!this.accountsMap.containsKey(name)) {
            return -1;
        }else{
            return this.accountsMap.get(name);
        }
    }

    public int getTotalMoney() {
        int totalMoneyInTheSystem = 0;

        for(Map.Entry<String, Integer> entry : this.accountsMap.entrySet()){
            totalMoneyInTheSystem += entry.getValue();
        }
        return totalMoneyInTheSystem;
    }

    public static void main(String[] args) {
        CentralBankingLedger ledger = new CentralBankingLedger();

        System.out.println(ledger.createAccount("Alice", 1000));
        System.out.println(ledger.createAccount("Bob", 500));
        System.out.println(ledger.deposit("Alice", 200));
        System.out.println(ledger.transfer("Alice", "Bob", 300));
        System.out.println(ledger.getBalance("Alice"));
        System.out.println(ledger.getBalance("Bob"));
        System.out.println(ledger.getTotalMoney());
        System.out.println(ledger.withdraw("Bob", 100));
        System.out.println(ledger.getBalance("Bob"));
        System.out.println(ledger.getTotalMoney());
    }
}
