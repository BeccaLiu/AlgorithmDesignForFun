package Advance;

/**
 * Created by rliu on 11/30/16.
 * Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{3, 1, 2, 1}, {1, 0, 5, 0}, {4, 9, 7, 6}, {0, 1, 2, 5}};
        System.out.println(primitive(grid, 0, 0));
        System.out.println(primitiveImp(grid, 0, 0, new int[grid.length][grid[0].length]));
        System.out.println(minPathSumDp(grid));
        System.out.println(minPathSumDp1D(grid));
    }

    //helper(i,j)=Min(helper(i+1,j),helper(i,j+1))+grid[i][j]
    //Time: O(2^(m&n)) every nodes, we have two choices, so it is 2*2*2.....*2(m*n nodes)
    public static int primitive(int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];
        if (i >= grid.length || j >= grid[0].length)
            return Integer.MAX_VALUE;
        int right = primitive(grid, i, j + 1);
        int down = primitive(grid, i + 1, j);
        return grid[i][j] + Math.min(right, down);
    }

    //recursive is using bottom up
    //using memorized array to save time, and remove duplicates
    //time: O(m*n)
    public static int primitiveImp(int[][] grid, int i, int j, int[][] saved) {
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];
        if (i >= grid.length || j >= grid[0].length)
            return Integer.MAX_VALUE;
        if (saved[i][j] != 0)
            return saved[i][j];
        int right = primitive(grid, i, j + 1);
        int down = primitive(grid, i + 1, j);
        saved[i][j] = grid[i][j] + Math.min(right, down);
        return saved[i][j];
    }

    //top down
    //Time: O(m*n)
    //Space: O(m*n)
    public static int minPathSumDp(int[][] grid) {
        int[][] saved = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0)
                    saved[i][j] = grid[i][j];
                else {
                    int up = i - 1 < 0 ? Integer.MAX_VALUE : saved[i - 1][j];
                    int left = j - 1 < 0 ? Integer.MAX_VALUE : saved[i][j - 1];
                    saved[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return saved[saved.length - 1][saved[0].length - 1];
    }

    //Time is not able to improve, as we need to visit each cell at least one time
    //Space: O(n) 2d to 1d
    //min[i][j] = Min(min[i,j-1],min[i-1,j])+grid[i][j]
    //apparently, we only need O(n) space
    //but there is no way to go O(1) space, as you need two factor to defined current smallest path
    public static int minPathSumDp1D(int[][] grid) {
        int[] saved = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0)
                    saved[j] = grid[i][j];
                else {
                    int up = i - 1 < 0 ? Integer.MAX_VALUE : saved[j];
                    int left = j - 1 < 0 ? Integer.MAX_VALUE : saved[j - 1];
                    saved[j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return saved[saved.length - 1];

    }

}
