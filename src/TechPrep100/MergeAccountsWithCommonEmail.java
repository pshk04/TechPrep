package TechPrep100;

import java.util.*;

public class MergeAccountsWithCommonEmail {

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        emails.add("John");
        emails.add("johnsmith@mail.com");
        emails.add("john_newyork@mail.com");
        accounts.add(emails);
        emails = new ArrayList<>();
        emails.add("John");
        emails.add("johnsmith@mail.com");
        emails.add("john00@mail.com");
        accounts.add(emails);
        emails = new ArrayList<>();
        emails.add("Mary");
        emails.add("mary@mail.com");
        accounts.add(emails);
        emails = new ArrayList<>();
        emails.add("John");
        emails.add("johnnybravo@mail.com");
        accounts.add(emails);

        for(List<String> groupedEmails : accountsMerge(accounts)){
            System.out.println(groupedEmails);
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> finalGroups = new ArrayList<>();
        Map<String, List<String>> emailGroupsMap = new TreeMap<>();
        List<String> emailLists;
        Set<String> uniqueEmailsSet;
        String name = "";
        int perfixIndex = 0;
        boolean commonEmailsFound = false;

        for(List<String> emails : accounts){
            name = emails.get(0);
            if(emailGroupsMap.containsKey(name)){
                emailLists = (ArrayList) (emailGroupsMap.get(name));
                for(int i = 1; i < emails.size(); i++){
                    if(emailLists.contains(emails.get(i))){
                        uniqueEmailsSet = new HashSet<>();
                        uniqueEmailsSet.addAll(emails);
                        uniqueEmailsSet.addAll(emailLists);
                        emailLists = new ArrayList<>();
                        if(!emailLists.get(0).equals(name)) {
                            emailLists.add(name);
                        }
                        emailLists.addAll(uniqueEmailsSet);
                        Collections.sort(emailLists);
                        emailGroupsMap.put(name, emailLists);
                        commonEmailsFound = true;
                        break;
                    }
                }
                if(!commonEmailsFound){
                    perfixIndex++;
                    name = name + perfixIndex+"";
                    emailGroupsMap.put(name, emails);
                }
            }else{
                emailLists = new ArrayList<>();
                emailLists.add(name);
                for(int i = 1; i < emails.size(); i++){
                    emailLists.add(emails.get(i));
                }
                Collections.sort(emailLists);
                emailGroupsMap.put(name, emailLists);
            }
            commonEmailsFound = false;
        }
        for(Map.Entry<String, List<String>> entry : emailGroupsMap.entrySet()){
            finalGroups.add(entry.getValue());
        }
        return finalGroups;
    }
}
