package BitManipulation;

/**
 * Created by rliu on 4/9/17.
 * Fast Power
 * Calculate the an % b where a, b and n are all 32bit integers.
 * Example
 * For 2^31 % 3 = 2
 * For 100^1000 % 1000 = 0
 * Analysis:
 */
public class FastPower {
    public static void main(String[] args) {
        fastPower(2, 3, 3);
    }

    public static int fastPower(int a, int b, int n) {
        if (n == 1) {
            return a % b;
        }
        if (n == 0) {
            return 1 % b;
        }

        //caculate a^n
        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1) {
            product = (product * a) % b;
        }
        return (int) product;
    }
}
