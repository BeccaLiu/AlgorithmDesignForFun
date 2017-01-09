package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 1/8/17.
 */
public class KthSmallestElementBST {
    public static void main(String[] args) {
        System.out.println(kthSmallestIte(new TreeNode(new int[]{4, 3, 5, 1}), 2));

    }

    //in order iterative
    public static int kthSmallestIte(TreeNode root, int k) {
        if (root == null)
            return 0;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        int i = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            i++;
            if (i == k)
                return cur.val;
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return root.val;

    }

    //in order recursive ,can not think of recursive solution even with the help of iterative solution
    public static int kthSmallestRec(TreeNode root, int k) {
        int[] count = new int[]{k};
        int[] val = new int[1];
        helper(root, count, val);
        return val[0];
    }

    public static void helper(TreeNode root, int[] count, int[] val) {
        if (root == null)
            return;
        helper(root.left, count, val);
        if (--count[0] == 0) {
            val[0] = root.val;
            return;
        }
        helper(root.right, count, val);
    }

    //binary search fastest way!! unable to think of this solution
    //However, the time complexity of this method is still O(n) in worst case, as it is using the idea of binary search, but actually did not get rid of half soultion space
    public static int kthSmallestBinary(TreeNode root, int k) {
        if (root == null)
            return -1;
        int[] res = new int[]{Integer.MIN_VALUE};
        helper(root, k, res);
        return res[0];
    }

    public static int helper(TreeNode root, int k, int[] res) {
        if (res[0] != Integer.MIN_VALUE && root == null)
            return 0;
        int leftCount = helper(root.left, k, res);
        if (leftCount + 1 == k)
            res[0] = root.val;
        int rightCount = helper(root.right, k - leftCount - 1, res);

        return leftCount + rightCount + 1;

    }
}
