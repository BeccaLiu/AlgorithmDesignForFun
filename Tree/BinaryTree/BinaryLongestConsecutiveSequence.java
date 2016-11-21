package Tree.BinaryTree;

/**
 * Created by rliu on 11/20/16.
 * Binary Tree Longest Consecutive Sequence
 * Given a binary tree, find the length of the longest consecutive sequence
 */
public class BinaryLongestConsecutiveSequence {
    public static void main(String[] args) {

    }

    public int longest(TreeNode root, int curLen, int prev) {
        if (root == null)
            return curLen;
        if (root.val == prev + 1)
            curLen++;
        else
            curLen = 1;
        int left = longest(root.left, curLen, root.val);
        int right = longest(root.right, curLen, root.val);
        return Math.max(Math.max(left, right), curLen);
    }
}
