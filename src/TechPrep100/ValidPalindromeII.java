package TechPrep100;

public class ValidPalindromeII {

    public static void main(String[] args) {
        System.out.println("Can we make a palindrome by removing a single character: "+validPalindrome("abca"));
    }


    public static boolean validPalindrome(String s) {

        int i = 0;
        String subString = "";

        if(s.length() == 1){
            return true;
        }

        while(i < s.length() - 1){
            subString = s.substring(0,i) + s.substring(i + 1);
            if(isPalindrome(subString)){
                return true;
            }
            i++;
        }
        return false;
    }

    public static boolean isPalindrome(String s){

        int i = 0 , j = s.length() - 1;

        if(i == j){
            return true;
        }

        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }
}
