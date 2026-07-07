package TechPrep100;

import java.util.*;

public class RemoveInvalidParenthesis {

    public static void main(String[] args) {
        String[] strArray = {"()())()"};
        for(String expression : strArray) {
            System.out.println("The possible valid parenthesis are: ");
            for(String validString : removeInvalidPrenthesis(expression)) {
                System.out.print(validString+" ");
            }
            System.out.println();
        }
    }

    public static List<String> removeInvalidPrenthesis(String s){
        if(s == null || s.length() == 0){
            return new ArrayList<>();
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        boolean found = false;

        visited.add(s);
        queue.offer(s);

        while(!queue.isEmpty() && !found){
            int size = queue.size();


            for(int i = 0 ; i < size; i++){
                String currentString = queue.poll();
                System.out.println("Current substring is: "+currentString);

                if(isValidString(currentString)){
                    result.add(currentString);
                    found = true;
                }
                if(found){
                    continue;
                }

                System.out.println("Processing substrings of: "+currentString);

                for(int j = 0; j < currentString.length(); j++){
                    if(currentString.charAt(j) != '(' && currentString.charAt(j) != ')'){
                        continue;
                    }
                    String subString = currentString.substring(0, j) + currentString.substring(j + 1);
                    if(!visited.contains(subString)) {
                        visited.add(subString);
                        queue.offer(subString);
                        System.out.println("Adding substring to Queue: "+subString);
                    }
                }
            }
        }
        return result;
    }

    public static boolean isValidString(String s){
        int count = 0;

        for(char c : s.toCharArray()){
            if(c == '('){
                count++;
            }else if(c == ')'){
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        return (count == 0);
    }
}
