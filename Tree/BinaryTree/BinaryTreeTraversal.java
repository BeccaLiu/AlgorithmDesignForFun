package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rliu on 1/10/17.
 */
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4, 5, 6});
        List<Integer> list = new LinkedList<>();
        if (root == null)
            return;
        postorderIte(root, list);
        System.out.println(list);
        return;
    }

    public static void preorderIte(TreeNode root, List<Integer> list) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(curr.val);
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
    }

    public static void postorderIte(TreeNode root, List<Integer> list) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(0, curr.val);
            if (curr.left != null)
                stack.push(curr.left);
            if (curr.right != null)
                stack.push(curr.right);
        }

    }

    public void preorderRec(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preorderRec(root.left, list);
        preorderRec(root.right, list);

    }

    public void inorderIte(TreeNode root, List<Integer> list) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(curr.val);
            if (curr.right != null) {
                curr = curr.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
        }
    }

    public void inorderRec(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        inorderRec(root.left, list);
        list.add(root.val);
        inorderRec(root.right, list);

    }

    public void postorderRec(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        postorderRec(root.left, list);
        postorderRec(root.right, list);
        list.add(root.val);

    }
}
