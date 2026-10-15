package LLD.TransactionProcessingEngine;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TransactionProcessingEngine {

    private Map<Integer, Transaction> transactionsMap;
    private int transactionId;

    public TransactionProcessingEngine() {
        this.transactionsMap = new HashMap<>();
        this.transactionId = 1;
    }

    public int process(String type, double amount) {
        double fee = calculateFee(TransactionType.valueOf(type), amount);
        Transaction newTransaction = new Transaction(amount, this.transactionId, TransactionState.PROCESSED, TransactionType.valueOf(type),fee);
        this.transactionsMap.put(this.transactionId, newTransaction);
        this.transactionId++;
        return newTransaction.getId();

    }

    public String getStatus(int txnId) {
        if(this.transactionsMap.containsKey(txnId)){
            return this.transactionsMap.get(txnId).getTransactionState().name();
        }else{
            return TransactionState.NOT_FOUND.name();
        }
    }

    public double getFee(int txnId) {
        double fee = 0.0;
        if(this.transactionsMap.containsKey(txnId)){
            Transaction transaction = this.transactionsMap.get(txnId);
            BigDecimal bd = new BigDecimal(Double.toString(transaction.getTransactionFee()));
            fee = bd.setScale(2, RoundingMode.CEILING).doubleValue();
            return fee;
        }
        return -1;
    }

    public double getNetAmount(int txnId) {
        double netAmount = -1;

        if(this.transactionsMap.containsKey(txnId)){
            Transaction transaction = this.transactionsMap.get(txnId);
            netAmount = transaction.getAmount() - transaction.getTransactionFee();
            BigDecimal bd = new BigDecimal(Double.toString(netAmount));
            netAmount = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
            return netAmount;
        }
        return netAmount;
    }

    public double getTotalFees(String type) {
        double totalTransactionFeesCollected = 0;

        for(Map.Entry<Integer, Transaction> entry : this.transactionsMap.entrySet()){
            Transaction currentTransaction = entry.getValue();
            if(currentTransaction.getTransactionType().equals(TransactionType.valueOf(type))){
                totalTransactionFeesCollected += currentTransaction.getTransactionFee();
            }
        }
        BigDecimal bd = new BigDecimal(Double.toString(totalTransactionFeesCollected));
        totalTransactionFeesCollected = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return totalTransactionFeesCollected;
    }

    public double calculateFee(TransactionType type, double amount){
        double currentFee = 0;

        if(type.equals(TransactionType.ACH)){
            currentFee = 0.25;
        }else if(type.equals(TransactionType.WIRE)){
            currentFee = ((0.10/100.00) * amount) + 15.00;
        }else if(type.equals(TransactionType.CARD)){
            currentFee = ((2.90/100.00) * amount) + 0.30;
        }

        return currentFee;
    }

    public static void main(String[] args) {
        TransactionProcessingEngine engine = new TransactionProcessingEngine();

        System.out.println(engine.process("ACH", 1000.0));
        System.out.println(engine.process("WIRE", 5000.0));
        System.out.println(engine.process("CARD", 100.0));
        System.out.println(engine.getFee(1));
        System.out.println(engine.getFee(2));
        System.out.println(engine.getFee(3));
        System.out.println(engine.getNetAmount(3));
        System.out.println(engine.getTotalFees("WIRE"));
        System.out.println(engine.getTotalFees("CARD"));

        System.out.println();

        engine = new TransactionProcessingEngine();

        System.out.println(engine.process("ACH", 50.0));
        System.out.println(engine.process("ACH", 200.0));
        System.out.println(engine.getStatus(1));
        System.out.println(engine.getStatus(99));
        System.out.println(engine.getFee(99));
        System.out.println(engine.getTotalFees("ACH"));
        System.out.println(engine.getNetAmount(2));

        System.out.println();

        engine = new TransactionProcessingEngine();

        System.out.println(engine.process("ACH", 1));
        System.out.println(engine.process("ACH", 2));
        System.out.println(engine.process("WIRE", 100));
        System.out.println(engine.process("WIRE", 200));
        System.out.println(engine.process("CARD", 50));
        System.out.println(engine.process("CARD", 75));
        System.out.println(engine.getTotalFees("ACH"));
        System.out.println(engine.getTotalFees("WIRE"));
        System.out.println(engine.getTotalFees("CARD"));
        System.out.println(engine.getFee(6));
        System.out.println(engine.getNetAmount(6));

        System.out.println();

        engine = new TransactionProcessingEngine();

        System.out.println(engine.process("CARD", 1));
        System.out.println(engine.getFee(1));
        System.out.println(engine.getNetAmount(1));
        System.out.println(engine.getStatus(1));

    }
}
