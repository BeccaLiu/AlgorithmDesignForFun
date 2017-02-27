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
}
