package LLD.BetLedger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class BetLedger {

    private Map<String, Bet> betsMap;
    private Map<String, User> usersMap;

    public BetLedger() {
        this.betsMap = new HashMap<>();
        this.usersMap = new HashMap<>();
    }

    public void deposit(String userId, double amount) {
        if(!this.usersMap.containsKey(userId)){
            User user = new User(userId, amount);
            this.usersMap.put(userId, user);
        }else{
            User user = this.usersMap.get(userId);
            user.setBalance(user.getBalance() + amount);
        }
    }

    public boolean placeBet(String betId, String userId, String selectionId, double stake, double decimalOdds) {

        double betStake = 0.0;

        if (!this.betsMap.containsKey(betId) && this.usersMap.containsKey(userId)) {
            Bet bet = new Bet(betId, decimalOdds, selectionId, stake, userId);
            User user = this.usersMap.get(userId);
            betStake = bet.getStake();

            if(user.getBalance() >= betStake){
                user.setBalance(user.getBalance() - betStake);
                bet.setBetStatus("ACCEPTED");
                this.betsMap.put(betId, bet);
                return true;
            }else{
                bet.setBetStatus("REJECTED");
                this.betsMap.put(betId, bet);
                return false;
            }
        }else if(this.betsMap.containsKey(betId)){
            Bet bet = this.betsMap.get(betId);
            if(bet.getBetStatus().equals("ACCEPTED")){
                return true;
            }
        }
        return false;
    }

    public boolean settleBet(String betId, String result) {
        double totalStakeAmount = 0.0;
        String betStatus = "";

        if(this.betsMap.containsKey(betId)){
            Bet bet = this.betsMap.get(betId);
            User user = this.usersMap.get(bet.getUserId());
            betStatus = bet.getBetStatus();

            if(betStatus.equals("ACCEPTED") && !bet.getBetSettlementStatus()) {
                if (result.equals("won")) {
                    totalStakeAmount = bet.getStake() * bet.getDecimalOdds();
                    user.setBalance(user.getBalance() + totalStakeAmount);
                    bet.setBetSettlementStatus(true);
                    return true;
                } else if (result.equals("void")) {
                    totalStakeAmount = bet.getStake();
                    user.setBalance(user.getBalance() + totalStakeAmount);
                    bet.setBetSettlementStatus(true);
                    return true;
                }else{
                    bet.setBetSettlementStatus(true);
                    return true;
                }
            }
        }
        return false;
    }

    public double getBalance(String userId) {

        if(this.usersMap.containsKey(userId)){
            User user = this.usersMap.get(userId);
            return roundDecimalHalfUp(user.getBalance());
        }
        return 0.0;
    }

    public double getLiability(String selectionId) {

        double totalPotentialPayout = 0.0;

        for(Map.Entry<String, Bet> entry : this.betsMap.entrySet()){
            Bet bet = entry.getValue();
            if(bet.getSelectionId().equals(selectionId) && bet.getBetStatus().equals("ACCEPTED") && !bet.getBetSettlementStatus()){
                totalPotentialPayout += (bet.getStake() * bet.getDecimalOdds());
            }
        }
        return roundDecimalHalfUp(totalPotentialPayout);
    }

    public static double roundDecimalHalfUp(double value){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {

        BetLedger ledger = new BetLedger();

        ledger.deposit("alice", 100.0);
        System.out.println(ledger.placeBet("b1", "alice", "s1", 20.0, 2.5));
        System.out.println(ledger.placeBet("b2", "alice", "s1", 100.0, 3.0));
        System.out.println(ledger.getBalance("alice"));
        System.out.println(ledger.getLiability("s1"));
        System.out.println(ledger.settleBet("b1", "won"));
        System.out.println(ledger.settleBet("b1", "won"));
        System.out.println(ledger.getBalance("alice"));
        System.out.println(ledger.getLiability("s1"));

        System.out.println();


        ledger = new BetLedger();

        ledger.deposit("bob", 50.0);
        System.out.println(ledger.placeBet("x1", "bob", "m1", 60.0, 2.0));
        ledger.deposit("bob", 20.0);
        System.out.println(ledger.placeBet("x1", "bob", "m1", 60.0, 2.0));
        System.out.println(ledger.placeBet("x2", "bob", "m2", 30.0, 1.5));
        System.out.println(ledger.placeBet("x2", "bob", "m2", 30.0, 1.5));
        System.out.println(ledger.getBalance("bob"));
        System.out.println(ledger.settleBet("x2", "void"));
        System.out.println(ledger.getBalance("bob"));
        System.out.println(ledger.settleBet("x1", "won"));
        System.out.println(ledger.getLiability("m2"));

        System.out.println();
    }
}
