package Sorting;

import java.util.*;

public class RomanNumeralSort {

    public static void main(String[] args) {
        String[][] namesArray = {
                {
                        "Louis IX",
                        "Louis VIII",
                        "David II",
                        "David I"
                },
                {
                        "Henry IV",
                        "Henry I",
                        "Henry IX",
                        "Anne III",
                        "Anne I"
                }
        };
        List<String> namesList;

        for(int i = 0 ; i < namesArray.length; i++){
            namesList = new ArrayList<>();
            for(String name : namesArray[i]){
                namesList.add(name);
            }
            System.out.println("Roman Names in sorted order: "+ romanSort(namesList));
        }
    }

    public static List<String> romanSort(List<String> names) {
        List<String> result = new ArrayList<>();

        PriorityQueue<String[]> namesMinHeap = new PriorityQueue<>((a, b) -> {
            int p1 = a[0].compareTo(b[0]);
            if(p1 == 0){
                return Integer.compare(convertRomanNumberToDecimal(a[1]), convertRomanNumberToDecimal(b[1]));
            }
            return p1;
        });

        for(String name : names){
            String[] nameParts = name.split(" ");
            namesMinHeap.offer(new String[]{nameParts[0], nameParts[1]});
        }

        while(!namesMinHeap.isEmpty()){
            String[] name = namesMinHeap.poll();
            result.add(name[0] +" "+name[1]);
        }

        return result;
    }

    public static int convertRomanNumberToDecimal(String romanNumber){
        int actualDecimalValue = 0;
        String previousChar = "";
        Map<String, Integer> romanToDecimalNumberMap = new HashMap<>();
        romanToDecimalNumberMap.put("I", 1);
        romanToDecimalNumberMap.put("V", 5);
        romanToDecimalNumberMap.put("X", 10);
        romanToDecimalNumberMap.put("L", 50);
        romanToDecimalNumberMap.put("C", 100);
        romanToDecimalNumberMap.put("D", 500);
        romanToDecimalNumberMap.put("M", 1000);

        for(int i = romanNumber.length() - 1; i >= 0; i--){
            String currentChar = romanNumber.charAt(i)+"";

            if(i != romanNumber.length() - 1 && romanToDecimalNumberMap.get(previousChar) > romanToDecimalNumberMap.get(currentChar)){
                actualDecimalValue -= romanToDecimalNumberMap.get(currentChar);
            }else{
                actualDecimalValue += romanToDecimalNumberMap.get(currentChar);
            }
            previousChar = romanNumber.charAt(i)+"";
        }

        return actualDecimalValue;
    }
}
