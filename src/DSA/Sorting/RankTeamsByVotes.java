package Sorting;

import java.util.*;

public class RankTeamsByVotes {

    public static void main(String[] args) {
        String[][] votesList = {
//                {
//                        "ABC",
//                        "ACB",
//                        "ABC",
//                        "ACB",
//                        "ACB"
//                },
                {
                        "WXYZ",
                        "XYZW"
                }
        };

        for(int i = 0 ; i < votesList.length; i++){
            System.out.println("The order of the teams with highest votes: "+rankTeams(votesList[i]));
        }
    }

    public static String rankTeams(String[] votes) {
        int totalPositions = votes[0].length();
        Map<Character, int[]> positionVoteCountMap = new TreeMap<>();

        for(String vote : votes){
            for(int i = 0; i < vote.length(); i++){
                char currentChar = vote.charAt(i);
                if(!positionVoteCountMap.containsKey(currentChar)){
                    positionVoteCountMap.put(currentChar, new int[totalPositions]);
                }
                positionVoteCountMap.get(currentChar)[i]++; // W = {1,0,0,1}
            }
        }

        Character[] teams = new Character[totalPositions];
        for(int i = 0; i < totalPositions; i++){
            teams[i] = votes[0].charAt(i); // {W, X, Y, Z}
        }

        Arrays.sort(teams, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int[] charPositionsForFirstString = positionVoteCountMap.get(c1);
                int[] charPositionsForSecondString = positionVoteCountMap.get(c2);
                for(int i = 0; i < totalPositions; i++){
                    if(charPositionsForFirstString[i] != charPositionsForSecondString[i]){
                        return charPositionsForSecondString[i] - charPositionsForFirstString[i];
                    }
                }
                return Character.compare(c1, c2);
            }
        });

        StringBuilder highestVoteOrder = new StringBuilder();

        for(char ch : teams){
            highestVoteOrder.append(ch);
        }

        return highestVoteOrder.toString();
    }
}
