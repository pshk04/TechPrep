package Sorting;

import java.util.*;

public class NaturalSort_INCOMPLETE {

    public static void main(String[] args) {
        String[][] strings = {
                {
                        "a1b2","a1b10","a1b1","a2b1","a10b1"
                },
                {
                        "item10","item2","item1","file20","file3"
                },
//                {
//                    "a1", "b", "c", "a", "b2", "1a"
//                },
//                {
//                        "x","x","x1","x01","x001"
//                },
                {
                        "file","file1","file10","file2","file20a","file20b"
                }
        };
        for(int i = 0; i < strings.length; i++) {
            System.out.println("Natural Sort: "+ Arrays.toString(naturalSort(strings[i])));
        }
    }

    public static String[] naturalSort(String[] strs) {
        if(strs.length == 0){
            return new String[]{};
        }
        String[] result = new String[strs.length];
        Map<String, List<String>> digitsMap = new TreeMap<>();
        List<String> digitsList;
        List<String> duplicateStrList = new ArrayList<>();
        boolean isDigit = false, digitIsPresent = false;

        PriorityQueue<String[]> stringMinHeap = new PriorityQueue<>((a,b) ->{
            int p1 = a[1].compareTo(b[1]);
            if(p1 == 0) {
                if (a.length == 3) {
                    return a[2].compareTo(b[2]);
                } else if (a.length == 4) {
                    int p2 = a[2].compareTo(b[2]);
                    if (p2 == 0) {
                        return a[3].compareTo(b[3]);
                    }
                    return p2;
                }
            }
            return p1;
        });

//        PriorityQueue<String[]> stringMinHeap = new PriorityQueue<>((a,b) ->{
//            int p1 = 0;
//            if(a.length == 2 && b.length == 2) {
//                if (Character.isDigit(a[1].charAt(0))) {
//                    p1 = Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
//                } else {
//                    p1 = a[1].compareTo(b[1]);
//                }
//                return p1;
//            }else if (a.length == 3 && b.length == 3) {
//                if(Character.isDigit(a[1].charAt(0))){
//                    p1 = Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
//                }else {
//                    p1 = a[1].compareTo(b[1]);
//                }
//                if (p1 == 0) {
//                    int p2 = 0;
//                    if(Character.isDigit(a[2].charAt(0))){
//                        p2 = Integer.compare(Integer.parseInt(a[2]), Integer.parseInt(b[2]));
//                    }else {
//                        p2 = a[2].compareTo(b[2]);
//                    }
//                    return p2;
//                }
//                return p1;
//            }else if (a.length == 4 && b.length == 4) {
//                if(Character.isDigit(a[1].charAt(0))){
//                    p1 = Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
//                }else {
//                    p1 = a[1].compareTo(b[1]);
//                }
//                if (p1 == 0) {
//                    int p2 = 0;
//                    if(Character.isDigit(a[2].charAt(0))){
//                        p2 = Integer.compare(Integer.parseInt(a[2]), Integer.parseInt(b[2]));
//                    }else {
//                        p2 = a[2].compareTo(b[2]);
//                    }
//                    if (p2 == 0) {
//                        int p3 = 0;
//                        if(Character.isDigit(a[3].charAt(0))){
//                            p3 = Integer.compare(Integer.parseInt(a[3]), Integer.parseInt(b[3]));
//                        }else {
//                            p3 = a[3].compareTo(b[3]);
//                        }
//                        return p3;
//                    }
//                    return p2;
//                }
//                return p1;
//            }else {
//                if (Character.isDigit(a[0].charAt(0))) {
//                    p1 = Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0]));
//                } else {
//                    p1 = a[0].compareTo(b[0]);
//                }
//                return p1;
//            }
//        });

        for(int i = 0 ; i < strs.length; i++){
            digitsList = new ArrayList<>();
            StringBuilder digitStr = new StringBuilder();
            StringBuilder charStr = new StringBuilder();
            String currentStr = strs[i];
            digitIsPresent = false;

            if(currentStr.length() > 0 && currentStr != null){
                for(int j = 0 ; j < currentStr.length(); j++) {
                    while(j < currentStr.length() && Character.isDigit(currentStr.charAt(j))) {
                        digitStr.append(currentStr.charAt(j));
                        j++;
                        isDigit = true;
                    }
                    if(isDigit) {
                        digitsList.add(digitStr.toString());
                        isDigit = false;
                        digitStr = new StringBuilder();
                        digitIsPresent = true;
                        j--;
                    }else{
                        while(j < currentStr.length() && !Character.isDigit(currentStr.charAt(j))) {
                            charStr.append(currentStr.charAt(j));
                            j++;
                        }
                        digitsList.add(charStr.toString());
                        charStr = new StringBuilder();
                        j--;
                    }
                }
                if(!digitIsPresent){
                    digitsList.add("0");
                }
                if(!digitsMap.containsKey(currentStr)) {
                    digitsMap.put(currentStr, digitsList);
                }else{
                    duplicateStrList.add(currentStr);
                }
            }
        }

        System.out.println("digitsMap: "+digitsMap);

        for(Map.Entry<String, List<String>> entry : digitsMap.entrySet()){
            int index = 0;
            String currentStr = entry.getKey();
            List<String> currentStrDigitsList = entry.getValue();
            String[] strValues = new String[1 + currentStrDigitsList.size()];
            strValues[index] = currentStr;
            index++;

            if(currentStrDigitsList.size() > 0) {
                for (String digit : currentStrDigitsList) {
                    strValues[index] = digit;
                    index++;
                }
                stringMinHeap.offer(strValues);
            }
        }
        if(duplicateStrList.size() > 0){
            int index = 0;
            for(String duplicateValue : duplicateStrList){
                List<String> valuesList = digitsMap.get(duplicateValue);
                String[] strValues = new String[1 + valuesList.size()];
                for (String digit : valuesList) {
                    strValues[index] = digit;
                    index++;
                }
                stringMinHeap.offer(strValues);
            }
        }
        int index = 0;
        while(!stringMinHeap.isEmpty()){
            result[index] = stringMinHeap.poll()[0];
//            System.out.println(result[index]);
            index++;
        }
        return result;
    }
}
