package LLD.BankingApplication2;

import java.util.*;

public class BankingSystem {

    private Map<String, Integer> bankAccountsMap;
    private Map<String, Integer> successfulTransfersMap;

    public BankingSystem() {
        this.bankAccountsMap = new HashMap<>();
        this.successfulTransfersMap = new HashMap<>();
    }

    public boolean createAccount(int timestamp, String accountId) {
        if(this.bankAccountsMap.containsKey(accountId)){
            return false;
        }else{
            this.bankAccountsMap.put(accountId, 0);
            this.successfulTransfersMap.put(accountId, 0);
            return true;
        }
    }

    public Integer deposit(int timestamp, String accountId, int amount) {
        if(!this.bankAccountsMap.containsKey(accountId)){
            return null;
        }else{
            int currentBalance = this.bankAccountsMap.get(accountId) + amount;
            this.bankAccountsMap.put(accountId, currentBalance);
            return currentBalance;
        }
    }

    public List<String> topSpenders(int timestamp, int n) {
        PriorityQueue<AccountTransfer> maxTransfersAccountsHeap = new PriorityQueue<>((d1, d2) -> {
            int priorityCompare = Integer.compare(d2.getTransferAmount(), d1.getTransferAmount());

            if (priorityCompare == 0) {
                return d1.getAccountId().compareTo(d2.getAccountId());
            }

            return priorityCompare;
        });

        String accountId = "";
        int transferAmount = 0;
        List<String> topTransfersAccountList = new ArrayList<>();
        AccountTransfer accountTransfer;

        for(Map.Entry<String, Integer> entry : this.successfulTransfersMap.entrySet()){
            accountId = entry.getKey();
            transferAmount = entry.getValue();
            accountTransfer = new AccountTransfer(accountId, transferAmount);
            maxTransfersAccountsHeap.offer(accountTransfer);
        }

        n = Math.min(n, maxTransfersAccountsHeap.size());

        while(!maxTransfersAccountsHeap.isEmpty()){
            accountTransfer = maxTransfersAccountsHeap.poll();
            accountId = accountTransfer.getAccountId();
            topTransfersAccountList.add(accountId);
        }
        StringBuilder accountTransferStr = new StringBuilder();
        List<String> topSendersList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            accountId = topTransfersAccountList.get(i);
            accountTransferStr.append(accountId).append("(").append(this.successfulTransfersMap.get(accountId)).append(")");
            topSendersList.add(accountTransferStr.toString());
            accountTransferStr = new StringBuilder();
        }
        return topSendersList;
    }

    public Integer transfer(int timestamp, String sourceId, String targetId, int amount) {
        if(!this.bankAccountsMap.containsKey(sourceId) || !this.bankAccountsMap.containsKey(targetId)){
            return null;
        }else if(sourceId.equals(targetId)){
            return null;
        }else if(this.bankAccountsMap.get(sourceId) < amount){
            return null;
        }else{
            int currentBalance = this.bankAccountsMap.get(targetId) + amount;
            this.bankAccountsMap.put(targetId, currentBalance);
            if(this.successfulTransfersMap.containsKey(sourceId)){
                int totalTransferAmount = this.successfulTransfersMap.get(sourceId) + amount;
                this.successfulTransfersMap.put(sourceId, totalTransferAmount);
            }else{
                this.successfulTransfersMap.put(sourceId, amount);
            }
            currentBalance = this.bankAccountsMap.get(sourceId) - amount;
            this.bankAccountsMap.put(sourceId, currentBalance);
            return Integer.valueOf(currentBalance);
        }
    }
}
