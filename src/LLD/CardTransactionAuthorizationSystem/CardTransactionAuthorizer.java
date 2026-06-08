package LLD.CardTransactionAuthorizationSystem;

import java.util.*;

public class CardTransactionAuthorizer {
    private Map<String,Integer> bankAccountMap;
    private Map<String,Boolean> accountActivityMap;
    private Map<String, List<Integer>> approvalTimestampMap;

    public CardTransactionAuthorizer() {
        this.bankAccountMap = new HashMap<>();
        this.accountActivityMap = new HashMap<>();
        this.approvalTimestampMap = new HashMap<>();
    }

    public String createAccount(String accountId, int initialBalance) {
        if(!this.bankAccountMap.containsKey(accountId)){
            this.bankAccountMap.put(accountId, initialBalance);
            this.accountActivityMap.put(accountId, false);
            return "CREATED";
        }else{
            return "ALREADY_EXISTS";
        }
    }

    public String setBlocked(String accountId, boolean blocked) {
        if(this.accountActivityMap.containsKey(accountId) && blocked){
            this.accountActivityMap.put(accountId, true);
            return "OK";
        } else if (this.accountActivityMap.containsKey(accountId) && !blocked){
            this.accountActivityMap.put(accountId, false);
            return "OK";
        } else{
            return "ACCOUNT_NOT_FOUND";
        }
    }

    public String authorize(String accountId, int amount, int timestamp) {
        if(!this.bankAccountMap.containsKey(accountId)){
            return "ACCOUNT_NOT_FOUND";
        }else if(this.bankAccountMap.containsKey(accountId) && this.accountActivityMap.containsKey(accountId) && Boolean.valueOf(this.accountActivityMap.get(accountId))){
            return "ACCOUNT_BLOCKED";
        }else if(this.bankAccountMap.containsKey(accountId) && this.accountActivityMap.containsKey(accountId) && !Boolean.valueOf(this.accountActivityMap.get(accountId))){
            int currentBalance = this.bankAccountMap.get(accountId);
            if(currentBalance >= amount){
                if(this.approvalTimestampMap.containsKey(accountId)){
                    List<Integer> approvalsList = this.approvalTimestampMap.get(accountId);
                    for(int i = 0 ; i < approvalsList.size(); i++){
                        if(((timestamp - approvalsList.get(i)) < 120) && approvalsList.size() >= 3){
                            return "HIGH_FREQUENCY";
                        }else{
                            this.bankAccountMap.put(accountId, currentBalance - amount);
                            approvalsList.add(timestamp);
                            Collections.sort(approvalsList);
                            this.approvalTimestampMap.put(accountId, approvalsList);
                            return "APPROVED";
                        }
                    }
                }else{
                    List<Integer> approvalsList = new ArrayList<>();
                    this.bankAccountMap.put(accountId, currentBalance - amount);
                    approvalsList.add(timestamp);
                    Collections.sort(approvalsList);
                    this.approvalTimestampMap.put(accountId, approvalsList);
                    return "APPROVED";
                }

            } else if (this.bankAccountMap.get(accountId) < amount) {
                return "INSUFFICIENT_FUNDS";
            }
        }
        return "ACCOUNT_NOT_FOUND";
    }

    public int getBalance(String accountId) {
        if(this.bankAccountMap.containsKey(accountId)){
            return this.bankAccountMap.get(accountId);
        }
        return -1;
    }

    public void printBankAccounts(){
        for(Map.Entry<String, Integer> entry : this.bankAccountMap.entrySet()){
            System.out.println("Account Id: "+entry.getKey()+" : "+entry.getValue());
        }
    }
}
