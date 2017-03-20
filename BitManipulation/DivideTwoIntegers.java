package BitManipulation;

/**
 * Created by rliu on 3/19/17.
 * using the chord algorithm of distributed hash table
 * can also solve the question first bad version, if do not know the number of the versions need to multiple and get place
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(-125 % 3);
        System.out.println(-125 / 3);

        System.out.println(divide(-125, 3));

    }
    //eg:125=3*32+3*8+3*1+2
    //-125=-3*32+-3*8+-3*1-2

    public static int divide(int dividend, int divisor) {
        boolean negative = (dividend > 0 && divisor < 0) ||
                (dividend < 0 && divisor > 0);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int ans = 0;
        long mod = 0;
        while (a >= b) {
            int shift = 0;
            //b<<shift b*2^shift so 3<<2=3*2*2=12

            while ((b << shift) <= a) {
                shift++;
            }
            //as 3*2^5=96
            //want to know 96/3? it is == 2^5
            //1<<shift = 2^shift
            ans += 1 << (shift - 1);
            a = a - (b << (shift - 1));
            mod = a;
        }
        System.out.println(negative ? -mod : mod);

        return negative ? -ans : ans;
    }
}
