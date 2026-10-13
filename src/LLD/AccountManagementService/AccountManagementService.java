package LLD.AccountManagementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManagementService {
    private Map<String, Account> accountsMap;
    private List<String> accountTypes;

    public AccountManagementService() {
        this.accountsMap = new HashMap<>();

        this.accountTypes = new ArrayList<>();
        this.accountTypes.add("STANDARD");
        this.accountTypes.add("METAL");
        this.accountTypes.add("BUSINESS");
    }

    public boolean createAccount(String accountId, String accountType) {

        if(!this.accountTypes.contains(accountType)) {
            return false;
        }else if(!this.accountsMap.containsKey(accountId)){
            Account newAccount = new Account(0, accountType, accountId,0);
            this.accountsMap.put(accountId, newAccount);
            return true;
        }
        return false;
    }

    public boolean deposit(String accountId, int amount) {
        int currentbalance = 0, rewardsObtained = 0;

        if(this.accountsMap.containsKey(accountId)){
            Account currentAccount = this.accountsMap.get(accountId);
            currentbalance = currentAccount.getAccountbalance() + amount;
            currentAccount.setAccountbalance(currentbalance);
            rewardsObtained = findRewardsForTheAccount(currentAccount.getAccountType(), amount);
            currentAccount.setRewardPoints(currentAccount.getRewardPoints() +  rewardsObtained);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountId, int amount) {
        int withdrawalFee = 0, currentbalance = 0, withdrawalCharges = 0;

        if(this.accountsMap.containsKey(accountId)){
            Account currentAccount = this.accountsMap.get(accountId);
            withdrawalFee = findWithdrawalOrTransferFee(currentAccount.getAccountType(), amount);
            currentbalance = currentAccount.getAccountbalance();
            withdrawalCharges = amount + withdrawalFee;
            System.out.println("withdrawal charges: "+withdrawalCharges);

            if(currentbalance >= withdrawalCharges) {
                currentAccount.setAccountbalance(currentbalance - withdrawalCharges);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean transfer(String fromId, String toId, int amount) {
        int transferFee = 0, currentSourceAccountbalance = 0, currentTransferAccountbalance = 0, transferCharges = 0, rewardsObtained = 0;

        if(this.accountsMap.containsKey(fromId) && this.accountsMap.containsKey(toId)){
            Account currentSourceAccount = this.accountsMap.get(fromId); // b1
            Account currentTransferAccount = this.accountsMap.get(toId); // s1
            transferFee = findWithdrawalOrTransferFee(currentSourceAccount.getAccountType(), amount); // b1 -> s1 , 2000
            currentSourceAccountbalance = currentSourceAccount.getAccountbalance();
            currentTransferAccountbalance = currentTransferAccount.getAccountbalance();
            transferCharges = amount + transferFee; // 303

            if(currentSourceAccountbalance >= transferCharges) {
                currentSourceAccount.setAccountbalance(currentSourceAccountbalance - transferCharges); // 697
                rewardsObtained = findRewardsForTheAccount(currentTransferAccount.getAccountType(), amount); // 3
                currentTransferAccount.setRewardPoints(currentTransferAccount.getRewardPoints() + rewardsObtained);
                currentTransferAccount.setAccountbalance(currentTransferAccountbalance + amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public int getBalance(String accountId) {
        if(this.accountsMap.containsKey(accountId)){
            return this.accountsMap.get(accountId).getAccountbalance();
        }
        return -1;
    }

    public int getRewards(String accountId) {
        if(this.accountsMap.containsKey(accountId)){
            return this.accountsMap.get(accountId).getRewardPoints();
        }
        return -1;
    }

    public int findWithdrawalOrTransferFee(String accountType, int amount){
        int withdrawalFee = 0;

        if(accountType.equals("STANDARD")) {
            withdrawalFee = 1;
        }else if(accountType.equals("METAL")){
            withdrawalFee = 0;
        }else if(accountType.equals("BUSINESS")){
            withdrawalFee = (int) Math.floor((double)((0.5 * amount)/100));
            withdrawalFee = withdrawalFee < 2 ? 2 : withdrawalFee;
        }

        return withdrawalFee;
    }

    public int findRewardsForTheAccount(String accountType, int amount){
        int rewardPoints = 0;
        if(accountType.equals("STANDARD")) {
            if (amount < 100) {
                rewardPoints = 0;
            } else {
                rewardPoints = amount / 100;
            }
        }else if(accountType.equals("METAL")){
            if (amount < 100) {
                rewardPoints = 0;
            } else {
                rewardPoints = (amount / 100) * 2;
            }
        }else if(accountType.equals("BUSINESS")){
            if (amount < 100) {
                rewardPoints = 0;
            } else {
                rewardPoints = (amount / 100) * 3;
            }
        }
        return rewardPoints;
    }

    public static void main(String[] args) {
        AccountManagementService svc = new AccountManagementService();

        System.out.println(svc.createAccount("b1", "BUSINESS"));
        System.out.println(svc.createAccount("s1", "STANDARD"));
        System.out.println(svc.deposit("b1", 10000));
        System.out.println(svc.transfer("b1", "s1", 2000));
        System.out.println(svc.getBalance("b1"));
        System.out.println(svc.getBalance("s1"));
        System.out.println(svc.getRewards("s1"));
        System.out.println(svc.withdraw("b1", 1000));
        System.out.println(svc.getBalance("b1"));

        System.out.println();

        svc = new AccountManagementService();

        System.out.println(svc.createAccount("a1", "STANDARD"));
        System.out.println(svc.createAccount("a2", "METAL"));
        System.out.println(svc.deposit("a1", 500));
        System.out.println(svc.deposit("a2", 1000));
        System.out.println(svc.withdraw("a1", 100));
        System.out.println(svc.getBalance("a1"));
        System.out.println(svc.getRewards("a2"));
        System.out.println(svc.getBalance("a2"));

        System.out.println();

        svc = new AccountManagementService();

        System.out.println(svc.createAccount("s", "STANDARD"));
        System.out.println(svc.createAccount("m", "METAL"));
        System.out.println(svc.createAccount("b", "BUSINESS"));
        System.out.println(svc.deposit("s", 1000)); // s = 1000, 10
        System.out.println(svc.deposit("m", 1000)); // m = 1000, 20
        System.out.println(svc.deposit("b", 1000)); // b = 1000, 30

        System.out.println(svc.transfer("s","m", 200)); // s = 799; m = 1200, 24
        System.out.println(svc.transfer("b","s", 300)); // b = 699, s = 1099, 13

        System.out.println(svc.getBalance("s")); // 1099
        System.out.println(svc.getBalance("m")); // 1200

        System.out.println(svc.getBalance("b")); // 698 not 699
        System.out.println(svc.getRewards("s")); // 13 not 3
        System.out.println(svc.getRewards("m")); // 24 not 4

        System.out.println(svc.getRewards("b"));

    }
}
