package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 1/8/17.
 * 116. Populating Next Right Pointers in Each Node
 * 117. Populating Next Right Pointers in Each Node II
 */
public class PopulateNextRightPointer {
    //my code works for any binary tree
    public static void connect(TreeLinkNode root) {
        if (root == null)
            return;
        ArrayDeque<TreeLinkNode> queue = new ArrayDeque<TreeLinkNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode curr = queue.poll();
                if (i == size - 1)
                    curr.next = null;
                else
                    curr.next = queue.peek();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }

    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(0);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);//perfect BT
        root.left.left = new TreeLinkNode(4); //any BT
        connect(root);
    }

    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

}
