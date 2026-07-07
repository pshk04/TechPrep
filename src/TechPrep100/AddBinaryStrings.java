package TechPrep100;

public class AddBinaryStrings {

    public static void main(String[] args) {
        String[] a = {"11"};
        String[] b = {"1"};

        for(int i = 0 ; i < a.length; i++) {
            System.out.println("After adding two binary strings: " + addBinary(a[i], b[i]));
        }
    }

    public static String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();

        while(i >= 0 || j >= 0 || carry > 0){
            int sum = carry;

            if(i >= 0){
                sum += a.charAt(i) - '0';
                System.out.println("at i: "+sum);
                i--;
            }

            if(j >= 0){
                sum += b.charAt(j) - '0';
                System.out.println("at j: "+sum);
                j--;
            }

            result.append(sum % 2);
            System.out.println("result: "+result);
            carry = sum / 2;
        }

        return result.reverse().toString();

    }
}
