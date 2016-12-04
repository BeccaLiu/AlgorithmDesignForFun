package Advance;

/**
 * Created by rliu on 11/30/16.
 * Range Sum Query 2D
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class SumRange2D {
    QuadTreeNode root;

    public SumRange2D(int[][] arr) {
        root = buildTree(arr, 0, arr.length - 1, 0, arr[0].length - 1);
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}, {4, 5, 6, 7}};
        SumRange2D sr2d = new SumRange2D(arr);
        System.out.println(sr2d.sumRange(0, 2, 0, 2));
        sr2d.update(0, 0, 0);
        System.out.println(sr2d.sumRange(0, 2, 0, 2));
    }

    public QuadTreeNode buildTree(int[][] arr, int sRow, int eRow, int sCol, int eCol) {
        QuadTreeNode rt = new QuadTreeNode(sRow, eRow, sCol, eCol);
        int midRow = sRow + (eRow - sRow) / 2;
        int midCol = sCol + (eCol - sCol) / 2;
        if (sRow == eRow && sCol == eCol) {
            rt.sum = arr[sRow][sCol];
            return rt;
        } else if (sRow == eRow) {
            rt.up = rt.down = null;
            rt.left = buildTree(arr, sRow, eRow, sCol, midCol);
            rt.right = buildTree(arr, sRow, eRow, midCol + 1, eCol);
            rt.sum = rt.left.sum + rt.right.sum;
        } else if (sCol == eCol) {
            rt.left = rt.right = null;
            rt.up = buildTree(arr, sRow, midRow, sCol, eCol);
            rt.down = buildTree(arr, midRow + 1, eRow, sCol, eCol);
            rt.sum = rt.up.sum + rt.down.sum;
        } else {
            rt.left = buildTree(arr, sRow, eRow, sCol, midCol);
            rt.right = buildTree(arr, sRow, eRow, midCol + 1, eCol);
            rt.up = buildTree(arr, sRow, midRow, sCol, eCol);
            rt.down = buildTree(arr, midRow + 1, eRow, sCol, eCol);
            rt.sum = rt.left.sum + rt.right.sum;
        }
        return rt;
    }

    private int sumRange(int row1, int row2, int col1, int col2) {
        return sumRange(root, row1, row2, col1, col2);
    }

    private int sumRange(QuadTreeNode root, int row1, int row2, int col1, int col2) {
        if (row1 > row2 || col1 > col2)
            return 0;
        int midRow = root.startRow + (root.endRow - root.startRow) / 2;
        int midCol = root.startCol + (root.endCol - root.startCol) / 2;
        if (row1 == root.startRow && row2 == root.endRow && col1 == root.startCol && col2 == root.endCol)
            return root.sum;
        if (root.startRow == row1 && root.endRow == row2) { //
            if (col2 <= midCol) { //all at left
                return sumRange(root.left, row1, row2, col1, col2);
            } else if (col1 > midCol) { //all at right
                return sumRange(root.right, row1, row2, col1, col2);
            } else //mix left and right
                return sumRange(root.left, row1, row2, col1, midCol) + sumRange(root.right, row1, row2, midCol + 1, col2);
        } else {
            if (row2 <= midRow) {
                return sumRange(root.up, row1, row2, col1, col2);
            } else if (row1 > midRow) {
                return sumRange(root.down, row1, row2, col1, col2);
            } else {
                return sumRange(root.up, row1, midRow, col1, col2) + sumRange(root.down, midRow + 1, row2, col1, col2);
            }
        }

    }

    private void update(int row, int col, int val) {
        update(root, row, col, val);
    }

    private void update(QuadTreeNode root, int row, int col, int val) {
        //base case
        if (root.startCol == root.endCol && root.startRow == root.endRow) {
            root.sum = val;
            return;
        }
        int midRow = root.startRow + (root.endRow - root.startRow) / 2;
        int midCol = root.startCol + (root.endCol - root.startCol) / 2;
        if (root.startRow == root.endRow) {
            //case 1:
            if (col <= midCol)
                update(root.left, row, col, val);
            else
                update(root.right, row, col, val);
            root.sum = root.left.sum + root.right.sum;
            //case 2:
        } else if (root.startCol == root.endCol) {
            if (row <= midRow)
                update(root.up, row, col, val);
            else
                update(root.down, row, col, val);
            root.sum = root.up.sum + root.down.sum;
        } else {
            //case 3:
            if (row <= midRow)
                update(root.up, row, col, val);
            else
                update(root.down, row, col, val);
            if (col <= midCol)
                update(root.left, row, col, val);
            else
                update(root.right, row, col, val);
            root.sum = root.left.sum + root.right.sum;
        }
    }

    //using quart tree to solve
    class QuadTreeNode {
        int startRow;
        int startCol;
        int endRow;
        int endCol;
        QuadTreeNode left;
        QuadTreeNode right;
        QuadTreeNode up;
        QuadTreeNode down;
        int sum;

        public QuadTreeNode(int sRow, int eRow, int sCol, int eCol) {
            this.startRow = sRow;
            this.endRow = eRow;
            this.startCol = sCol;
            this.endCol = eCol;
            int sum = 0;
        }
    }
}
