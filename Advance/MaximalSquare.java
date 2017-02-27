package Advance;

/**
 * Created by rliu on 2/25/17.
 * 221. Maximal Square
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] arr = new char[][]{{'1', '1', '1', '0'}, {'1', '1', '1', '1'}, {'1', '1', '1', '1'}, {'1', '1', '0', '0'}};
        System.out.println(maximalSquare(arr));

    }

    //dp solution
    //Input :   1 1 1 0
    //          1 1 1 1
    //          1 1 1 1
    //          1 1 0 0

    //dp:       1 1 1 0
    //          1 2 2 1
    //          1 2 3 2
    //          1 2 0 0

    //Input :   0 1 1 0
    //          1 1 1 1
    //          1 1 1 1
    //          1 1 0 0

    //dp:       0 1 1 0
    //          1 1 2 1
    //          1 2 2 2
    //          1 2 0 0
    // dp[i][j] is the length of the max square including matrix[i][j]

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int dp[][] = new int[m][n];
        int maxlen = 0;

        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxlen = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxlen = 1;
            }
        }

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxlen = Math.max(maxlen, dp[i][j]);
                }
            }
        return maxlen * maxlen;
    }
}
