package LLD.TransactionCategorizationService;

public class Transaction {

    private int id;
    private String category;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(int id, String category, int amount) {
        this.id = id;
        this.category = category;
        this.amount = amount;
    }
}
