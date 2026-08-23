package Sorting;

import com.sun.source.tree.Tree;

import java.util.*;

public class CountMentionsPerUser {

    public static void main(String[] args) {
        int[] usersCounts = {2,3,1,3,5,3,4}; //
        List<List<String>> events;
        List<String> eventsList;
        String[][][] eventsArray = {
                {
                        {"MESSAGE","HERE","1"},
                        {"OFFLINE","0","2"},
                        {"MESSAGE","HERE","3"},
                        {"MESSAGE","ALL","4"}
                },
                {
                        {"MESSAGE","id0 id1","5"},
                        {"MESSAGE","ALL","10"}
                },
                {
                        {"MESSAGE","ALL","1"}
                },
                {
                        {"OFFLINE","1","10"},
                        {"MESSAGE","HERE","10"}
                },
                {
                        {"OFFLINE", "0", "10"},
                        {"OFFLINE", "1", "20"},
                        {"OFFLINE", "2", "30"},
                        {"MESSAGE", "HERE", "50"},
                        {"MESSAGE", "ALL", "100"}
                },
                {
                        {"OFFLINE","0","1"},
                        {"OFFLINE","1","1"},
                        {"OFFLINE","2","1"},
                        {"MESSAGE","HERE","2"},
                        {"MESSAGE","HERE","61"}
                },
                {
                        {"MESSAGE","ALL","1"},
                        {"MESSAGE","HERE","2"},
                        {"OFFLINE","3","3"},
                        {"MESSAGE","id0 id2","4"},
                        {"MESSAGE","HERE","5"},
                        {"MESSAGE","ALL","100"}
                }
        };

        for(int i = 0; i < usersCounts.length; i++){
            events = new ArrayList<>();
            for(String[] event : eventsArray[i]) {
                eventsList = new ArrayList<>();
                for(String e: event) {
                    eventsList.add(e);
                }
                events.add(eventsList);
            }
            System.out.println("user mentions in each event: "+Arrays.toString(findUserMentionsPerEvent(usersCounts[i], events)));
        }
    }

    public static int[] findUserMentionsPerEvent(int numberOfUsers, List<List<String>> events) {

        List<String> onlineUsersList = new ArrayList<>();
        Map<Integer, List<String>> offlineUsersMap = new HashMap<>();
        Map<String, Integer> messageCountsMap = new TreeMap<>();
        String eventType = "", recepientsType = "", remainingIndex = "";
        String[] receipients = null;
        int eventTime = 0, allMessageRecepients = 0;

        for(int i = 0 ; i < numberOfUsers; i++){
            onlineUsersList.add(i+"");
        }

        for(List<String> event : events){
            eventType = event.get(0);
            recepientsType = event.get(1);
            eventTime = Integer.parseInt(event.get(2));

            if(offlineUsersMap.containsKey(eventTime)){
                List<String> offlinersList = offlineUsersMap.get(eventTime);
                for(String userId : offlinersList){
                    if(!onlineUsersList.contains(userId)){
                        onlineUsersList.add(userId);
                    }
                }
                offlineUsersMap.remove(eventTime);
            }

            if(eventType.equals("MESSAGE")){
                if(recepientsType.equals("HERE")){
//                    System.out.println("onlineUsersList: "+onlineUsersList+" for event type: "+eventType);
                    for(int i = 0; i < onlineUsersList.size(); i++){
                        String userId = onlineUsersList.get(i);
                        if(messageCountsMap.containsKey(userId)){
                            messageCountsMap.put(userId, messageCountsMap.get(userId) + 1);
                        }else{
                            messageCountsMap.put(userId, 1);
                        }
                    }
                }else if(recepientsType.equals("ALL")){
                    if(messageCountsMap.size() == 0){
                        for(int k = 0 ; k < numberOfUsers; k++){
                            messageCountsMap.put(k+"", 1);
                        }
                    }else {
                        for (Map.Entry<String, Integer> entry : messageCountsMap.entrySet()) {
                            messageCountsMap.put(entry.getKey(), entry.getValue() + 1);
                        }
                    }
                    allMessageRecepients++;
                }else{
                    receipients = recepientsType.split(" ");
                    for(int i = 0; i < receipients.length; i++){
                        String userId = receipients[i].replace("id","");
                        if(messageCountsMap.containsKey(userId)){
                            messageCountsMap.put(userId, messageCountsMap.get(userId) + 1);
                        }else{
                            messageCountsMap.put(userId, 1);
                        }
                    }
                }
            }else if(eventType.equals("OFFLINE")){
                List<String> offlinersList;
                String receipientIndex = recepientsType;
                onlineUsersList.remove(receipientIndex);

                if(offlineUsersMap.containsKey(eventTime + 60)) {
                    offlinersList = offlineUsersMap.get(eventTime + 60);
                }else{
                    offlinersList = new ArrayList<>();
                }
                offlinersList.add(receipientIndex);
                offlineUsersMap.put(eventTime + 60, offlinersList);
            }
        }

        if(offlineUsersMap.size() > 0){
            for(Map.Entry<Integer, List<String>> entry : offlineUsersMap.entrySet()){
                List<String> offlinersList = entry.getValue();
                for(String userId: offlinersList) {
                    if(!messageCountsMap.containsKey(userId)) {
                        messageCountsMap.put(userId, allMessageRecepients);
                    }
                }
            }
        }
        int[] messageCounts = new int[numberOfUsers];
        int index = 0, recepientsCount = 0;

        if(receipients != null){
            recepientsCount = receipients.length;
            while(messageCountsMap.size() < numberOfUsers){
                remainingIndex = receipients[receipients.length - 1] + recepientsCount;
                messageCountsMap.put(remainingIndex, allMessageRecepients);
            }
        }else if(messageCountsMap.size() < numberOfUsers){
            messageCountsMap.put(remainingIndex, allMessageRecepients);
        }

        if(numberOfUsers > 0 && messageCountsMap.size() > 0){
            for(Map.Entry<String, Integer> entry : messageCountsMap.entrySet()){
                if(index < messageCounts.length){
                    messageCounts[index] = entry.getValue();
                    index++;
                }
            }
        }

        System.out.println(messageCountsMap);
        System.out.println("offlineUsersMap: "+offlineUsersMap);

        return messageCounts;
    }
}
