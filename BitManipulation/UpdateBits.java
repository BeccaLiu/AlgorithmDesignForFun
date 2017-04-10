package BitManipulation;

/**
 * Created by rliu on 4/9/17.
 */
public class UpdateBits {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(updateBits(2046, 21, 2, 6)));
    }

    public static int updateBits(int n, int m, int i, int j) {
        // write your code here
        int max = ~0; /* All 1’s */
        // 1’s through position j, then 0’s
        if (j == 31)
            j = max;
        else
            j = (1 << (j + 1)) - 1;
        int left = max - j;
        // 1’s after position i
        int right = ((1 << i) - 1);
        // 1’s, with 0s between i and j
        int mask = left | right; //became 11111111111111111111111110000011 where set i to j to 0;
        // Clear i through j, then put m in there
        //n&mask. clear through j by change i to j also to 0;
        return ((n & mask) | (m << i));
    }
}
