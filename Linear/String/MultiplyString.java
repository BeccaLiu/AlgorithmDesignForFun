package Linear.String;

/**
 * Created by rliu on 1/27/17.
 * 43. Multiply Strings
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyString {
    public static void main(String[] args) {
        System.out.println((long) 12345678 * 12345678);
        System.out.println(multiply("12345678", "12345678"));
    }

    public static String multiply(String num1, String num2) {
        if (num1 == null)
            return num2;
        if (num2 == null)
            return num1;
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                //we do not need second pass to calculate all the add and mod
                int curr = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[i + j + 1];
                res[i + j + 1] = curr % 10;
                res[i + j] += curr / 10;
            }
        }
//        int add=0;
//        for(int i=res.length-1;i>=0;i--){
//            res[i]+=add;
//            add=res[i]/10;
//            res[i]%=10;
//        }

        //the res[0] will be 0; for example 1*1->[0,1]
        //and the res length will always be m+n
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0)
                continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
