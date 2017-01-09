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
    //[I am stuck here]: using DistanceFromLeaf
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4, 5, 6});
        root.left.right.left = new TreeNode(7);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        DistanceFromLeaf(root, res);
    }

    //Analysis: brainstorm the question,usually we tag the DistanceFromLeaf of a node from root to leaf, from level 0 to level h,
    //          but here, if we want to print the leaf first, we need to see leaf as the level 0, and its parent is level 2
    //          so this question is to calculate the distance of every node from its leaf children
    //          for each recursive call, we can only get access to current root, and left node, and right node and we also need to know the distance of left and right, so that we can calculate the current distance
    //          so the return value is int, as we are process previous result immediately.
    public static int DistanceFromLeaf(TreeNode node, ArrayList<ArrayList<Integer>> res) {
        if (node == null)
            return 0;
        int leftBottomUpHeight = DistanceFromLeaf(node.left, res);
        int rightBottomUpHeight = DistanceFromLeaf(node.right, res);
        int currentHeight = Math.max(leftBottomUpHeight, rightBottomUpHeight) + 1;
        if (currentHeight > res.size())
            res.add(new ArrayList<>());
        res.get(currentHeight - 1).add(node.val);
        return currentHeight;

    }

}
