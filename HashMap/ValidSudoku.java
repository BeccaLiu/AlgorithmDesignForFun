package HashMap;

import java.util.HashSet;

/**
 * Created by rliu on 1/6/17.
 * 36. Valid Sudoku
 * Determine if a Sudoku is valid
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 */
public class ValidSudoku {
    public static void main(String[] args) {

    }

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return false;

        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rowSet = new HashSet<>();
            HashSet<Character> colSet = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.')
                    if (rowSet.contains(board[i][j]))
                        return false;
                    else
                        rowSet.add(board[i][j]);
                if (board[j][i] != '.')
                    if (colSet.contains(board[j][i]))
                        return false;
                    else
                        colSet.add(board[j][i]);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                HashSet<Character> set = new HashSet<>();
                for (int m = i * 3; m < i * 3 + 3; m++)
                    for (int n = j * 3; n < j * 3 + 3; n++)
                        if (board[m][n] != '.')
                            if (set.contains(board[m][n]))
                                return false;
                            else
                                set.add(board[m][n]);
            }
        }

        return true;
    }
}
