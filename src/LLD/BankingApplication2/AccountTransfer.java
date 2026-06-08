package LLD.BankingApplication2;

public class AccountTransfer {

    public String accountId;
    public int transferAmount;

    public AccountTransfer(String id, int amount){
        this.accountId = id;
        this.transferAmount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }
}
