package Linear.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rliu on 4/10/17.
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Notice
 * <p>
 * The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {
    public static void main(String[] args) {
        largestNumber(new Integer[]{0, 0});
    }

    public static String largestNumber(Integer[] num) {
        // write your code here

        Arrays.sort(num, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                String str1 = String.valueOf(i1);
                String str2 = String.valueOf(i2);
                return (str2 + str1).compareTo(str1 + str2);
            }
        });

        if (num[0] == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i : num)
            sb.append(i);
        return sb.toString();

    }
}
