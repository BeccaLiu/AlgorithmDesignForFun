package Linear.String;

/**
 * Created by rliu on 1/31/17.
 * 8. String to Integer (atoi)
 * Implement atoi to convert a string to an integer.
 * <p>
 * 1. The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * 2. The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * 3. If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * 4. If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */
public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi("-2147483648"));
        //System.out.println(Integer.parseInt("  -0012a42"));
    }

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int start = 0;
        while (str.charAt(start) == ' ')
            start++;

        int output = 0;
        boolean isNegative = false;
        boolean sign = false;

        if (str.charAt(start) == '+' || str.charAt(start) == '-') {
            isNegative = str.charAt(start) == '-';
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (!Character.isDigit(curr)) {
                break;
            }

            //Attention: how to handle the int range exceed problem
            //Int.Max=2147483647  2^31
            //Int.Min=-2147483648 2^31-1
            //current output is already larger than 214748364 aka 214748365
            //or current output is equals to 214748364 aka input is 2147483647/2147483648/2147483649 but current is larger than 7
            if (output > Integer.MAX_VALUE / 10 || output == Integer.MAX_VALUE / 10 && curr - '0' > Integer.MAX_VALUE % 10) {
                if (!isNegative)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }
//            if (!isNegative && output >= Integer.MAX_VALUE / 10 && (curr - '0') > Integer.MAX_VALUE % 10)
//                //if (output < 0 && !isNegative)
//                return Integer.MAX_VALUE;
//            else if (isNegative && -output <= Integer.MIN_VALUE / 10 && -(curr - '0') < Integer.MIN_VALUE % 10)
//                //else if (output < 0 && isNegative)
//                return Integer.MIN_VALUE;
            output = output * 10 + (str.charAt(i) - '0');
        }

        return isNegative ? -output : output;
    }
}
