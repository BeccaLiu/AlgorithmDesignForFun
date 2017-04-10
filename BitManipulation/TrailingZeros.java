package BitManipulation;

/**
 * Created by rliu on 4/9/17.
 * Trailing Zeros
 * How many trailing zeroes would be found in 4617!, upon expansion?
 * I'll apply the procedure from above:
 * <p>
 * 51 :  4617 ÷ 5 = 923.4, so I get 923 factors of 5
 * 52 :  4617 ÷ 25 = 184.68, so I get 184 additional factors of 5
 * 53 :  4617 ÷ 125 = 36.936, so I get 36 additional factors of 5
 * 54 :  4617 ÷ 625 = 7.3872, so I get 7 additional factors of 5
 * 55 :  4617 ÷ 3125 = 1.47744, so I get 1 more factor of 5
 * 56 :  4617 ÷ 15625 = 0.295488, which is less than 1, so I stop here.
 * Then 4617! has 923 + 184 + 36 + 7 + 1 = 1151 trailing zeroes.
 */
public class TrailingZeros {
    public static void main(String[] args) {
        System.out.println(trailingZeros(4617));
    }

    public static int trailingZeros(int n) {
        // write your code here
        int cnt = 0;
        long i = 5;
        while (true) {
            long c = n / i;
            if (c > 0) {
                i *= 5;
                cnt += c;
            } else
                break;
        }
        return cnt;
    }
}
