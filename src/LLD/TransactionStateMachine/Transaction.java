package LLD.TransactionStateMachine;

public class Transaction {

    private String id;
    private int amount;
    private TransactionState state;

    public long getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionState getState() {
        return this.state;
    }

    public void setState(TransactionState state) {
        this.state = state;
    }

    public Transaction(int amount, String id, TransactionState state) {
        this.amount = amount;
        this.id = id;
        this.state = state;
    }
}
