package Sorting;

import java.util.*;

public class BadgeAccessLogs {

    public static void main(String[] args) {
        String[][][] accessLogs = {
                {
                        {"alice", "100", "enter"},
                        {"bob", "120", "enter"},
                        {"alice", "130", "exit"},
                        {"bob", "140", "enter"},
                        {"alice", "160", "enter"},
                        {"alice", "200", "enter"},
                        {"bob", "300", "exit"}
                },
                {
                        {"carl","10","enter"},
                        {"carl","30","exit"},
                        {"carl","50","enter"},
                        {"carl","60","enter"},
                        {"carl","70","enter"},
                        {"dave","5","exit"}
                }
        };

        System.out.println("The mimatched and frequent users lists are:");

        for(int i = 0 ; i < accessLogs.length; i++) {
            for (List<String> usersList : findMimatchedAndFrequentEntries(accessLogs[i])) {
                System.out.println(usersList);
            }
        }
    }

    public static List<List<String>> findMimatchedAndFrequentEntries(String[][] accessLogs){
        Map<String, Integer> userEntryExitCountMap = new HashMap<>();
        Map<String, List<Integer>> userFrequencyCountMap = new HashMap<>();
        List<String> mismatchedEntryUsersList = new ArrayList<>();
        List<String> frequentUsersList = new ArrayList<>();
        List<List<String>> usersList = new ArrayList<>();
        List<Integer> timesList;
        Arrays.sort(accessLogs, (a,b)->a[1].compareTo(b[1]));

        for(String[] accessLog : accessLogs){
            String user = accessLog[0];
            int entryTime = Integer.parseInt(accessLog[1]);
            String event = accessLog[2];

            if(userEntryExitCountMap.containsKey(user)){
                if(event.equals("enter")){
                    userEntryExitCountMap.put(user, userEntryExitCountMap.get(user) + 1);
                }else{
                    userEntryExitCountMap.put(user, userEntryExitCountMap.get(user) - 1);
                }
            }else{
                if(event.equals("enter")){
                    userEntryExitCountMap.put(user, 1);
                }else{
                    userEntryExitCountMap.put(user, -1);
                }
            }

            if(event.equals("enter")) {
                if (userFrequencyCountMap.containsKey(user)) {
                    timesList = userFrequencyCountMap.get(user);
                } else {
                    timesList = new ArrayList<>();
                }
                timesList.add(entryTime);
                userFrequencyCountMap.put(user, timesList);
            }
        }

        for(Map.Entry<String, Integer> entry : userEntryExitCountMap.entrySet()){
            if(entry.getValue() != 0){
                mismatchedEntryUsersList.add(entry.getKey());
            }
        }
        Collections.sort(mismatchedEntryUsersList);
        usersList.add(mismatchedEntryUsersList);

        for(Map.Entry<String, List<Integer>> entry : userFrequencyCountMap.entrySet()){
            List<Integer> entryTimesList = entry.getValue();
            int frequentEntry = 1, remainingTime = 60;

            for(int i = entryTimesList.size() - 1; i > 0; i--){
                if((entryTimesList.get(i) - entryTimesList.get(i - 1)) < 60){
                    remainingTime -= (entryTimesList.get(i) - entryTimesList.get(i - 1));
                    if(remainingTime > 0) {
                        frequentEntry++;
                    }
                }
                if(frequentEntry == 3){
                    frequentUsersList.add(entry.getKey());
                    break;
                }
            }
        }
        Collections.sort(frequentUsersList);
        usersList.add(frequentUsersList);
        return usersList;
    }
}
