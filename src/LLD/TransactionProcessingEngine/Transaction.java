package LLD.TransactionProcessingEngine;

public class Transaction {

    private int id;
    private double amount;
    private TransactionType transactionType;
    private TransactionState transactionState;
    private double transactionFee;

    public double getAmount() {
        return amount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(double amount, int id, TransactionState transactionState, TransactionType transactionType, double fee) {
        this.amount = amount;
        this.id = id;
        this.transactionState = transactionState;
        this.transactionType = transactionType;
        this.transactionFee = fee;
    }
}
