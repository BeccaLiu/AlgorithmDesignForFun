package Linear.Array;

/**
 * Created by rliu on 12/18/16.
 * Spiral Matrix 2
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * For example,
 * Given n = 3,
 * <p>
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrix2 {
    public static void main(String[] args) {
        generateMatrix(1);
    }

    public static int[][] generateMatrix(int n) {
        int[][] rt = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int down = n - 1;
        int index = 1;
        while (left <= right && top <= down) {
            for (int col = left; col <= right && top <= down; col++)
                rt[top][col] = index++;
            top++;
            for (int row = top; row <= down && left <= right; row++)
                rt[row][right] = index++;
            right--;
            for (int col = right; col >= left && top <= down; col--)
                rt[down][col] = index++;
            down--;
            for (int row = down; row >= top && left <= right; row--)
                rt[row][left] = index++;
            left++;
        }
        return rt;
    }
}
