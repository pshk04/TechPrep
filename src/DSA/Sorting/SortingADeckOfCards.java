package Sorting;

import java.util.Arrays;
import java.util.List;

public class SortingADeckOfCards {

    public static void main(String[] args) {
        String[][] decksArray = {
                {
                        "AS", "2C", "KH", "TD", "5S", "3C", "JH", "9D"
                },
                {
                        "7H","7C","7S","7D"
                }
        };

        for(int i = 0 ; i < decksArray.length; i++){
            System.out.println("Sorting Decks: "+ Arrays.toString(sortDeck(decksArray[i])));
        }
    }

    public static String[] sortDeck(String[] deck) {

        List<String> customRankOrder = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q" , "K", "A");
        List<String> customSuitsOrder = Arrays.asList("C", "D", "H", "S");

        Arrays.sort(deck, (Card1, Card2) ->{
            int p1 = Integer.compare(customSuitsOrder.indexOf(Card1.charAt(1)+""), customSuitsOrder.indexOf(Card2.charAt(1)+""));
            if(p1 == 0){
                return Integer.compare(customRankOrder.indexOf(Card1.charAt(0)+""), customRankOrder.indexOf(Card2.charAt(0)+""));
            }
            return p1;
        });

        return deck;
    }
}
