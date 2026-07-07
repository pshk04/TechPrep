package TechPrep100;

public class ValidPalindrome1 {

    public static void main(String[] args) {
        String[] s = {
                "No lemon, no melon",
                "Hello, world!",
                "?"
        };
        for(String sentence : s) {
            System.out.println("Is is a palindrome?: "+isPalindrome(sentence));
        }
    }

    public static boolean isPalindrome(String s){

        s = s.replaceAll("[^a-zA-Z0-9]", "").replaceAll(" ","").toLowerCase();
        System.out.println(s);
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
