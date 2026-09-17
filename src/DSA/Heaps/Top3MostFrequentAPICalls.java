package Heaps;

import java.util.*;

public class Top3MostFrequentAPICalls {

    public static void main(String[] args) {
        String[][] apiCallsArray = {
                {
                        "/api/users",
                        "/api/orders",
                        "/api/users",
                        "/api/products",
                        "/api/orders",
                        "/api/users",
                        "/api/products",
                        "/api/orders",
                        "/api/health"
                },
                {
                        "/api/login",
                        "/api/login",
                        "/api/logout",
                        "/api/logout",
                        "/api/dashboard",
                        "/api/dashboard",
                        "/api/settings"
                }
        };

        for(String[] apiCalls : apiCallsArray){
            List<String> apiCallsList = new ArrayList<>();
            for(String apiCall : apiCalls){
                apiCallsList.add(apiCall);
            }
            System.out.println("The API calls sorted by frequency and lexicographically: "+ topThreeApiCalls(apiCallsList));
        }
    }

    public static List<String> topThreeApiCalls(List<String> logs) {
        Map<String, Integer> apiCallsCountMap = new TreeMap<>();
        PriorityQueue<String[]> apiCallsMaxHeap = new PriorityQueue<>((a,b) -> {
            int apiCallFrequency = Integer.compare(java.lang.Integer.parseInt(b[1]), Integer.parseInt(a[1]));
            if(apiCallFrequency == 0){
                return a[0].compareTo(b[0]);
            }
            return apiCallFrequency;
        });

        for(String apiCall : logs){
            if(apiCallsCountMap.containsKey(apiCall)){
                apiCallsCountMap.put(apiCall, apiCallsCountMap.get(apiCall) + 1);
            }else{
                apiCallsCountMap.put(apiCall, 1);
            }
        }

        for(Map.Entry<String, Integer> entry : apiCallsCountMap.entrySet()){
            apiCallsMaxHeap.offer(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
        }
        List<String> result = new ArrayList<>();
        int count = 0;
        while(!apiCallsMaxHeap.isEmpty()){
            if(count < 3) {
                result.add(apiCallsMaxHeap.poll()[0]);
            }else{
                break;
            }
            count++;
        }

        return result;
    }
}
