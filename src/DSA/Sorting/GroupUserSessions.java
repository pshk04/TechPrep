package Sorting;

import java.util.*;

public class GroupUserSessions {
    public static void main(String[] args) {
        Object[][][] userSessions = {
                {
                        {"alice",0,"click"},
                        {"alice",10,"scroll"},
                        {"alice",25,"click"},
                        {"alice",60,"scroll"},
                        {"alice",70,"click"}
                },
                {
                        {"alice",0,"click"},
                        {"bob",5,"scroll"},
                        {"alice",20,"scroll"},
                        {"bob",40,"click"},
                        {"alice",55,"click"}
                },
                {
                        {"user1",0,"a"},
                        {"user1",29,"b"},
                        {"user1",58,"c"},
                        {"user1",87,"d"}
                },
                {
                        {"user1",0,"a"},
                        {"user1",30,"b"},
                        {"user1",60,"c"}
                },
                {
                        {"a",0,"x"},
                        {"b",0,"x"},
                        {"c",0,"x"},
                        {"a",10,"y"},
                        {"b",10,"y"},
                        {"c",10,"y"}
                }
        };
        List<List<Object>> userSessionsList;
        List<Object> sessionsList;

        for(int i = 0 ; i < userSessions.length; i++){
            userSessionsList = new ArrayList<>();
            for(int j = 0 ; j < userSessions[i].length; j++){
                Object[] userSession = userSessions[i][j];
                sessionsList = new ArrayList<>();
                for(Object sessionObj : userSession){
                    sessionsList.add(sessionObj);
                }
                userSessionsList.add(sessionsList);
            }
            System.out.println("Final list size: "+String.valueOf(sessionize(userSessionsList)));
        }
    }

    public static List<List<List<Object>>> sessionize(List<List<Object>> actions) {

        List<List<List<Object>>> groupedSessionObjects = new ArrayList<>();
        Map<String, List<List<List<Object>>>> userGroupedSessionsMap = new HashMap<>();
        List<List<List<Object>>> groupedSessionsObjectsList;

        Collections.sort(actions, (a, b)->{
            return Integer.compare(Integer.parseInt(String.valueOf(a.get(1))), Integer.parseInt(String.valueOf(b.get(1))));
        });

        PriorityQueue<List<List<Object>>> sessionMinHeap = new PriorityQueue<>((a, b) -> {
            int tsPerSessionComparator = Integer.compare(Integer.parseInt(String.valueOf(a.get(0).get(1))), Integer.parseInt(String.valueOf(b.get(0).get(1))));
            if(tsPerSessionComparator == 0){
                return String.valueOf(a.get(0).get(0)).compareTo(String.valueOf(b.get(0).get(0)));
            }
            return tsPerSessionComparator;
        });

        for(List<Object> userAction : actions){
            String user = String.valueOf(userAction.get(0));
            int timestamp = Integer.parseInt(String.valueOf(userAction.get(1)));
            List<List<Object>> eachUserSessionsList;

            if(userGroupedSessionsMap.containsKey(user)){
                groupedSessionsObjectsList = userGroupedSessionsMap.get(user);
                int lastActionTimeStamp = findLastActionTimeStamp(groupedSessionsObjectsList);

                if((timestamp - lastActionTimeStamp) < 30){
                    eachUserSessionsList = groupedSessionsObjectsList.get(groupedSessionsObjectsList.size() - 1);
                    eachUserSessionsList.add(userAction);
                }else{
                    eachUserSessionsList = new ArrayList<>();
                    eachUserSessionsList.add(userAction);
                    groupedSessionsObjectsList.add(eachUserSessionsList);
                    userGroupedSessionsMap.put(user, groupedSessionsObjectsList);
                }
            }else{
                groupedSessionsObjectsList = new ArrayList<>();
                eachUserSessionsList = new ArrayList<>();
                eachUserSessionsList.add(userAction);
                groupedSessionsObjectsList.add(eachUserSessionsList);
                userGroupedSessionsMap.put(user, groupedSessionsObjectsList);
            }
        }

        for(Map.Entry<String, List<List<List<Object>>>> entry : userGroupedSessionsMap.entrySet()){
            for(List<List<Object>> groupedSessionsList : entry.getValue()){
                sessionMinHeap.offer(groupedSessionsList);
            }
        }

        while(!sessionMinHeap.isEmpty()){
            groupedSessionObjects.add(sessionMinHeap.poll());
        }

        return groupedSessionObjects;
    }

    public static int findLastActionTimeStamp(List<List<List<Object>>> groupedUserSessionsList){
        int lastTimeStamp = Integer.MIN_VALUE;

        for(List<List<Object>> userSessionsList : groupedUserSessionsList){
            List<Object> userSession = userSessionsList.get(userSessionsList.size() - 1);
            int currentTimeStamp = Integer.parseInt(String.valueOf(userSession.get(1)));
            if(currentTimeStamp > lastTimeStamp){
                lastTimeStamp = currentTimeStamp;
            }
        }
        return lastTimeStamp;
    }
}
