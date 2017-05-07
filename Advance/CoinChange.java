package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 2/25/17.
 * 322. Coin Change
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));//3
        System.out.println(coinChange(new int[]{1}, 0));//0
        System.out.println(coinChange(new int[]{12}, 11));//-1
        System.out.println(coinChange(new int[]{474, 83, 404, 3}, 264));//8
        System.out.println(coinChangeDFS(new int[]{1, 2, 5}, 11, 0));
        System.out.println(coinChangeDP(new int[]{2, 3, 5, 6}, 10));

    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (coins == null || coins.length == 0)
            return -1;

        int[] dp = new int[amount + 1];

        //this is the key, just initial the dp[coins[i]] to 1 is not enough, can cause problem, eg, coins=[3] and amount=4, if dp[4-3]=0, then dp[4]=0+1, which is definitely incorrect
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++)
            if (coins[i] < amount)
                dp[coins[i]] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int j : coins)
                if (j <= i && dp[i - j] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - j] + 1);
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //count solution number
    //eg 1,2,3 with 4 [1,1,1,1][1,1,2][2,2][1,3] ->4

    public static long coinChangeDP(int[] coins, int amount) {
        long[] DP = new long[amount + 1]; // O(N) space.
        DP[0] = (long) 1;    // n == 0 case.
        for (int coin : coins) {
            for (int j = coin; j < DP.length; j++) {
                // The only tricky step.
                DP[j] += DP[j - coin];
            }
        }
        return DP[amount];

    }

    //dfs will exceed time, but can not use cache
    //count how many solution
    public static int coinChangeDFS(int[] coins, int amount, int idx) {
        if (amount == 0)
            return 1;
        else if (amount < 0)
            return 0;
        int cnt = 0;
        for (int i = idx; i < coins.length; i++) {
            if (amount - coins[i] >= 0)
                cnt += coinChangeDFS(coins, amount - coins[i], i);
            else
                break;
        }
        return cnt;
    }
}
