package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 11/13/16.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int[] arr) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        this.val = arr[0];
        queue.offer(this);
        for (int i = 0; i * 2 + 2 < arr.length; i++) {
            TreeNode curr = queue.poll();
            TreeNode left = new TreeNode(arr[i * 2 + 1]);
            TreeNode right = new TreeNode(arr[i * 2 + 2]);
            curr.left = left;
            curr.right = right;
            queue.offer(left);
            queue.offer(right);
        }
    }
}
