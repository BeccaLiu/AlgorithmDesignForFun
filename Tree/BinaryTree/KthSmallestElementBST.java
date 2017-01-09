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
    //TODO: according someone's code share, I think this solution is slower
    public static int kthSmallestBinary(TreeNode root, int k) {
        return 0;

    }
}
