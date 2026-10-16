package LLD.Leaderboard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leaderboard {

    private Map<Integer, Player> playersMap;

    public Leaderboard() {
        this.playersMap = new HashMap<>();
    }

    public void addScore(int playerId, int score) {

        if(!this.playersMap.containsKey(playerId)){
            Player player = new Player(playerId, score);
            this.playersMap.put(playerId, player);
        }else{
            Player player = this.playersMap.get(playerId);
            player.setScore(player.getScore() + score);
        }
    }

    public int top(int K) {
        int topKPlayersScore = 0;

        PriorityQueue<int[]> playersScoreMaxHeap = new PriorityQueue<>((a,b) -> {
            int playerScoreComparator = Integer.compare(b[1], a[1]);
            if(playerScoreComparator == 0){
                return Integer.compare(a[0], b[0]);
            }

            return playerScoreComparator;
        });

        for(Map.Entry<Integer, Player> entry : this.playersMap.entrySet()){
            Player player = entry.getValue();
            playersScoreMaxHeap.offer(new int[]{player.getId(), player.getScore()});
        }

        while(!playersScoreMaxHeap.isEmpty() && K > 0){
            topKPlayersScore += playersScoreMaxHeap.poll()[1];
            K--;
        }

        return topKPlayersScore;
    }

    public void reset(int playerId) {

        if(this.playersMap.containsKey(playerId)){
            Player player = this.playersMap.get(playerId);
            player.setScore(0);
        }
    }

    public static void main(String[] args) {
        Leaderboard board = new Leaderboard();

        board.addScore(1, 73);
        board.addScore(2, 56);
        board.addScore(3, 39);
        board.addScore(4, 51);
        board.addScore(5, 4);
        System.out.println(board.top(1));
        board.reset(1);
        board.reset(2);
        board.addScore(2, 51);
        System.out.println(board.top(3));

        System.out.println();

        board = new Leaderboard();

        board.addScore(1, 10);
        board.addScore(2, 20);
        System.out.println(board.top(2));
        board.addScore(1, 15);
        System.out.println(board.top(1));
        board.reset(2);
        System.out.println(board.top(1));
    }
}
