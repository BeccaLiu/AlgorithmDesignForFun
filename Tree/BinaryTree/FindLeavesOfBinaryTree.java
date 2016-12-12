package Tree.BinaryTree;

import java.util.ArrayList;

/**
 * Created by rliu on 12/11/16.
 * Find leaves of Binary Tree
 * Given a binary tree, find all leaves and then remove those leaves. Then repeat the previous steps until the tree is empty.
 * Example:
 * Given binary tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {
    //[I am stuck here]: using height
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4, 5, 6});
        root.left.right.left = new TreeNode(7);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        height(root, res);
        System.out.print(res);
    }

    public static int height(TreeNode node, ArrayList<ArrayList<Integer>> res) {
        if (node == null)
            return 0;
        int leftHeight = height(node.left, res);
        int rightHeight = height(node.right, res);
        int currentHeight = Math.max(leftHeight, rightHeight) + 1;
        if (currentHeight > res.size())
            res.add(new ArrayList<>());
        res.get(currentHeight - 1).add(node.val);
        return currentHeight;

    }

}
