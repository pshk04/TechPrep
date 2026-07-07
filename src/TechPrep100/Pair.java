package TechPrep100;

public class Pair<S, I extends Number> {
    private String word;
    private int count;

    public Pair(String beginWord, int i) {
        this.word = beginWord;
        this.count = i;
    }

    public String getKey() {
        return this.word;
    }

    public int getValue() {
        return this.count;
    }

    public String toString(){
        return this.getKey() +" : "+this.getValue();
    }
}
