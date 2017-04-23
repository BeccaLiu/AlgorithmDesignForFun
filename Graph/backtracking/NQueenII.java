package Graph.backtracking;

/**
 * Created by rliu on 4/22/17 9:18 PM.
 */
public class NQueenII {
    public static void main(String[] args) {
        System.out.println(totalNQueens(3));
    }

    public static int totalNQueens(int n) {
        //write your code here
        boolean[][] queens = new boolean[n][n];
        return solveQueens(queens, 0);
    }

    public static int solveQueens(boolean[][] queens, int row) {
        if (row == queens.length) {
            return 1;
        }
        int count = 0;

        for (int j = 0; j < queens.length; j++) {
            if (canAttack(queens, row, j)) {
                queens[row][j] = true;
                count += solveQueens(queens, row + 1);
                queens[row][j] = false;
            }
        }
        return count;
    }

    public static boolean canAttack(boolean[][] queens, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (queens[i][col])
                return false;
        }
        int offset = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (col - offset >= 0 && queens[i][col - offset])
                return false;
            if (col + offset < queens.length && queens[i][col + offset])
                return false;
            offset++;
        }
        return true;

    }
}
