package TechPrep100;

public class IntegerToEnglishWords {

    public static void main(String[] args) {
        int[] nums = {123, 1234, 12345, 123456, 1234567, 12345678, 123456789, 1234567898};
        for (int num : nums) {
            System.out.println("The number "+ num +" after conversion is: " + numberToWords(num));
        }
    }

    public static String numberToWords(int num) {
        String result = "";

        if (num >= 1000000000) {
            int billionsPart = num / 1000000000;
            result += getWordsForNumber(billionsPart) + " Billion ";
            num %= 1000000000;
        }
        if (num >= 1000000) {
            int millionsPart = num / 1000000;
            result += getWordsForNumber(millionsPart) + " Million ";
            num %= 1000000;
        }

        if(num >= 1000){
            int thousandsPart = num / 1000;
            result += getWordsForNumber(thousandsPart) + " Thousand ";
            num %= 1000;
        }

        if (num > 0) {
            result += getWordsForNumber(num);
        }
        return result.trim();
    }

    public static String getWordsForNumber(int num){
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String result = "";

        if(num >= 100){
            result += ones[num / 100] + " hundred " + getWordsForNumber(num % 100);
        }else if(num >= 20){
            result += tens[num / 10] + ((num % 10 != 0) ? " " + ones[num % 10] : "");
        }else if(num > 0){
            result += ones[num];
        }

        return result.trim();
    }
}
