package Graph.backtracking;

import java.util.ArrayList;

/**
 * Created by rliu on 4/13/17.
 */
public class NQueens {
    public static void main(String[] args) {
        solveNQueens(4);

    }

    public static ArrayList<ArrayList<String>> solveNQueens(int n) {
        // write your code here
        boolean[][] queens = new boolean[n][n];
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        solveNQueens(n, 0, res, new boolean[n][n]);
        return res;
    }

    public static void solveNQueens(int n, int row, ArrayList<ArrayList<String>> res, boolean[][] queens) {
        if (row == n) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++)
                    if (queens[i][j])
                        sb.append("Q");
                    else
                        sb.append(".");
                list.add(sb.toString());
            }

            res.add(list);
            return;
        }

        for (int i = row; i < n; i++) {
            boolean valid = false;
            for (int j = 0; j < n; j++) {
                if (i == 0 || (i > 0 && canNotBeAttack(queens, i, j))) {
                    valid = true;
                    queens[i][j] = true;
                    solveNQueens(n, row + 1, res, queens);
                    queens[i][j] = false;
                    valid = false;
                }
            }
            if (!valid) {
                return;
            }
        }

    }

    public static boolean canNotBeAttack(boolean[][] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i][col])
                return false;
            if (col - (row - i) >= 0 && queens[i][col - (row - i)])
                return false;
            if (col + (row - i) < queens[0].length && queens[i][col + (row - i)])
                return false;
        }
        return true;
    }
}
