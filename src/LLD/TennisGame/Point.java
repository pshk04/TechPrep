package LLD.TennisGame;

public enum Point {
    LOVE("Love"),
    FIFTEEN("Fifteen"),
    THIRTY("Thirty"),
    FORTY("Forty"),
    DEUCE("Deuce"),
    ADVANTAGE("Advantage"),
    WIN("Win");

    private final String value;

    Point(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
