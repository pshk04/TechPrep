package LLD.CreditCardLimitManager;

public class CreditCard {

    private int cardId;
    private int cardLimit;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(int cardLimit) {
        this.cardLimit = cardLimit;
    }

    public CreditCard(int balance, int cardId, int cardLimit) {
        this.balance = balance;
        this.cardId = cardId;
        this.cardLimit = cardLimit;
    }
}
