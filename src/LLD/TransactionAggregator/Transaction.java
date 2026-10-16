package LLD.TransactionAggregator;

public class Transaction {

    private int amount;
    private String merchant;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Transaction(int amount, String merchant) {
        this.amount = amount;
        this.merchant = merchant;
    }
}
