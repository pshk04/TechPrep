package LLD.CreditCardLimitManager;

import java.util.HashMap;
import java.util.Map;

public class CreditCardLimitManager {

    private Map<Integer, CreditCard> creditCardsMap;

    public CreditCardLimitManager() {
        this.creditCardsMap = new HashMap<>();
    }

    public boolean addCard(int cardId, int limit) {

        if(!this.creditCardsMap.containsKey(cardId)){
            CreditCard creditCard = new CreditCard(0, cardId, limit);
            this.creditCardsMap.put(cardId, creditCard);
            return true;
        }
        return false;
    }

    public boolean charge(int cardId, int amount) {
        int outstandingBalance = 0;

        if(this.creditCardsMap.containsKey(cardId)){
            CreditCard creditCard = this.creditCardsMap.get(cardId);
            outstandingBalance = creditCard.getBalance() + amount;
            if(outstandingBalance <= creditCard.getCardLimit()){
                creditCard.setBalance(outstandingBalance);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean pay(int cardId, int amount) {

        if(this.creditCardsMap.containsKey(cardId)){
            CreditCard creditCard = this.creditCardsMap.get(cardId);
            if(creditCard.getBalance() >= amount){
                creditCard.setBalance(creditCard.getBalance() - amount);
                return true;
            } else if (amount > creditCard.getBalance()) {
                creditCard.setBalance(0);
                return true;
            } else{
                return false;
            }
        }
        return false;
    }

    public long availableCredit(int cardId) {

        if(this.creditCardsMap.containsKey(cardId)){
            CreditCard creditCard = this.creditCardsMap.get(cardId);
            return creditCard.getCardLimit() - creditCard.getBalance();
        }
        return -1;
    }

    public long totalAvailableCredit() {
        int totalAvailableCredit = 0;

        for(Map.Entry<Integer, CreditCard> entry : this.creditCardsMap.entrySet()){
            CreditCard creditCard = entry.getValue();
            totalAvailableCredit += (creditCard.getCardLimit() - creditCard.getBalance());
        }

        return totalAvailableCredit;
    }

    public static void main(String[] args) {
        CreditCardLimitManager mgr = new CreditCardLimitManager();

        System.out.println(mgr.addCard(1, 1000));
        System.out.println(mgr.addCard(2, 500));
        System.out.println(mgr.charge(1, 400));
        System.out.println(mgr.charge(2, 600));
        System.out.println(mgr.availableCredit(1));
        System.out.println(mgr.pay(1, 150));
        System.out.println(mgr.availableCredit(1));
        System.out.println(mgr.totalAvailableCredit());

        System.out.println();

        mgr = new CreditCardLimitManager();

        System.out.println(mgr.addCard(10, 2000));
        System.out.println(mgr.addCard(10, 9999));
        System.out.println(mgr.charge(10, 2000));
        System.out.println(mgr.pay(10, 5000));
        System.out.println(mgr.availableCredit(10));
        System.out.println(mgr.charge(10, 1));
        System.out.println(mgr.availableCredit(99));

        System.out.println();

    }
}
