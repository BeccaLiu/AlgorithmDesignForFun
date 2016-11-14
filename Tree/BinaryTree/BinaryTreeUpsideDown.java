package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 11/13/16.
 * Given a binary tree with specific structure
 * all right nodes are either 1. leaf nodes with left sibling, 2 empty
 * valid:           invalid
 * 1                    1
 * 2    3              2       3
 * 4  5                 4 5     6
 * reverse it (make child as root) to a new tree where the original right nodes becoming new left leaf nodes, return the new root
 * ex:
 * 4
 * 5     2
 * 3 1
 * Key: the new root node is the leftmost child 4
 * Time Complexity: O(n)
 * Space Complexity: O(n) not logN, as original tree is not complete, so the layer is approximately n/2
 */
public class BinaryTreeUpsideDown {
    public static TreeNode reverse(TreeNode root) {
        if (root == null)
            return root;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode rt = stack.pop();
        TreeNode newRoot = rt;
        while (!stack.isEmpty()) {
            TreeNode oriRoot = stack.pop();
            newRoot.left = oriRoot.right;
            newRoot.right = oriRoot;
            oriRoot.left = null;
            oriRoot.right = null;
            newRoot = oriRoot;
        }
        return rt;
    }

    public static TreeNode recursionReverse(TreeNode root) {
        if (root == null || root.left == null)
            return root;
        //Key: Assume all lower level is handled
        TreeNode newRoot = recursionReverse(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;

    }

    public static TreeNode inPlace(TreeNode root) {
        if (root == null || root.left == null)
            return root;
        TreeNode pre = null;

        while (root != null) {
            TreeNode next = root.left;
            root.left = pre;
            pre = root;
            root = next;
        }

        root = pre;
        TreeNode newRoot = root;

        while (root.left != null) {
            root.right = root.left;
            root.left = root.right.right;
            root.right.right = null;
            root = root.right;
        }
        return newRoot;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        TreeNode root = new TreeNode(arr);
        System.out.print(inPlace(root));

    }

}
