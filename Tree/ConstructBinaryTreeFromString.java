package Tree;

import Tree.BinaryTree.TreeNode;

/**
 * Created by rliu on 5/11/17.
 * Construct Binary Tree from String
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 * <p>
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 * <p>
 * 4
 * /   \
 * 2     6
 * / \   /
 * 3   1 5
 */
public class ConstructBinaryTreeFromString {
    public static void main(String[] args) {
        String s = "4(2(3)(1))(6(5))";
        System.out.println(str2tree(s, 0, s.length() - 1));

    }

    public static TreeNode str2tree(String s, int left, int right) {
        if (left > right)
            return null;
        if (left == right)
            return new TreeNode(s.charAt(left) - '0');

        TreeNode root = new TreeNode(s.charAt(left) - '0');
        int i = left + 1;
        int count = 0;
        while (i < s.length() - 1) {
            if (s.charAt(i) == '(')
                count++;
            if (s.charAt(i) == ')')
                count--;
            if (count == 0) {
                break;
            }
            i++;
        }
        root.left = str2tree(s, left + 2, i - 1);
        root.right = str2tree(s, i + 2, right - 1);
        return root;
    }

}
