package Advance;

/**
 * Created by rliu on 12/4/16.
 */
public class SumRange2DBinaryIndexTree {
    int[][] biTree;
    int[][] arr;

    public SumRange2DBinaryIndexTree(int[][] arr) {
        this.arr = arr;
        this.biTree = new int[arr.length + 1][arr[0].length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                init(i, j, arr[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        SumRange2DBinaryIndexTree sr2d = new SumRange2DBinaryIndexTree(arr);
        System.out.println(sr2d.sumRange(0, 1, 1, 2));
        sr2d.update(1, 1, 6);
        System.out.println(sr2d.sumRange(0, 1, 1, 2));
    }

    public void update(int row, int col, int val) {
        int diff = arr[row][col] - val;
        arr[row][col] = val;
        init(row, col, -diff);
    }

    public int sumRange(int row1, int row2, int col1, int col2) {
        // 1,2,1,2
        //         c1 c2
        //      1  1  1     1     1  1  1     1
        //r1->  1  1  1  -  1  -           +
        //r2->  1  1  1     1
        return getSum(row2, col2) - getSum(row2, col1 - 1) - getSum(row1 - 1, col2) + getSum(row1 - 1, col1 - 1);
    }

    public int getSum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= (i & -i))
            for (int j = col + 1; j > 0; j -= (j & -j)) {
                sum += biTree[i][j];
            }
        return sum;

    }

    public void init(int row, int col, int val) {
        for (int i = row + 1; i < biTree.length; i += (i & -i)) {
            for (int j = col + 1; j < biTree[0].length; j += (j & -j)) {
                biTree[i][j] += val;
            }
        }
    }
}
