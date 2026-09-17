package Heaps;

import java.util.*;
import java.util.stream.Collectors;

public class MergeSortedStreams {

    public static void main(String[] args) {
        int[][][][] streams = {
                {
                        {{1,101},{3,103},{5,105}},
                        {{2,102},{4,104},{6,106}},
                        {{1,107},{3,103},{7,108}}
                },
                {
                        {{1,1},{2,2}},
                        {{1,1},{2,2}}
                },
                {
                        {{1,5},{3,6}},
                        {{2,5},{4,7}},
                        {{1,8},{5,6}}
                }
        };

        for(int i = 0 ; i < streams.length; i++){
            for(int[] mergedStream : mergeStreams(streams[i])){
                System.out.println(Arrays.toString(mergedStream));
            }
            System.out.println();
        }
    }

    public static int[][] mergeStreams(int[][][] streams) {
        Map<Integer, Set<Integer>> streamsMap = new TreeMap<>();
        int messageTimeStamp = 0, messageId = 0, index = 0, totalSize = 0;
        Set<Integer> messageSet;
        Set<Integer> uniqueMessageSet = new HashSet<>();
        int[][] sortedStreams;

        for(int[][] stream : streams){
            for(int j = 0 ; j < stream.length; j++) {
                messageTimeStamp = stream[j][0];
                messageId = stream[j][1];
                if (streamsMap.containsKey(messageTimeStamp)){
                    messageSet = streamsMap.get(messageTimeStamp);
                }else{
                    messageSet = new TreeSet<>();
                }
                if(!messageSet.contains(messageId) && !uniqueMessageSet.contains(messageId)){
                    totalSize++;
                }
                if(!uniqueMessageSet.contains(messageId)) {
                    messageSet.add(messageId);
                    streamsMap.put(messageTimeStamp, messageSet);
                    uniqueMessageSet.add(messageId);
                }
            }
        }
        sortedStreams = new int[totalSize][0];

        for(Map.Entry<Integer, Set<Integer>> entry : streamsMap.entrySet()) {
            messageSet = entry.getValue();
            messageTimeStamp = entry.getKey();
            for (int currentMessageId : messageSet) {
                if (index < totalSize){
                    sortedStreams[index] = new int[]{messageTimeStamp, currentMessageId};
                    index++;
                }else{
                    break;
                }
            }
        }
        return sortedStreams;
    }
}
