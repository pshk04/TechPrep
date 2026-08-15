package Sorting;

import java.util.Map;
import java.util.TreeMap;

public class BetterCompression {

    public static void main(String[] args) {
        String[] compressedWords = {"a3c9b2c1","z1a1b1a2", "a3a100a20b12b100" }; //

        for(String compressedWord : compressedWords){
            System.out.println("The alphabetical order of compressed word: "+orderCompression(compressedWord));
            System.out.println("The alphabetical order of compressed word (optimized): "+betterCompression(compressedWord));
        }
    }

    public static String betterCompression(String word){
        Map<Character, Integer> charCountMap = new TreeMap<>();
        char[] charArray = word.toCharArray();
        int i = 0;
        char currentChar;
        StringBuilder finalCompressedString = new StringBuilder();

        while(i < charArray.length){
            currentChar = charArray[i];
            int num = 0;
            i++;

            while(i < charArray.length && Character.isDigit(charArray[i])){
                num = num * 10 + (charArray[i] - '0');
                i++;
            }
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + num);
        }
        for(Map.Entry<Character, Integer> entry : charCountMap.entrySet()){
            finalCompressedString.append(entry.getKey()).append(entry.getValue());
        }

        return finalCompressedString.toString();
    }
    public static String orderCompression(String word){
        Map<Character, Integer> charCountMap = new TreeMap<>();
        char[] charArray = word.toCharArray();
        char currentChar, previousChar = charArray[0];
        StringBuilder digitChar = new StringBuilder();

        for(int i = 0; i < charArray.length; i++){
            currentChar = charArray[i];
            if(Character.isDigit(currentChar)){
                digitChar.append(currentChar);
            }else{
                if(!charCountMap.containsKey(currentChar)){
                    charCountMap.put(currentChar, 0);
                }
                if(charCountMap.containsKey(previousChar) && i > 0){
                    charCountMap.put(previousChar, (charCountMap.get(previousChar) + Integer.parseInt(digitChar.toString())));
                }else{
                    if(i > 0) {
                        charCountMap.put(previousChar, Integer.parseInt(digitChar.toString()));
                    }
                }
                digitChar = new StringBuilder();
                previousChar = currentChar;
            }
        }
        if(charCountMap.containsKey(previousChar)){
            charCountMap.put(previousChar, charCountMap.get(previousChar) + Integer.parseInt(digitChar.toString()));
        }else{
            charCountMap.put(previousChar, Integer.parseInt(digitChar.toString()));
        }
        digitChar = new StringBuilder();

        for(Map.Entry<Character, Integer> entry : charCountMap.entrySet()){
            digitChar.append(entry.getKey()).append(entry.getValue());
        }

        return digitChar.toString();
    }
}
