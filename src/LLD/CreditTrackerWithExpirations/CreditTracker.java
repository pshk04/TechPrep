package LLD.CreditTrackerWithExpirations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CreditTracker {
    private Map<Integer, Integer> creditsBalanceMap;

    public CreditTracker() {
        this.creditsBalanceMap = new TreeMap<>();
    }

    public void addCredits(int timestamp, int amount, int expiryTime) {
        if(this.creditsBalanceMap.containsKey(expiryTime)){
            int currentBalance = this.creditsBalanceMap.get(expiryTime);
            this.creditsBalanceMap.put(expiryTime, currentBalance + amount);
        }else{
            this.creditsBalanceMap.put(expiryTime, amount);
        }

    }

    public long spend(int timestamp, int amount) {
        return this.findAnddeleteExpiredCredits(timestamp, amount);
    }

    public long getBalance(int timestamp) {
        int currentBalance = 0;
        List<Integer> deleteList = new ArrayList<>();

        for(int expiry : this.creditsBalanceMap.keySet()){
            if(expiry > timestamp){
                currentBalance += this.creditsBalanceMap.get(expiry);
            }else{
                deleteList.add(expiry);
            }
        }
        this.deleteCredits(deleteList);
        return currentBalance;
    }

    public int findAnddeleteExpiredCredits(int timestamp, int amount){
        List<Integer> deleteList = new ArrayList<>();
        int currentTimeStamp = 0, currentCredit = 0, amountSpent = 0;

        for(Map.Entry<Integer, Integer> entry : this.creditsBalanceMap.entrySet()){
            currentTimeStamp = entry.getKey();
            currentCredit = entry.getValue();

            if(currentTimeStamp < timestamp){
                deleteList.add(currentTimeStamp);
            }else{
                if(currentCredit < amount){
                    amountSpent += currentCredit;
                    amount -= currentCredit;
                    deleteList.add(currentTimeStamp);
                }else{
                    currentCredit -= amount;
                    amountSpent += amount;
                    break;
                }
            }

        }
        this.deleteCredits(deleteList);
        if(this.creditsBalanceMap.size() > 0 && this.creditsBalanceMap.containsKey(currentTimeStamp)) {
            this.creditsBalanceMap.put(currentTimeStamp, currentCredit);
        }

        if(amountSpent == amount){
            return amount;
        }
        if(amountSpent != amount){
            return amountSpent;
        }
        return 0;
    }

    public void deleteCredits(List<Integer> deleteList){
        for(int creditExpiry : deleteList){
            this.creditsBalanceMap.remove(creditExpiry);
        }
    }
}
