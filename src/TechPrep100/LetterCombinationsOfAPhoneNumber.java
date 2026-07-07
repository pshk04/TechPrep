package TechPrep100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    static Map<Integer, String> digitMap = new HashMap<>();
    public static void main(String[] args) {
        String[] digits = {
//                "2",
                "79"
//                "23",
//                "234",
//                "2345"
        };


        for(String digitCombination : digits){
            System.out.println(letterCombinations(digitCombination));
        }
    }

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }

        Map<Character, String> phoneMap = new HashMap<Character, String>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        // Iterative BFS-style approach
        List<String> result = new ArrayList<String>();
        result.add("");

        for (int i = 0; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            String letters = phoneMap.get(digit);
            List<String> newResult = new ArrayList<String>();
            for (String combo : result) {
                for (int j = 0; j < letters.length(); j++) {
                    newResult.add(combo + letters.charAt(j));
                }
            }
            result = newResult;
        }

        return result;
    }

    public static List<String> letterCombination(String digits){
        digitMap.put(2,"abc");
        digitMap.put(3,"def");
        digitMap.put(4,"ghi");
        digitMap.put(5,"jkl");
        digitMap.put(6,"mno");
        digitMap.put(7,"pqrs");
        digitMap.put(8,"tuv");
        digitMap.put(9,"wxyz");

        if(digits.length() == 0){
            return new ArrayList<>();
        }else if(digits.length() == 1){
            List<String> list = new ArrayList<>();
            String chars = digitMap.get(Integer.parseInt(digits));
            for(int i = 0 ; i < chars.length(); i++){
                list.add(chars.charAt(i)+"");
            }

            return list;
        }

        StringBuilder result = new StringBuilder();
        String firstWord = "", secondWord = "", thirdWord = "", fourthWord = "";
        List<String> finalList = new ArrayList<>();
        String[] words = new String[digits.length()];

        for(int i = 0 ; i < digits.length(); i++){
            words[i] = digitMap.get(digits.charAt(i));
        }

        if(digits.length() == 2) {
            firstWord = digitMap.get(Integer.parseInt(digits.charAt(0)+""));
            secondWord = digitMap.get(Integer.parseInt(digits.charAt(1)+""));

            for (int i = 0; i < firstWord.length(); i++) {
                result.append(firstWord.charAt(i));
                for (int j = 0; j < secondWord.length(); j++) {
                    result.append(secondWord.charAt(j));
                    finalList.add(result.toString());
                    result = new StringBuilder();
                    result.append(firstWord.charAt(i));
                }
                result = new StringBuilder();
            }
        }else if(digits.length() == 3) {
            firstWord = digitMap.get(Integer.parseInt(digits.charAt(0)+""));
            secondWord = digitMap.get(Integer.parseInt(digits.charAt(1)+""));
            thirdWord = digitMap.get(Integer.parseInt(digits.charAt(2)+""));

            for (int i = 0; i < firstWord.length(); i++) {
                result.append(firstWord.charAt(i));
                for (int j = 0; j < secondWord.length(); j++) {
                    result.append(secondWord.charAt(j));
                    for (int k = 0; k < thirdWord.length(); k++) {
                        result.append(thirdWord.charAt(k));
                        finalList.add(result.toString());
                        result = new StringBuilder();
                        result.append(firstWord.charAt(i)).append(secondWord.charAt(j));
                    }
                    result = new StringBuilder();
                    result.append(firstWord.charAt(i));
                }
                result = new StringBuilder();
            }
        }else if(digits.length() == 4) {
            firstWord = digitMap.get(Integer.parseInt(digits.charAt(0)+""));
            secondWord = digitMap.get(Integer.parseInt(digits.charAt(1)+""));
            thirdWord = digitMap.get(Integer.parseInt(digits.charAt(2)+""));
            fourthWord = digitMap.get(Integer.parseInt(digits.charAt(3)+""));

            for (int i = 0; i < firstWord.length(); i++) {
                result.append(firstWord.charAt(i));
                for (int j = 0; j < secondWord.length(); j++) {
                    result.append(secondWord.charAt(j));
                    for (int k = 0; k < thirdWord.length(); k++) {
                        result.append(thirdWord.charAt(k));
                        for (int l = 0; l < fourthWord.length(); l++) {
                            result.append(fourthWord.charAt(l));
                            finalList.add(result.toString());
                            result = new StringBuilder();
                            result.append(firstWord.charAt(i)).append(secondWord.charAt(j)).append(thirdWord.charAt(k));
                        }
                        result = new StringBuilder();
                        result.append(firstWord.charAt(i)).append(secondWord.charAt(j));
                    }
                    result = new StringBuilder();
                    result.append(firstWord.charAt(i));
                }
                result = new StringBuilder();
            }
        }
        return finalList;
    }
}
