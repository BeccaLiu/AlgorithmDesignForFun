package Linear.LinkedList;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;

/**
 * Created by rliu on 4/11/17.
 */
public class SearchRange {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{20, 8, 22, 4, 12});
        System.out.println(searchRange(root, 10, 22));
    }

    public static ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        if (root == null)
            return new ArrayList<Integer>();

        ArrayList<Integer> list = new ArrayList<>();

        if (root.left != null && root.val >= k1)
            list.addAll(searchRange(root.left, k1, k2));
        if (root.val >= k1 && root.val <= k2)
            list.add(root.val);
        if (root.right != null && root.val <= k2)
            list.addAll(searchRange(root.right, k1, k2));
        return list;
    }
}
