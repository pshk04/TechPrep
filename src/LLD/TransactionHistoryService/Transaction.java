package LLD.TransactionHistoryService;

public class Transaction {

    private int id;
    private String type;
    private int amount;
    private int timestamp;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Transaction(int amount, int id, int timestamp, String type) {
        this.amount = amount;
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
    }
}
