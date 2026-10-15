package LLD.TransactionStateMachine;

import java.util.HashMap;
import java.util.Map;

public class TransactionStateMachine {

    private Map<String, Transaction> transactionsMap;

    public TransactionStateMachine() {
        this.transactionsMap = new HashMap<>();
    }

    public boolean initiate(String transactionId, int amount) {

        if(this.transactionsMap.containsKey(transactionId)){
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            if(currentTransaction.getAmount() == amount){
                return true;
            }else{
                return false;
            }
        }else{
            Transaction newTransaction = new Transaction(amount,transactionId,TransactionState.INITIATED);
            this.transactionsMap.put(transactionId, newTransaction);
            return true;
        }
    }

    public boolean process(String transactionId) {
        if(this.transactionsMap.containsKey(transactionId)) {
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            if (currentTransaction.getState().equals(TransactionState.PROCESSING)) {
                return true;
            } else if (currentTransaction.getState().equals(TransactionState.INITIATED)) {
                currentTransaction.setState(TransactionState.PROCESSING);
                return true;
            } else if (currentTransaction.getState().equals(TransactionState.COMPLETED) ||
                    currentTransaction.getState().equals(TransactionState.FAILED)) {
                return false;
            }
        }
        return false;
    }

    public boolean complete(String transactionId) {
        if(this.transactionsMap.containsKey(transactionId)) {
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            if (currentTransaction.getState().equals(TransactionState.COMPLETED)) {
                return true;
            } else if (currentTransaction.getState().equals(TransactionState.PROCESSING)) {
                currentTransaction.setState(TransactionState.COMPLETED);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean fail(String transactionId) {
        if(this.transactionsMap.containsKey(transactionId)) {
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            if (currentTransaction.getState().equals(TransactionState.FAILED)) {
                return true;
            } else if (currentTransaction.getState().equals(TransactionState.PROCESSING)) {
                currentTransaction.setState(TransactionState.FAILED);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String getState(String transactionId) {
        if(this.transactionsMap.containsKey(transactionId)) {
            Transaction currentTransaction = this.transactionsMap.get(transactionId);
            return currentTransaction.getState().name();
        }
        return "UNKNOWN";
    }

    public static void main(String[] args) {
        TransactionStateMachine sm = new TransactionStateMachine();

        System.out.println(sm.initiate("tx1", 100));
        System.out.println(sm.initiate("tx1", 100));
        System.out.println(sm.process("tx1"));
        System.out.println(sm.process("tx1"));
        System.out.println(sm.complete("tx1"));
        System.out.println(sm.complete("tx1"));
        System.out.println(sm.fail("tx1"));
        System.out.println(sm.getState("tx1"));

        System.out.println();

        sm = new TransactionStateMachine();

        System.out.println(sm.initiate("tx2", 250));
        System.out.println(sm.process("tx2"));
        System.out.println(sm.fail("tx2"));
        System.out.println(sm.complete("tx2"));
        System.out.println(sm.getState("tx2"));
        System.out.println(sm.getState("tx99"));
        System.out.println(sm.initiate("tx2", 999));

    }
}
