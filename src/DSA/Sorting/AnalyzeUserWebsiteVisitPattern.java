package Sorting;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeUserWebsiteVisitPattern {

    public static void main(String[] args) {
        String[][] usernames = {
                {"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                {"ua","ua","ua","ub","ub","ub"}
        };
        String[][] websites = {
                {"home","about","career","home","cart","maps","home","home","about","career"},
                {"a","b","a","a","b","c"}
        };

        int[][] timestamps = {
                {1,2,3,4,5,6,7,8,9,10},
                {1,2,3,4,5,6}
        };

        for(int i = 0 ; i < usernames.length; i++){
            System.out.println("The most visited pattern is: "+ findMostVisitedPattern(usernames[i], timestamps[i], websites[i]));
            System.out.println();
        }

    }

    public static List<String> findMostVisitedPattern(String[] usernames, int[] timestamp, String[] websites){

        Map<String, List<String>> usernameSiteVisitCountMap = new HashMap<>();
        Map<String, Integer> patternCountMap = new TreeMap<>();
        List<String> sitesList;
        List<List<String>> sitesVisitList = new ArrayList<>();
        int maxCount = Integer.MIN_VALUE;
        String maxCountPattern = "";

        for(int i = 0; i < usernames.length; i++){
            String username = usernames[i];
            String websiteVisted = websites[i];

            if(usernameSiteVisitCountMap.containsKey(username)){
                sitesList = usernameSiteVisitCountMap.get(username);
            }else{
                sitesList = new ArrayList<>();
            }
            sitesList.add(websiteVisted);
            usernameSiteVisitCountMap.put(username, sitesList);
        }

        for(Map.Entry<String, List<String>> entry : usernameSiteVisitCountMap.entrySet()){
            sitesVisitList.add(entry.getValue());
        }

        for(int i = 0 ; i < sitesVisitList.size() - 1; i++){
            String pattern = sitesVisitList.get(i).stream().map(Object::toString).collect(Collectors.joining("_"));
            for(int j = i + 1; j < sitesVisitList.size(); j++){
                if(sitesVisitList.get(i).size() == 3 && sitesVisitList.get(i).size() == sitesVisitList.get(j).size()){
                    if(patternCountMap.containsKey(pattern)){
                        patternCountMap.put(pattern, patternCountMap.get(pattern) + 1);
                    }else{
                        patternCountMap.put(pattern, 1);
                    }
                }else{
                    int count = getMatchingPatternCount(sitesVisitList.get(i), sitesVisitList.get(j));
                    if(patternCountMap.containsKey(pattern)){
                        patternCountMap.put(pattern, patternCountMap.get(pattern) + 1);
                    }else{
                        patternCountMap.put(pattern, 1);
                    }
                }
            }
        }

        System.out.println("Pattern Map: "+patternCountMap);

        for(Map.Entry<String, Integer> entry : patternCountMap.entrySet()){
            int currentCount = entry.getValue();
            if(currentCount > maxCount){
                maxCountPattern = entry.getKey();
                maxCount = currentCount;
            }
        }
        System.out.println("maxCountPattern: "+maxCountPattern);

        sitesList = new ArrayList<>();
        String[] mostVistedSitesPattern = maxCountPattern.split("_");
        for(String site : mostVistedSitesPattern){
            sitesList.add(site);
        }
        return sitesList;
    }

    public static int getMatchingPatternCount(List<String> pattern1, List<String> pattern2){
        String patternA = "", patternB = "";

        if(pattern1.size() < pattern2.size()) {
            patternA = pattern1.stream().map(Object::toString).collect(Collectors.joining("_"));
            patternB = pattern2.stream().map(Object::toString).collect(Collectors.joining("_"));
        }else{
            patternB = pattern1.stream().map(Object::toString).collect(Collectors.joining("_"));
            patternA = pattern2.stream().map(Object::toString).collect(Collectors.joining("_"));
        }
        int count = 0;

        while(patternB.indexOf(patternA) >= 0){
            int beginIndex = patternB.indexOf(patternA);
            int patternALength = patternA.length();
            patternB = patternB.substring(beginIndex + patternALength);
            count++;
        }
        return count;
    }
}
