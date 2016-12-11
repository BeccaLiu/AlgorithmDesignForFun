package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by rliu on 12/10/16.
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * <p>
 * Primitive Idea: Using O(n) time to go through whole tree and get the inorder array of tree, using binary search(lgN), find the place that target should be insert, using two pointer find k numbers should be return
 * better Solution based on primitive idea, are we doing redundant work? are we check the treenode that is useless to the result we want?
 * we can separate the tree into two part, the left part is all the nodes smaller than target, and the right is bigger than target
 * using 2 stack to store the left part and right part, and the top of the stack is the number closer to the target
 */
public class ClosetBinarySearchTreeValue2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{6, 4, 10, 1, 5, 8, 12});
        int k = 3;
        int target = 7;
        closetValue(root, target, k);
    }

    public static void closetValue(TreeNode root, int target, int k) {
        ArrayList<Integer> res = new ArrayList<>();

        ArrayDeque<Integer> s1 = new ArrayDeque<>();
        ArrayDeque<Integer> s2 = new ArrayDeque<>();
        inOrder(root, s1, s2, target);
        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)) {
                res.add(s1.pop());
            } else {
                res.add(s2.pop());
            }
        }
        System.out.println(res);
    }

    public static void inOrder(TreeNode root, ArrayDeque<Integer> sm, ArrayDeque<Integer> bg, int target) {
        if (root == null)
            return;
        inOrder(root.left, sm, bg, target);
        if (root.val < target) {
            sm.addFirst(root.val);

        } else {
            bg.addLast(root.val);
        }
        inOrder(root.right, sm, bg, target);

    }


}
