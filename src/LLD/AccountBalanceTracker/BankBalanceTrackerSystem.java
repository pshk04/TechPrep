package LLD.AccountBalanceTracker;

import java.util.ArrayList;
import java.util.List;

public class BankBalanceTrackerSystem {
    public static void main(String[] args) {
        AccountBalanceTracker abt = new AccountBalanceTracker();
        List<String> transactions = new ArrayList<>();
        String[] transactionsArray = {
                "DEPOSIT Ida 60","TRANSFER Ida Jack 30"
        };

        for(int j = 0 ; j < transactionsArray.length; j++) {
            transactions.add(transactionsArray[j]);
        }
        abt.processTransactions(transactions);

        transactionsArray = new String[]{"WITHDRAW Ida 10", "DEPOSIT Jack 40", "WITHDRAW Jack 20"};
        for(int j = 0 ; j < transactionsArray.length; j++) {
            transactions.add(transactionsArray[j]);
        }
        abt.processTransactions(transactions);

        String[] users = {"Ida", "Jack"};
        for(String user : users) {
            System.out.println(abt.getUserBalance(user.toLowerCase()));
        }
    }
}
