package Sorting;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RecurringTransactions {

    public static void main(String[] args) {
        String[][][] customerTransactionsList = {
                {
                        {"t1","50","2024-01-01","Netflix"},
                        {"t2","50","2024-01-31","Netflix"},
                        {"t3","50","2024-03-01","Netflix"},
                        {"t4","25","2024-01-15","Spotify"},
                        {"t5","25.5","2024-02-14","Spotify"},
                        {"t6","100","2024-01-10","Amazon"}
                },
                {
                        {"a1","10","2024-03-01","Gym"},
                        {"a2","10.5","2024-03-31","Gym"},
                        {"a3","10.25","2024-04-30","Gym"},
                        {"a4","10","2024-05-30","Gym"}
                },
                {
                        {"c1","15","2024-01-01","MerchA"},
                        {"c2","15","2024-03-01","MerchA"}
                },
                {
                        {"e1","5","2024-01-01","Coffee"},
                        {"e2","5","2024-01-02","Coffee"},
                        {"e3","5","2024-01-03","Coffee"}
                }
        };

        List<List<String>> transactionsList;
        List<String> merchantTransaction;

        for(String[][] transactions : customerTransactionsList){
            transactionsList = new ArrayList<>();
            for(String[] transaction : transactions){
                merchantTransaction = new ArrayList<>();
                for(String transactionItem : transaction){
                    merchantTransaction.add(transactionItem);
                }
                transactionsList.add(merchantTransaction);
            }
            System.out.println(findRecurringTransactions(transactionsList));
        }
    }

    public static List<List<String>> findRecurringTransactions(List<List<String>> transactions) {

        Map<String, List<List<String>>> merchantTransactionsMap = new HashMap<>();
        List<List<String>> transactionsList;
        String merchant = "";

        for(List<String> transaction : transactions){
            merchant = transaction.get(3);
            if(merchantTransactionsMap.containsKey(merchant)){
                transactionsList = merchantTransactionsMap.get(merchant);
                transactionsList.add(transaction);
            }else{
                transactionsList = new ArrayList<>();
                transactionsList.add(transaction);
                merchantTransactionsMap.put(merchant, transactionsList);
            }
        }

        transactionsList = new ArrayList<>();

        for(Map.Entry<String, List<List<String>>> merchantTransactionsEntry : merchantTransactionsMap.entrySet()){
            List<String> sortedRecurringTransactions = findRecurringTransactionsForMerchant(merchantTransactionsEntry.getValue());
            if(sortedRecurringTransactions.size() >= 2) {
                transactionsList.add(sortedRecurringTransactions);
            }
        }

        Collections.sort(transactionsList, (a, b) ->{
            return a.get(0).compareTo(b.get(0));
        });

        return transactionsList;
    }

    public static List<String> findRecurringTransactionsForMerchant(List<List<String>> merchantTransactions){
        List<String> recurringTransactions = new ArrayList<>();

        Collections.sort(merchantTransactions, (a, b) ->{
            return LocalDate.parse(a.get(2)).compareTo(LocalDate.parse(b.get(2)));
        });

        List<String> previousMerchantTransaction = merchantTransactions.get(0);
        recurringTransactions.add(previousMerchantTransaction.get(0));
        LocalDate currentMerchantTransactionDate, previousMerchantTransactionDate;
        List<String> currentMerchantTransaction;
        float currentTransactionAmount = 0f, previousTransactionAmount = Float.valueOf(merchantTransactions.get(0).get(1));

        for(int i = 1 ; i < merchantTransactions.size(); i++){
            currentMerchantTransaction = merchantTransactions.get(i);
            currentMerchantTransactionDate = LocalDate.parse(currentMerchantTransaction.get(2));
            previousMerchantTransactionDate = LocalDate.parse(previousMerchantTransaction.get(2));
            Period period = Period.between(previousMerchantTransactionDate, currentMerchantTransactionDate);
            int yearsBetweenTransactions = period.getYears();
            int monthsBetweenTransactions = period.getMonths();
            int daysBetweenTransactions = period.getDays();

            currentTransactionAmount = Float.valueOf(currentMerchantTransaction.get(1));

            if(daysBetweenTransactions <= 30 && monthsBetweenTransactions <= 1 && yearsBetweenTransactions == 0) {

                if ((Math.abs(currentTransactionAmount - previousTransactionAmount) <= 1.0)) {
                    if(daysBetweenTransactions == 1 && monthsBetweenTransactions == 0) {
                        previousMerchantTransaction = currentMerchantTransaction;
                        continue;
                    }else{
                        recurringTransactions.add(currentMerchantTransaction.get(0));
                    }
                }
            }
            previousMerchantTransaction = currentMerchantTransaction;
        }

        return recurringTransactions;
    }
}
