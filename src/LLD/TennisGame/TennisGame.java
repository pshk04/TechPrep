package LLD.TennisGame;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private String player1Name;
    private String player2Name;
    private Map<String, Point> playerPointsMap;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.playerPointsMap = new HashMap<>();
        this.playerPointsMap.put(player1Name, Point.LOVE);
        this.playerPointsMap.put(player2Name, Point.LOVE);
    }

    public void pointWonBy(String playerName) {
        Point currentPoint = this.playerPointsMap.get(playerName);

        if(currentPoint.equals(Point.LOVE)){
            this.playerPointsMap.put(playerName, Point.FIFTEEN);
        } else if (currentPoint.equals(Point.FIFTEEN)) {
            this.playerPointsMap.put(playerName, Point.THIRTY);
        } else if (currentPoint.equals(Point.THIRTY)) {
            this.playerPointsMap.put(playerName, Point.FORTY);
        } else if (currentPoint.equals(Point.FORTY)) {
            if(playerName.equals(this.player1Name)){
                String otherPlayerPoint = this.playerPointsMap.get(this.player2Name).getValue();
                if(otherPlayerPoint.equals("Love") || otherPlayerPoint.equals("Fifteen") || otherPlayerPoint.equals("Thirty")){
                    this.playerPointsMap.put(playerName, Point.WIN);
                }else if(otherPlayerPoint.equals("Advantage")){
                    this.playerPointsMap.put(this.player2Name, Point.FORTY);
                }else{
                    this.playerPointsMap.put(playerName, Point.ADVANTAGE); // Max in Advantage
                }
            }else{
                String otherPlayerPoint = this.playerPointsMap.get(this.player1Name).getValue();
                if(otherPlayerPoint.equals("Love") || otherPlayerPoint.equals("Fifteen") || otherPlayerPoint.equals("Thirty")){
                    this.playerPointsMap.put(playerName, Point.WIN);
                }else if(otherPlayerPoint.equals("Advantage")){
                    this.playerPointsMap.put(this.player1Name, Point.FORTY);
                }else{
                    this.playerPointsMap.put(playerName, Point.ADVANTAGE);
                }
            }
        } else if (currentPoint.equals(Point.ADVANTAGE)) {
            this.playerPointsMap.put(playerName, Point.WIN);
        }
    }

    public String score() {

        Point player1Score = this.playerPointsMap.get(this.player1Name);
        Point player2Score = this.playerPointsMap.get(this.player2Name);

        if(player1Score.equals(player2Score)){
            if(player1Score.equals(Point.FORTY)){
                return Point.DEUCE.getValue();
            } else if (player1Score.equals(Point.LOVE)) {
                return Point.LOVE.getValue()+"-"+"All";
            } else if (player1Score.equals(Point.FIFTEEN)) {
                return Point.FIFTEEN.getValue()+"-"+"All";
            } else if (player1Score.equals(Point.THIRTY)) {
                return Point.THIRTY.getValue()+"-"+"All";
            }
        }else if(player1Score.equals(Point.WIN) && player2Score.equals(Point.FORTY)){
            return Point.WIN.getValue() + " for " + this.player1Name;
        }else if(player2Score.equals(Point.WIN) && player1Score.equals(Point.FORTY)){
            return Point.WIN.getValue() + " for " + this.player2Name;
        } else if (player1Score.equals(Point.WIN) && !player2Score.equals(Point.WIN)) {
            return Point.WIN.getValue() + " for " + this.player1Name;
        } else if (player2Score.equals(Point.WIN) && !player1Score.equals(Point.WIN)) {
            return Point.WIN.getValue() + " for " + this.player2Name;
        } else if (player1Score.equals(Point.ADVANTAGE) && player2Score.equals(Point.FORTY)) {
            return Point.ADVANTAGE.getValue() + " " +this.player1Name;
        } else if (player2Score.equals(Point.ADVANTAGE) && player1Score.equals(Point.FORTY)) {
            return Point.ADVANTAGE.getValue() + " " + this.player2Name;
        }
        return this.playerPointsMap.get(this.player1Name).getValue() + "-" + this.playerPointsMap.get(this.player2Name).getValue();
    }

    public static void main(String[] args) {

        TennisGame game = new TennisGame("Alice", "Bob");

        game.pointWonBy("Alice");
        System.out.println(game.score());
        game.pointWonBy("Bob");
        System.out.println(game.score());
        game.pointWonBy("Alice");
        game.pointWonBy("Bob");
        System.out.println(game.score());
        game.pointWonBy("Alice");
        System.out.println(game.score());
        game.pointWonBy("Bob");
        System.out.println(game.score());
        game.pointWonBy("Alice");
        System.out.println(game.score());

        System.out.println();

        game = new TennisGame("Player1", "Player2");

        game.pointWonBy("Player1");
        game.pointWonBy("Player1");
        game.pointWonBy("Player1");
        game.pointWonBy("Player1");
        System.out.println(game.score());

        System.out.println();

        game = new TennisGame("Sam", "Max");

        game.pointWonBy("Sam");
        game.pointWonBy("Max");
        game.pointWonBy("Sam");
        game.pointWonBy("Max");
        game.pointWonBy("Sam");
        game.pointWonBy("Max");
        game.pointWonBy("Max");
        game.pointWonBy("Max");
        System.out.println(game.score());

        System.out.println();

        game = new TennisGame("A", "B");

        game.pointWonBy("A");
        game.pointWonBy("B");
        System.out.println(game.score());
        game.pointWonBy("A");
        game.pointWonBy("B");
        System.out.println(game.score());
        game.pointWonBy("A");
        game.pointWonBy("B");
        System.out.println(game.score());
        game.pointWonBy("A");
        System.out.println(game.score());
        game.pointWonBy("B");
        System.out.println(game.score());
        game.pointWonBy("A");
        System.out.println(game.score());
    }
}
