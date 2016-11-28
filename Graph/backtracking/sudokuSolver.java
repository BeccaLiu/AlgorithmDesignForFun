package Graph.backtracking;

/**
 * Created by rliu on 11/27/16.
 * Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * <p>
 * BackTracking:
 * Primitiv Idea:
 * 1. try with 1-9 in each cell
 * 2.check each row and col and submaxtrix
 * 3.skip cells with number
 * 4.every level of recursion is a temperate attempt, we do not know if it gonna work, if it is not valid, we need back to upper level and try with next number
 * 5.when we reach the matrix right down corner, we know we succeed
 * Time: O(9^n)
 * space: O(27n) every cell has three checker
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solver(board, 0, 0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solver(char[][] board, int i, int j) {
        while (board[i][j] != '.') {
            j++;
            if (j == 9) {
                if (i == 8)
                    return true;
                i++;
                j = 0;
            }
        }

        boolean rowCheck[] = new boolean[9];
        boolean colCheck[] = new boolean[9];
        boolean matrixCheck[] = new boolean[9];
        buildChecker(board, rowCheck, colCheck, matrixCheck, i, j);
        for (int cur = 1; cur <= 9; cur++) {
            board[i][j] = (char) (cur + '0');
            if (checkValid(cur, rowCheck, colCheck, matrixCheck) && solver(board, i, j))
                return true;
            board[i][j] = '.';

        }
        return false;
    }

    public static void buildChecker(char[][] board, boolean[] rowCheck, boolean[] colCheck, boolean[] matrixCheck, int i, int j) {
        for (int col = 0; col < 9; col++) {
            if (board[i][col] != '.')
                rowCheck[(int) board[i][col] - '0' - 1] = true;
        }

        for (int row = 0; row < 9; row++) {
            if (board[row][j] != '.')
                colCheck[board[row][j] - '0' - 1] = true;
        }

        for (int row = i / 3 * 3; row < (i / 3 + 1) * 3; row++)
            for (int col = j / 3 * 3; col < (j / 3 + 1) * 3; col++) {
                if (board[row][col] != '.')
                    matrixCheck[board[row][col] - '0' - 1] = true;
            }
    }

    public static boolean checkValid(int k, boolean[] rowCheck, boolean[] colCheck, boolean[] matrixCheck) {
        return !(rowCheck[k - 1] || colCheck[k - 1] || matrixCheck[k - 1]);
    }


}
