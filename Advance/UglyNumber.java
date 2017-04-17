package Advance;

/**
 * Created by rliu on 4/16/17.
 * 263. Ugly Number
 * Write a program to check whether a given number is an ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {
    public static void main(String[] args) {
        System.out.println(isUgly(30));
        System.out.println(nthUglyNumber(6));
    }

    public static boolean isUgly(int num) {
        if (num < 0)
            return false;
        int reminder = 0;
        while (reminder == 0 && num != 1) {
            reminder = num % 2;
            if (reminder == 0)
                num /= 2;
        }
        reminder = 0;
        while (reminder == 0 && num != 1) {
            reminder = num % 3;
            if (reminder == 0)
                num /= 3;
        }
        reminder = 0;
        while (reminder == 0 && num != 1) {
            reminder = num % 5;
            if (reminder == 0)
                num /= 5;
        }
        return num == 1;
    }

    public static int nthUglyNumber(int n) {
        // Write your code here
        long[] res2 = new long[n];
        long[] res3 = new long[n];
        long[] res5 = new long[n];
        res2[0] = 1;
        //for 2
        for (int i = 1; i <= n / 2; i++) {
            res2[i] = (int) Math.pow(2, i);
        }

        //for 3
        //Two case:
        //case1 directly copy from res2[m]
        //case2 copy of res3[n]*3

        res3[0] = 1;
        int i = 1;
        int m = 1;
        int l = 0;
        while (i < n) {
            long resTwo = res2[m];
            long multi = res3[l] * 3;
            //int power = (int) Math.pow(3, p);
            long min = Math.min(resTwo, multi);
            res3[i++] = min;
            if (min == resTwo)
                m++;
            if (min == multi)
                l++;
        }

        //for 5
        res5[0] = 1;
        i = 1;
        m = 1;
        l = 0;
        while (i < n) {
            long resTwo = res3[m];
            long multi = res5[l] * 5;
            long min = Math.min(resTwo, multi);
            res5[i++] = min;
            if (min == resTwo)
                m++;
            if (min == multi)
                l++;
        }
        return (int) res5[n - 1];
    }


}
