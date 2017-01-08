package Tree.BinaryTree;

/**
 * Created by rliu on 1/7/17.
 * 404. Sum of Left Leaves
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {
    public static void main(String[] args) {
        sumOfLeftLeaves(new TreeNode(new int[]{1, 2, 3}));
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;

        return (root.left != null ? sum(root.left, true) : 0) + (root.right != null ? sum(root.right, false) : 0);

    }

    public static int sum(TreeNode root, boolean isLeft) {
        if (root.left == null && root.right == null && isLeft)
            return root.val;
        int l = root.left != null ? sum(root.left, true) : 0;
        int r = root.right != null ? sum(root.right, false) : 0;
        return l + r;
    }
}
