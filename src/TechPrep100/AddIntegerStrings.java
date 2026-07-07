package TechPrep100;

public class AddIntegerStrings {

    public static void main(String[] args) {
        String[] nums1 = {"11"};
        String[] nums2 = {"23"};

        for(int i = 0 ; i < nums1.length; i++){
//            System.out.println("After addiing two nums: "+addStrings(nums1[i], nums2[i]));
            System.out.println("After addiing two nums old: "+addBinaryOld(nums1[i], nums2[i]));
        }
    }

    public static String addStrings(String a, String b){
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();

        while(i >= 0 || j >= 0 || carry != 0){
            int digit1 = 0, digit2 = 0;

            if(i >= 0){
                digit1 = a.charAt(i) - '0';
                System.out.println("digit1: "+digit1);
                i--;
            }

            if(j >= 0){
                digit2 = b.charAt(j) - '0';
                System.out.println("digit2: "+digit2);
                j--;
            }

            int total = digit1 + digit2 + carry;
            carry = total / 10;
            result.append(total % 10);
            System.out.println("result: "+result);
        }

        return result.reverse().toString();
    }

    public static String addBinaryOld(String a, String b) {

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

            result.append(sum % 10);
            System.out.println("result: "+result);
            carry = sum / 10;
        }

        return result.reverse().toString();

    }

}
