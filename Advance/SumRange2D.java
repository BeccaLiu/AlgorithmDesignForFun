package Advance;

/**
 * Created by rliu on 11/30/16.
 */
public class SumRange2D {
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
        int val;
    }
}
