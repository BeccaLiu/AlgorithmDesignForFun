package Advance;

/**
 * Created by rliu on 4/4/17.
 * longest common substring
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        String a = "www.lintcode.com code";
        String b = "www.ninechapter.com code";
        System.out.println(longestCommonSubstring(a, b));

    }

    public static int longestCommonSubstring(String A, String B) {
        int[][] dp = new int[A.length()][B.length()];
        int max = 0;
        for (int i = 0; i < A.length(); i++)
            for (int j = 0; j < B.length(); j++)
                if (A.charAt(i) == B.charAt(j)) {
                    if (i - 1 >= 0 && j - 1 >= 0)
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    else
                        dp[i][j] = 1;
                    max = Math.max(max, dp[i][j]);
                }

        return max;

    }
}
