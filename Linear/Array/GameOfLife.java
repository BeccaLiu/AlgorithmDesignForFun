package Linear.Array;

/**
 * Created by rliu on 3/1/17.
 * 289. Game of Life
 * //Attention: if for each int, you want just store information within 0-9 ,using few digits and each digits stand for int[] info you want to store
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 */
public class GameOfLife {
    public static void main(String[] args) {
        gameOfLife(new int[][]{{1, 0, 0, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}});
    }

    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                int live = board[i][j];
                int countLive = getCurr(board, i - 1, j - 1) + getCurr(board, i, j - 1) + getCurr(board, i + 1, j - 1) + getCurr(board, i - 1, j) + getCurr(board, i + 1, j) + getCurr(board, i - 1, j + 1) + getCurr(board, i, j + 1) + getCurr(board, i + 1, j + 1);
                if ((live == 1 && countLive >= 2 && countLive <= 3) || live == 0 && countLive == 3)
                    board[i][j] += 10;
            }

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] / 10;
            }

    }

    public static int getCurr(int[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return 0;
        return board[i][j] % 10;
    }
}
