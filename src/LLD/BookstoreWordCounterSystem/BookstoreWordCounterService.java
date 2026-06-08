package LLD.BookstoreWordCounterSystem;

public class BookstoreWordCounterService {
    public static void main(String[] args) {
        BookstoreWordCounter bookstore = new BookstoreWordCounter();

        bookstore.addBook(1, "the cat sat on the mat");
        bookstore.addBook(2, "the dog sat on the log");
        System.out.println(bookstore.countWord("the"));
        System.out.println(bookstore.countWord("sat"));
        System.out.println(bookstore.countChar("t"));
        System.out.println(bookstore.getBookCount());
        System.out.println(bookstore.countWord("fish"));
    }
}
