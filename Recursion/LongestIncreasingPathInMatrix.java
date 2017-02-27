package Recursion;

/**
 * Created by rliu on 2/26/17.
 * 329. Longest Increasing Path in a Matrix
 */
public class LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
        System.out.println(longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] cache = new int[matrix.length][matrix[0].length];
        int[] maxLen = new int[1];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                dfsHelper(matrix, i, j, Integer.MAX_VALUE, cache, maxLen);
        return maxLen[0];

    }

    public static int dfsHelper(int[][] matrix, int i, int j, int pre, int[][] cache, int[] maxLen) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] >= pre)
            return 0;
        if (cache[i][j] != 0)
            return cache[i][j];

        int left = dfsHelper(matrix, i, j - 1, matrix[i][j], cache, maxLen);
        int right = dfsHelper(matrix, i, j + 1, matrix[i][j], cache, maxLen);
        int up = dfsHelper(matrix, i - 1, j, matrix[i][j], cache, maxLen);
        int down = dfsHelper(matrix, i + 1, j, matrix[i][j], cache, maxLen);
        int max = Math.max(Math.max(left, right), Math.max(up, down));
        cache[i][j] = max + 1;
        maxLen[0] = Math.max(maxLen[0], cache[i][j]);
        return cache[i][j];

    }
}
