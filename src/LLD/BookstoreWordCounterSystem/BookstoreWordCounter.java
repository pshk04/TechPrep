package LLD.BookstoreWordCounterSystem;

import java.util.HashMap;
import java.util.Map;

public class BookstoreWordCounter {
    private Map<Integer, String> booksMap;

    public BookstoreWordCounter() {
        this.booksMap = new HashMap<>();
    }

    public void addBook(int bookId, String text) {
        this.booksMap.put(bookId, text);
    }

    public int countWord(String word) {
        int wordCount = 0;
        for(int id : this.booksMap.keySet()){
            String text = this.booksMap.get(id);
            for(String wordInBook : text.split(" ")){
                if(word.equals(wordInBook)){
                    wordCount++;
                }
            }
        }

        return wordCount;
    }

    public int countChar(String ch) {
        int charCount = 0;
        for(int id : this.booksMap.keySet()){
            String text = this.booksMap.get(id);
            for(String wordInBook : text.split(" ")){
                if(wordInBook.indexOf(ch) >= 0){
                    for(char c : wordInBook.toCharArray()){
                        if(ch.equals(c+"")){
                            charCount++;
                        }
                    }
                }
            }
        }
        return charCount;
    }

    public int getBookCount() {
        return this.booksMap.size();
    }

}
