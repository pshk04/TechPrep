package DynamicProgramming;

public class PalindromeLength {

    private String palindrome;
    private int palindromeLength;

    public PalindromeLength(String palindrome, int palindromeLength) {
        this.palindrome = palindrome;
        this.palindromeLength = palindromeLength;
    }

    public String getPalindrome() {
        return palindrome;
    }

    public void setPalindrome(String palindrome) {
        this.palindrome = palindrome;
    }

    public int getPalindromeLength() {
        return palindromeLength;
    }

    public void setPalindromeLength(int palindromeLength) {
        this.palindromeLength = palindromeLength;
    }
}
