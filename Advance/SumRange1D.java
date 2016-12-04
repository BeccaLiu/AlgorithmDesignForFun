package Advance;

/**
 * Created by rliu on 11/30/16.
 */
public class SumRange1D {
    public static void main(String[] args) {

    }

    //Primitive : 1.sumRange() O(n) add up all number update O(1)
    //            2.sumRange() O(1) using sum all number like SumRange(1,3)=sum[3]-sum[1]   update() O(n) :modified one number, need to add to all sum
    // so maybe complexity of sumRange and update is O(logn) is feasible
    // we will think of tree
    // Segment Tree

    //线段树
    class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        int val;

    }

    //get clue from segment tree, we will think of using binary index tree

}
