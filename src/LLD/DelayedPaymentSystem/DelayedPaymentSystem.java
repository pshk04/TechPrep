package LLD.DelayedPaymentSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelayedPaymentSystem {
    private Map<String, Payment> paymentsMap;
    private int maxRetries;

    public DelayedPaymentSystem(int maxRetries) {
        this.paymentsMap = new HashMap<>();
        this.maxRetries = maxRetries;
    }

    public boolean schedulePayment(String paymentId, double amount, int timestamp) {

        if(!this.paymentsMap.containsKey(paymentId)){
            Payment payment = new Payment(amount, paymentId, timestamp, PaymentStatus.PENDING,this.maxRetries);
            this.paymentsMap.put(paymentId, payment);
            return true;
        }
        return false;
    }

    public List<String> processPayments(int currentTimestamp) {

        Payment payment = null;
        int paymentScheduledTimestamp = 0, retriesCount = 0;
        String paymentId = "";
        double paymentAmount = 0;
        List<String> passedPaymentsList = new ArrayList<>();

        for(Map.Entry<String, Payment> entry : this.paymentsMap.entrySet()){
            payment = entry.getValue();
            paymentScheduledTimestamp = payment.getScheduledTime();
            paymentAmount = payment.getAmount();
            paymentId = payment.getId();
            retriesCount = payment.getMaxRetries();

            if(payment.getStatus().equals(PaymentStatus.PENDING)) {
                if (paymentScheduledTimestamp <= currentTimestamp) {
                    if (paymentAmount < 10000) {
                        payment.setStatus(PaymentStatus.COMPLETED);
                        passedPaymentsList.add(paymentId);
                    } else if (retriesCount > 0) {
                        payment.setMaxRetries(retriesCount - 1);
                        payment.setScheduledTime(currentTimestamp + 60);
                        passedPaymentsList.add(paymentId);
                    }
                    if (payment.getMaxRetries() == 0) {
                        payment.setStatus(PaymentStatus.FAILED);
                    }
                }
            }
        }
        return passedPaymentsList;
    }

    public String getStatus(String paymentId) {

        if(this.paymentsMap.containsKey(paymentId)){
           return this.paymentsMap.get(paymentId).getStatus().name();
        }
        return PaymentStatus.NOT_FOUND.name();
    }

    public boolean cancelPayment(String paymentId) {
        if(this.paymentsMap.containsKey(paymentId)){
            Payment payment = this.paymentsMap.get(paymentId);
            if(payment.getStatus().equals(PaymentStatus.PENDING)){
                payment.setStatus(PaymentStatus.CANCELLED);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public double getPendingAmount() {
        double totalPendingPaymentAmount = 0;

        for(Map.Entry<String, Payment> entry : this.paymentsMap.entrySet()){
            Payment payment = entry.getValue();
            if(payment.getStatus().equals(PaymentStatus.PENDING)){
                totalPendingPaymentAmount += payment.getAmount();
            }
        }
        return Math.round(totalPendingPaymentAmount * 100.00) / 100.00;
    }

    public int getPaymentRetries(String paymentId) {
        int totalRetryAttemptsMade = -1;

        if(this.paymentsMap.containsKey(paymentId)){
            totalRetryAttemptsMade = this.maxRetries - this.paymentsMap.get(paymentId).getMaxRetries();
        }
        return totalRetryAttemptsMade;
    }

    public static void main(String[] args) {
        DelayedPaymentSystem sys = new DelayedPaymentSystem(2);

        System.out.println(sys.schedulePayment("p1", 500.0, 100));
        System.out.println(sys.schedulePayment("p2", 15000.0, 100));
        System.out.println(sys.schedulePayment("p3", 200.0, 200));
        System.out.println(sys.getPendingAmount());
        System.out.println(sys.processPayments(100));
        System.out.println(sys.getStatus("p1"));
        System.out.println(sys.getStatus("p2"));
        System.out.println(sys.getStatus("p3"));
        System.out.println(sys.processPayments(160));
        System.out.println(sys.getStatus("p2"));

        System.out.println();

        sys = new DelayedPaymentSystem(3);

        System.out.println(sys.schedulePayment("pay1", 100.0, 50));
        System.out.println(sys.cancelPayment("pay1"));
        System.out.println(sys.getStatus("pay1"));
        System.out.println(sys.processPayments(50));
        System.out.println(sys.schedulePayment("pay1", 200.0, 60));
        System.out.println(sys.getPendingAmount());
    }
}
