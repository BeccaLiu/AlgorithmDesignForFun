package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 3/13/17.
 */
public class ConvertBST2DLL {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(new Object[]{7, 3, 8, 1, 5, null, null, null, 2, 4, 6});

        covertBST2DDLIte(treeNode);
    }

    static TreeNode covertBST2DDLIte(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        TreeNode rt = stack.peek();
        TreeNode pre = null;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (pre != null) { //
                pre.right = curr;
                curr.left = pre;
                System.out.println(pre.val + " " + curr.val);
            }
            pre = curr;

            curr = curr.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return rt;
    }
}

