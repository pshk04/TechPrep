package TechPrep100;

public class Word {
    private String word;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Word(int count, String word) {
        this.count = count;
        this.word = word;
    }
}
