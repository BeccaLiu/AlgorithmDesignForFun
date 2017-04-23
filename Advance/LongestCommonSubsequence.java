package Advance;

/**
 * Created by rliu on 4/22/17 10:20 PM.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("ABCD", "EDCA"));
    }

    public static int longestCommonSubsequence(String A, String B) {
        // write your code here
        return dfs(A, B, 0, 0, new int[A.length()][B.length()]);
    }

    public static int dfs(String a, String b, int i, int j, int[][] memorized) {
        if (i == a.length() || j == b.length())
            return 0;

        if (memorized[i][j] != 0)
            return memorized[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            int rt = dfs(a, b, i + 1, j + 1, memorized) + 1;
            memorized[i][j] = rt;
            return rt;
        } else {
            int rt = Math.max(dfs(a, b, i + 1, j, memorized), dfs(a, b, i, j + 1, memorized));
            memorized[i][j] = rt;
            return rt;
        }

    }
}
