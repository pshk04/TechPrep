package LLD.DelayedPaymentSystem;

public class Payment {

    private String id;
    private double amount;
    private int scheduledTime;
    private PaymentStatus status;
    private int maxRetries;

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Payment(double amount, String id, int scheduledTime, PaymentStatus status, int maxRetries) {
        this.amount = amount;
        this.id = id;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.maxRetries = maxRetries;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(int scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
