package LLD.TransactionService;

public class Transaction {

    private int id;
    private long amount;
    private long timestamp;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(long amount, int id, long timestamp) {
        this.amount = amount;
        this.id = id;
        this.timestamp = timestamp;
    }
}
