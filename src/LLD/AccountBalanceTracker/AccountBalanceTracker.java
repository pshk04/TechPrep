package LLD.AccountBalanceTracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountBalanceTracker {
    private Map<String, Integer> userBankBalanceMap;

    public AccountBalanceTracker() {
        userBankBalanceMap = new HashMap<>();
    }

    public void processTransactions(List<String> transactions) {
        for(String transaction : transactions){
            if(transaction.contains("DEPOSIT")){
                String name = (transaction.split(" ")[1]).toLowerCase();
                int amount = Integer.parseInt(transaction.split(" ")[2]);
                if(this.userBankBalanceMap.containsKey(name)){
                    int currentBalance = this.userBankBalanceMap.get(name) + amount;
                    this.userBankBalanceMap.put(name, currentBalance);
                }else{
                    this.userBankBalanceMap.put(name, amount);
                }
            } else if (transaction.contains("WITHDRAW")) {
                String name = (transaction.split(" ")[1]).toLowerCase();
                int amount = Integer.parseInt(transaction.split(" ")[2]);
                if(this.userBankBalanceMap.containsKey(name) && this.userBankBalanceMap.get(name) > amount){
                    System.out.println("Transaction: "+transaction);
                    int currentBalance = this.userBankBalanceMap.get(name) - amount;
                    this.userBankBalanceMap.put(name, currentBalance);
                }
            } else if (transaction.contains("TRANSFER")) {
                String fromPerson = (transaction.split(" ")[1]).toLowerCase();
                String toPerson = (transaction.split(" ")[2]).toLowerCase();
                int transferAmount = Integer.parseInt(transaction.split(" ")[3]);
                if(this.userBankBalanceMap.containsKey(fromPerson) && this.userBankBalanceMap.containsKey(toPerson) &&
                        this.userBankBalanceMap.get(fromPerson) >= transferAmount && !fromPerson.equals(toPerson))
                {
                    int currentWithDrawerBalance = this.userBankBalanceMap.get(fromPerson) - transferAmount;
                    int currentDepositerBalance = this.userBankBalanceMap.get(toPerson) + transferAmount;
                    this.userBankBalanceMap.put(fromPerson, currentWithDrawerBalance);
                    this.userBankBalanceMap.put(toPerson, currentDepositerBalance);
                } else if (this.userBankBalanceMap.containsKey(fromPerson) && !this.userBankBalanceMap.containsKey(toPerson) &&
                        this.userBankBalanceMap.get(fromPerson) >= transferAmount && !fromPerson.equals(toPerson)) {
                    int currentWithDrawerBalance = this.userBankBalanceMap.get(fromPerson) - transferAmount;
                    this.userBankBalanceMap.put(fromPerson, currentWithDrawerBalance);
                    this.userBankBalanceMap.put(toPerson, transferAmount);
                }
            }
        }
        System.out.println("Ida: "+this.userBankBalanceMap.get("Ida".toLowerCase()));
        System.out.println("Jack: "+this.userBankBalanceMap.get("Jack".toLowerCase()));
    }

    public int getUserBalance(String user) {
        System.out.println("Printing the account balance: "+this.userBankBalanceMap.size());
        for(Map.Entry<String, Integer> entry : this.userBankBalanceMap.entrySet()){
            System.out.println(entry.getKey() +" "+entry.getValue());
        }
        return this.userBankBalanceMap.get(user.toLowerCase());
    }
}
