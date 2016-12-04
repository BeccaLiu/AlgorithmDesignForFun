package Advance;

/**
 * Created by rliu on 11/30/16.
 * Range sum query
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */
public class SumRange1D {
    SegmentTreeNode root;

    public SumRange1D(int[] arr) {
        root = buildTree(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        SumRange1D sumRange1D = new SumRange1D(arr);
        System.out.println(sumRange1D.sumRange(2, 3));
        sumRange1D.update(9, 6);
        System.out.println(sumRange1D.sumRange(2, 3));
    }

    public static void update(SegmentTreeNode root, int i, int val) {
        if (root.start == root.end) {
            root.sum = val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (i <= mid) {
            update(root.left, i, val);
        } else {
            update(root.right, i, val);
        }
        root.sum = root.left.sum + root.right.sum;
        return;
    }

    public SegmentTreeNode buildTree(int[] arr, int start, int end) {
        if (start > end)
            return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            root.sum = arr[end];
        } else {
            int mid = start + (end - start) / 2;
            root.left = buildTree(arr, start, mid);
            root.right = buildTree(arr, mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    //kazaizhechaojiu
    public int sumRange(SegmentTreeNode root, int i, int j) {
        if (i > j)
            return 0;
        if (i == root.start && j == root.end)
            return root.sum;
        int mid = root.start + (root.end - root.start) / 2;
        int rt = sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
        return rt;
    }


    //Primitive : 1.sumRange() O(n) add up all number update O(1)
    //            2.sumRange() O(1) using sum all number like SumRange(1,3)=sum[3]-sum[1]   update() O(n) :modified one number, need to add to all sum
    // so maybe complexity of sumRange and update is O(logn) is feasible
    // we will think of tree
    // Segment Tree

    //线段树
    static class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        int sum;

        SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

}
