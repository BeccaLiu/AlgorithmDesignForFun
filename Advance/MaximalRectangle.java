package Advance;

import java.util.ArrayDeque;

/**
 * Created by rliu on 2/25/17.
 * 85. Maximal Rectangle
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] arr = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] arr1 = new char[][]{{'1'}};
        System.out.println(maximalRectangle(arr1));
    }


    /*dp[i][j] vertical continuely height
Input :   0 1 1 0
          1 1 1 1
          1 1 1 1
          1 1 0 0

Output :  1 1 1 1
          1 1 1 1

      step 1:    0 1 1 0  maximum area  = 2
      step 2:
    row 1  1 2 2 1  area = 4, maximum area becomes 4
    row 2  2 3 3 2  area = 8, maximum area becomes 8
    row 3  3 4 0 0  area = 6, maximum area remains 8
    */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int max = dp[0][0];

        int localMax = max;
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = matrix[0][i] - '0';
                localMax += 1;
            } else {
                max = Math.max(localMax, max);
                localMax = 0;
            }
        }
        max = Math.max(localMax, max);
        //same as calculate the largest rectangle of histogram

        for (int i = 1; i < matrix.length; i++) {
            ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

            for (int j = 0; j <= matrix[0].length; j++) {
                if (j < matrix[0].length && matrix[i][j] == '1')
                    dp[i][j] = dp[i - 1][j] + 1;
                int rightHeight = j == matrix[0].length ? 0 : dp[i][j];
                while (!stack.isEmpty() && rightHeight < dp[i][stack.peek()]) {
                    int fixedHeight = dp[i][stack.pop()];
                    int left = stack.isEmpty() ? 0 : stack.peek() + 1; //[Attention]: the width or rectangle is not fixedHeight*(j-index)
                    max = Math.max(max, fixedHeight * (j - left));
                }
                stack.push(j);
            }
        }
        return max;
    }

    //dp solution
    //Input :   0 1 1 0
    //          1 1 1 1
    //          1 1 1 1
    //          1 1 0 0

    //dp:       0 1 1 0
    //          1
    //          1
    //          1
    //


}
