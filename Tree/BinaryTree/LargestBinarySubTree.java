package Tree.BinaryTree;

/**
 * Created by rliu on 11/25/16.
 * Largest BST Subtree
 * Given a binary tree
 * find the largest subtree which is BST, largest means as many nodes as possible, and return the size
 * key:bottom -up
 */
public class LargestBinarySubTree {
    public static void main(String[] args) {
        int[] a = {12, 9, 15, 2, 10, 13, 16, 0};
        TreeNode root = new TreeNode(a);
        int[] range = new int[2];
        int[] max = new int[1];
        findLargestBST(root, range, max);
        System.out.println(max[0]);

    }

    public static int findLargestBST(TreeNode root, int[] range, int[] globalMax) {
        //can also build a result object which contain range_min, range_max and count
        //it is better than blew implementation as the rangeLeft and rangeRight cause more space
        if (root == null) {
            range[0] = Integer.MAX_VALUE;
            range[1] = Integer.MIN_VALUE;
            return 0;
        }
        int[] rangeLeft = new int[2];
        int[] rangeRight = new int[2];
        int countLeft = findLargestBST(root.left, rangeLeft, globalMax);
        int countRight = findLargestBST(root.right, rangeRight, globalMax);
        if (countLeft == -1 || countRight == -1 || root.val <= rangeLeft[1] || root.val >= rangeRight[0]) {
            range[0] = 0;
            range[1] = 0;
            return -1;
        } else {
            globalMax[0] = Math.max(globalMax[0], countLeft + countRight + 1);
            range[0] = Math.min(rangeLeft[0], root.val);
            range[1] = Math.max(rangeRight[1], root.val);
            return countLeft + countRight + 1;
        }
    }
}
