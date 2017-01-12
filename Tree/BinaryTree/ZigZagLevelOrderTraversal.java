package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rliu on 11/23/16.
 * Zigzag level order traversal
 * Given a binary tree, return the level order of its nodes value
 * 3             {[3]
 * 9    2          ,[2,9]
 * 1 3   4 6         ,[1,3,4,6]}
 * Key: using deque and define a flag(leftToRight)
 * <p>
 * Can using double-linked list
 */
public class ZigZagLevelOrderTraversal {
    public static void main(String[] args) {
        int[] a = {3, 9, 2, 1, 3, 4, 6, 0, 7, 8};
        TreeNode t = new TreeNode(a);
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        boolean leftToRight = true;
        deque.add(t);
        while (!deque.isEmpty()) {
            int size = deque.size();

            ArrayList<Integer> temp = new ArrayList<>();
            if (leftToRight)
                for (int i = 0; i < size; i++) {
                    TreeNode cur = deque.pollFirst();
                    temp.add(cur.val);
                    if (cur.left != null)
                        deque.addLast(cur.left);
                    if (cur.right != null)
                        deque.addLast(cur.right);
                }
            else
                for (int i = 0; i < size; i++) {
                    TreeNode cur = deque.pollLast();
                    temp.add(cur.val);
                    if (cur.right != null)
                        deque.addFirst(cur.right);
                    if (cur.left != null)
                        deque.addFirst(cur.left);
                }
            list.add(temp);
            leftToRight = !leftToRight;
        }
        System.out.println(list);
    }

    public List<List<Integer>> zigzagLevelOrderImproved(TreeNode root) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null)
            return list;
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        boolean isLeftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);

                //just zigzag when add to list
                if (isLeftToRight)
                    l.add(cur.val);
                else
                    l.add(0, cur.val);
            }
            isLeftToRight = !isLeftToRight;
            list.add(l);
        }

        return list;

    }
}
