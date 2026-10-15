package LLD.TransactionCategorizationService;

import java.util.*;

public class TransactionCategorizationService {
    private int transactionId;
    private Map<String, String> rulesMap;
    private Map<Integer, Transaction> transactionsMap;

    public TransactionCategorizationService() {
        this.transactionId = 1;
        this.transactionsMap = new HashMap<>();
        this.rulesMap = new LinkedHashMap<>();
    }

    public void addRule(String keyword, String category) {
        keyword = keyword.toLowerCase();
        if(this.rulesMap.containsKey(keyword)){
            this.rulesMap.remove(keyword);
            this.rulesMap.put(keyword, category);
        }else {
            this.rulesMap.put(keyword, category);
        }
    }

    public int recordTransaction(String merchant, int amount) {
        String category = "";
        int currentTransactionId = 0;
        Transaction newTransaction = null;
        merchant = merchant.toLowerCase();
        List<String> ruleMapKeyWordsList = new ArrayList<>(this.rulesMap.keySet());

        for(int i = ruleMapKeyWordsList.size() - 1; i >= 0; i--){
            String keyword = ruleMapKeyWordsList.get(i);
            if(merchant.indexOf(keyword) >= 0){
                category = this.rulesMap.get(keyword);
                newTransaction = new Transaction(this.transactionId, category, amount);
                if(!this.transactionsMap.containsKey(this.transactionId)){
                    this.transactionsMap.put(this.transactionId, newTransaction);
                    this.transactionId++;
                    currentTransactionId = newTransaction.getId();
                    break;
                }
            }
        }
        if(newTransaction == null){
            category = "Uncategorized";
            newTransaction = new Transaction(this.transactionId, category, amount);
            this.transactionsMap.put(this.transactionId, newTransaction);
            this.transactionId++;
            currentTransactionId = newTransaction.getId();
        }

        return currentTransactionId;
    }

    public void overrideCategory(int transactionId, String category) {

        if(this.transactionsMap.containsKey(transactionId)){
            Transaction newTransaction = this.transactionsMap.get(transactionId);
            newTransaction.setCategory(category);
        }
    }

    public int getCategorySpend(String category) {
        int totalSpendForCategory = 0;

        for(Map.Entry<Integer, Transaction> entry : this.transactionsMap.entrySet()){
            Transaction transaction = entry.getValue();
            if(transaction.getCategory().equals(category)){
                totalSpendForCategory += transaction.getAmount();
            }
        }
        return totalSpendForCategory;
    }

    public List<Integer> listTransactionsByCategory(String category) {
        List<Integer> transactionIdsList = new ArrayList<>();

        for(Map.Entry<Integer, Transaction> entry : this.transactionsMap.entrySet()){
            Transaction transaction = entry.getValue();
            if(transaction.getCategory().equals(category)){
                transactionIdsList.add(transaction.getId());
            }
        }

        return transactionIdsList;
    }

    public static void main(String[] args) {
        TransactionCategorizationService svc = new TransactionCategorizationService();

        svc.addRule("starbucks", "Food");
        svc.addRule("uber", "Travel");
        System.out.println(svc.recordTransaction("Starbucks Coffee", 500));
        System.out.println(svc.recordTransaction("Uber Ride 42", 1200));
        System.out.println(svc.recordTransaction("Amazon Order", 3000));
        System.out.println(svc.getCategorySpend("Food"));
        System.out.println(svc.getCategorySpend("Travel"));
        svc.overrideCategory(3, "Shopping");
        System.out.println(svc.getCategorySpend("Shopping"));
        System.out.println(svc.listTransactionsByCategory("Travel"));

        System.out.println();

        svc = new TransactionCategorizationService();

        svc.addRule("market", "Groceries");
        svc.addRule("super market", "Food");
        System.out.println(svc.recordTransaction("super market 12", 250));
        System.out.println(svc.recordTransaction("downtown market", 100));
        System.out.println(svc.getCategorySpend("Food"));
        System.out.println(svc.getCategorySpend("Groceries"));
        svc.overrideCategory(1, "Groceries");
        System.out.println(svc.listTransactionsByCategory("Groceries"));

        System.out.println();

        svc = new TransactionCategorizationService();

        svc.addRule("uber", "Travel");
        svc.addRule("uber eats", "Food");
        svc.addRule("uber", "Rideshare");
        System.out.println(svc.recordTransaction("uber eats night", 150));
        System.out.println(svc.getCategorySpend("Rideshare"));


    }
}
