package LLD.BrowserHistory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BrowserHistory {

    private List<String> visitedLinks;

    public BrowserHistory() {
        this.visitedLinks = new LinkedList<>();
    }

    public void add(String link) {

        this.visitedLinks.add(link);
    }

    public void remove(String link) {

        this.visitedLinks.remove(this.visitedLinks.indexOf(link));
    }

    public List<String> searchByKeyword(String keyword) {
        List<String> linksWithKeyWordList = new ArrayList<>();

        for(String link : this.visitedLinks){
            if(link.indexOf(keyword) >= 0){
                linksWithKeyWordList.add(link);
            }
        }
        return linksWithKeyWordList;
    }

    public List<String> printInOrder() {
        List<String> linksInOrderList = new LinkedList<>(this.visitedLinks);
        return linksInOrderList; // does not pass all tests if this.visitedLinks is returned!!
    }

    public static void main(String[] args) {

        BrowserHistory history = new BrowserHistory();

        history.add("leetcode.com");
        history.add("github.com");
        history.add("leetcode.com/problems");
        System.out.println(history.printInOrder());
        System.out.println(history.searchByKeyword("leetcode"));
        history.remove("github.com");
        System.out.println(history.printInOrder());
        System.out.println();


        history = new BrowserHistory();

        history.add("a.com");
        history.add("a.com");
        history.add("b.com");
        history.remove("a.com");
        System.out.println(history.searchByKeyword("a"));
        System.out.println(history.printInOrder());

    }
}
