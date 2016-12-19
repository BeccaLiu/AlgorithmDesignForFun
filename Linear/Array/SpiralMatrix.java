package Linear.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 12/18/16.
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * For example,
 * Given the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 3}, {4, 5, 6, 6}, {7, 8, 9, 9}};
        System.out.print(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return list;

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int down = matrix.length - 1;

        while (left <= right && top <= down) {
            //details to pay attention: need to check left<=right and top <=down at every for loops
            //although we check this condition at the while loop, it is not enough to make sure every element to be added is valid
            //***check broad condition again and again if there are multiple loop inside a loop
            for (int col = left; col <= right; col++)
                list.add(matrix[top][col]);
            top++;
            for (int row = top; row <= down && left <= right; row++)
                list.add(matrix[row][right]);
            right--;
            for (int col = right; col >= left && top <= down; col--)
                list.add(matrix[down][col]);
            down--;
            for (int row = down; row >= top && left <= right; row--)
                list.add(matrix[row][left]);
            left++;
        }
        return list;
    }
}
