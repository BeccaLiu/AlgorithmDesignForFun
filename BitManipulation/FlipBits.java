package BitManipulation;

/**
 * Created by rliu on 4/7/17.
 */
public class FlipBits {
    public static void main(String[] args) {
        System.out.println(bitSwapRequired(-31, 14));
    }

    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int flip = a ^ b;//get the difference
        int count = 0;
        while (flip != 0) {
            count += flip & 1; //extract the lowest index
            flip >>>= 1; //after extraction, the lwest index info is useless, move right
        }
        return count;
    }
}
