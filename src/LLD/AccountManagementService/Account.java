package LLD.AccountManagementService;

public class Account {
    private String accountId;
    private int accountbalance;
    private String accountType;
    private int rewardPoints;

    public Account(int rewardPoints, String accountType, String accountId, int accountbalance) {
        this.rewardPoints = rewardPoints;
        this.accountType = accountType;
        this.accountId = accountId;
        this.accountbalance = accountbalance;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(int accountbalance) {
        this.accountbalance = accountbalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
