package LLD.BetLedger;

public class Bet {

    private String betId;
    private String userId;
    private String selectionId;
    private double stake;
    private double decimalOdds;
    private String betStatus;
    private boolean betSettlementStatus;

    public Bet(String betId, double decimalOdds, String selectionId, double stake, String userId) {
        this.betId = betId;
        this.decimalOdds = decimalOdds;
        this.selectionId = selectionId;
        this.stake = stake;
        this.userId = userId;
        this.betStatus = ""; // ACCEPTED or REJECTED
        this.betSettlementStatus = false;
    }

    public boolean getBetSettlementStatus() {
        return betSettlementStatus;
    }

    public void setBetSettlementStatus(boolean betSettlementStatus) {
        this.betSettlementStatus = betSettlementStatus;
    }

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public double getDecimalOdds() {
        return decimalOdds;
    }

    public void setDecimalOdds(double decimalOdds) {
        this.decimalOdds = decimalOdds;
    }

    public String getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(String selectionId) {
        this.selectionId = selectionId;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
