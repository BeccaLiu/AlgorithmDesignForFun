package Graph.backtracking;

/**
 * Created by rliu on 1/22/17.
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {
    public static void main(String[] args) {
        char board[][] = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCCED"));
        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "FCED"));
        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCB"));
        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCESEEEFS"));

    }

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0)
            return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (checkGrid(board, i, j, word, 0, visited))
                    return true;
            }
        }
        return false;
    }

    //[I am stuck here:] do not remember the add and remove pair!! here if we visited[i][j] and when we backtrack, we need set visited[i][j] to false again, to prepare for the next tentative solution
    public static boolean checkGrid(char[][] board, int i, int j, String word, int pos, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (pos == word.length() - 1)
            return !visited[i][j] && board[i][j] == word.charAt(pos);

        if (board[i][j] == word.charAt(pos) && !visited[i][j]) {
            visited[i][j] = true;
            boolean vlidroot = checkGrid(board, i - 1, j, word, pos + 1, visited) || checkGrid(board, i, j - 1, word, pos + 1, visited) || checkGrid(board, i + 1, j, word, pos + 1, visited) || checkGrid(board, i, j + 1, word, pos + 1, visited);
            visited[i][j] = false;
            return vlidroot;
        } else
            return false;
    }
}
