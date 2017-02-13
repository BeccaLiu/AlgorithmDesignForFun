package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/12/17.
 * 314. Binary Tree Vertical Order Travers
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * /\
 * /  \
 * 5   2
 * return its vertical order traversal as:
 * [
 * [4],
 * [9,5],
 * [3,0,1],
 * [8,2],
 * [7]
 * ]
 */
public class BTVerticalOrderTraversal {
    public static void main(String[] args) {
        System.out.println(verticalOrderBFS(new TreeNode(new int[]{3, 9, 8, 4, 0, 1, 7})));
    }

    public static List<List<Integer>> verticalOrderBFS(TreeNode root) {
        List<List<Integer>> resLeft = new ArrayList<>();
        List<List<Integer>> resRight = new ArrayList<>();

        ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<>();
        ArrayDeque<Integer> levelQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        levelQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = nodeQueue.poll();
                int level = levelQueue.poll();
                if (level >= 0) {
                    if (resRight.size() == level) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        resRight.add(temp);
                    }
                    resRight.get(level).add(curr.val);
                } else {
                    if (resLeft.size() == -level - 1) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        resLeft.add(temp);
                    }
                    resLeft.get(-level - 1).add(curr.val);
                }
                if (curr.left != null) {
                    nodeQueue.offer(curr.left);
                    levelQueue.offer(level - 1);
                }
                if (curr.right != null) {
                    nodeQueue.offer(curr.right);
                    levelQueue.offer(level + 1);
                }
            }

        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = resLeft.size() - 1; i >= 0; i--) {
            res.add(resLeft.get(i));
        }
        for (int i = 0; i < resRight.size(); i++) {
            res.add(resRight.get(i));
        }
        return res;

    }


    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> resLeft = new ArrayList<>();
        List<List<Integer>> resRight = new ArrayList<>();

        dfsHelper(root, resLeft, resRight, 0);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = resLeft.size() - 1; i >= 0; i--) {
            res.add(resLeft.get(i));
        }
        for (int i = 0; i < resRight.size(); i++) {
            res.add(resRight.get(i));
        }
        return res;
    }

    //use level to denote how far from center node root,if go left level-1, if go right level+1
    //dfs is working
    public static void dfsHelper(TreeNode root, List<List<Integer>> resLeft, List<List<Integer>> resRight, int level) {
        if (root == null)
            return;

        if (level >= 0) {
            if (resRight.size() == level) {
                ArrayList<Integer> temp = new ArrayList<>();
                resRight.add(temp);
            }
            resRight.get(level).add(root.val);
        } else {
            if (resLeft.size() == -level - 1) {
                ArrayList<Integer> temp = new ArrayList<>();
                resLeft.add(temp);
            }
            resLeft.get(-level - 1).add(root.val);
        }
        dfsHelper(root.left, resLeft, resRight, level - 1);
        dfsHelper(root.right, resLeft, resRight, level + 1);

    }


}
