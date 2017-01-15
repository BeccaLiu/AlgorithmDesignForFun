package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

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
        for (int i = 0; i * 2 + 1 < arr.length; i++) {
            TreeNode curr = queue.poll();
            TreeNode left = new TreeNode(arr[i * 2 + 1]);
            curr.left = left;
            queue.offer(left);
            if (i * 2 + 2 < arr.length) {
                TreeNode right = new TreeNode(arr[i * 2 + 2]);
                curr.right = right;
                queue.offer(right);
            }
        }
    }

    public TreeNode(Object[] arr) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        int i = 0;
        if (arr != null || arr.length != 0) {
            this.val = (int) arr[i++];
            queue.offer(this);

            while (!queue.isEmpty() && i < arr.length) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    TreeNode curr = queue.poll();
                    if (curr != null) {
                        Object left = i < arr.length ? arr[i++] : null;
                        Object right = i < arr.length ? arr[i++] : null;
                        if (left == null)
                            curr.left = null;
                        else {
                            curr.left = new TreeNode((Integer) left);
                            queue.offer(curr.left);
                        }
                        if (right == null)
                            curr.right = null;
                        else {
                            curr.right = new TreeNode((Integer) right);
                            queue.offer(curr.right);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Object[]{1, 2, 3, 4, null, 5, null, 6});
        System.out.println(root);
    }

    public String toString() {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(this);
        list.add(this.val);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left == null)
                    list.add(null);
                else {
                    list.add(curr.left.val);
                    q.offer(curr.left);
                }
                if (curr.right == null)
                    list.add(null);
                else {
                    list.add(curr.right.val);
                    q.offer(curr.right);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = list.size() - 1;
        while (i >= 0 && list.get(i) == null)
            i--;

        for (int j = 0; j <= i; j++) {
            sb.append(list.get(j) + " ");
        }
        return sb.toString();

    }
}
