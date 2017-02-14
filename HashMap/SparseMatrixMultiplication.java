package HashMap;

/**
 * Created by rliu on 2/13/17.
 * 311. Sparse Matrix Multiplication
 * A = [
 * [ 1, 0, 0],
 * [-1, 0, 3]
 * ]
 * B = [
 * [ 7, 0, 0 ],
 * [ 0, 0, 0 ],
 * [ 0, 0, 1 ]
 * ]
 * <p>
 * A*B
 * |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * | 0 0 1 |
 */
public class SparseMatrixMultiplication {
    public static void main(String[] args) {
        int[][] A = new int[][]{{1, -5}};//, {-1, 0, 3}};
        int[][] B = new int[][]{{7, -1}};//, {0, 0, 0}, {0, 0, 1}};
        multiplyFaster(A, B);
    }

    //however the navie solution can not pass the extream large input set, as this is a sparse matrix, which means most of the content is 0 , we do not need to caluclate the zero place
    //in this way, for quick check up, we will think of hashtable
    //or using two visited array to check is zero, this way is still not fast enough

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] rt = new int[A.length][B[0].length];

        for (int i = 0; i < rt.length; i++)
            for (int j = 0; j < rt[0].length; j++) {
                for (int k = 0; k < B.length; k++)
                    if (A[i][k] == 0 && B[k][j] == 0)
                        rt[i][j] += A[i][k] * B[k][j]; //using rt[i][j] += A[i][k] * B[k][j]
            }
        return rt;
    }

    //https://www.cs.cmu.edu/~scandal/cacm/node9.html
    public static int[][] multiplyFaster(int[][] A, int[][] B) {
        int[][] rt = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++) {
                //here if A[i][j]!=0, rt[i][j] will +=A[i][j]*B[j][k]
                //compare to previous if A[i][j]==0 we no need to go into iteration, but previously will iterator every thing
                if (A[i][j] != 0) {
                    for (int k = 0; k < B[0].length; k++)
                        if (B[j][k] != 0)
                            rt[i][k] += A[i][j] * B[j][k];
                }
            }

        return rt;

    }
}
