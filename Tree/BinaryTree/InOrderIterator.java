package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 12/7/16.
 */
public class InOrderIterator {
    ArrayDeque<TreeNode> stack = new ArrayDeque<>();

    public InOrderIterator(TreeNode temp) {
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }

    public static void main(String[] args) {
        TreeNode curr = new TreeNode(new int[]{1, 2, 3, 4, 5, 6, 7});
        InOrderIterator iterator = new InOrderIterator(curr);
        while (iterator.hasNext()) {
            System.out.print(iterator.next().val + " ");
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public TreeNode next() {
        TreeNode curr = stack.pop();
        if (curr.right != null) {
            TreeNode next = curr.right;
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
        }
        return curr;
    }

}
