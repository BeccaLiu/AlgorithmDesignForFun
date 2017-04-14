package Graph.backtracking;

import java.util.Arrays;

/**
 * Created by rliu on 4/13/17.
 * 279. Perfect Squares
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSquare {
    public static void main(String[] args) {
        System.out.println(numSquares(13));

    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            //dp[i]=Integer.MAX_VALUE;
            int sqrt = (int) Math.sqrt(i);
            int count = Integer.MAX_VALUE;
            while (sqrt >= 1) {
                count = Math.min(count, dp[i - sqrt * sqrt] + 1);
                sqrt--;
            }
            dp[i] = count;
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

}
