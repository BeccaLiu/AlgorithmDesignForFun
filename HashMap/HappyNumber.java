package HashMap;

import java.util.HashSet;

/**
 * Created by rliu on 12/27/16.
 * 202. Happy Number
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example: 19 is a happy number
 * <p>
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(2));
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> map = new HashSet<Integer>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                sum += Math.pow(n % 10, 2);
                n = n / 10;
            }
            if (map.contains(sum))
                return false;
            else
                map.add(sum);
            System.out.println(sum);
            n = sum;
        }
        return true;
    }

}
