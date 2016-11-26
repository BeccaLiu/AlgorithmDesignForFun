package Tree.BinaryTree;

import java.util.ArrayList;

/**
 * Created by rliu on 11/24/16.
 */
public class PathSum {
    public static void main(String[] args) {
        int[] a = {-5, 1, 5, 10, 6, 2, 4, -1, -2, 3};
        TreeNode t = new TreeNode(a);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        findPathSum(t, new ArrayList<Integer>(), res, 7);
        System.out.println(res);

        res = new ArrayList<>();
        findPathSumSubsection(t, new ArrayList<Integer>(), res, 7);
        System.out.println(res);

        int[] max = {Integer.MIN_VALUE};
        findMaxPathSumSubsection(t, max);
        System.out.println(max[0]);
        max[0] = Integer.MIN_VALUE;
        findMaxPathSumAny(t, max);
        System.out.println(max[0]);
        max[0] = Integer.MIN_VALUE;
        findMaxPathSumLeaf(t, max);
        System.out.println(max[0]);
    }

    //Given a binary tree and a target, find all root to leaf paths where each path's sum is given target
    //time Complexity: O(n) : not O(logn) as the tree may be looks like a linkedlist, O(n) is the worst case
    //space complexity O(n)
    public static void findPathSum(TreeNode root, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res, int target) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            if (target == root.val) {
                list.add(root.val);
                //res.add(list) will not work, the output will be [],[],[]
                res.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        list.add(root.val);
        findPathSum(root.left, list, res, target - root.val);
        findPathSum(root.right, list, res, target - root.val);
        list.remove(list.size() - 1);
    }

    //subsection of complete path from root to leaf, and part of the subsection ==target, from end to check
    //Time complexity O(n*n)
    public static void findPathSumSubsection(TreeNode root, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res, int target) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
            int sum = 0;
            for (int i = list.size() - 1; i >= 0; i--) {
                sum += list.get(i);
                if (sum == target) {
                    res.add(new ArrayList<Integer>(list));
                    break;
                }
            }
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        findPathSumSubsection(root.left, list, res, target);
        findPathSumSubsection(root.right, list, res, target);
        list.remove(list.size() - 1);
    }

    //return the max path sum on the subsection of complete path from root to leaf
    //bottom up, go the the root first and start to process the return
    //three sub problem
    //leftMax, rightMax, Math.max(left,right)+root.val
    //Key: when the return left and right <0, we will not count it in, as it will descrease the max value
    public static int findMaxPathSumSubsection(TreeNode root, int[] globalMax) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;
        //when we get left<0, the max will be 0;
        int leftMax = Math.max(findMaxPathSumSubsection(root.left, globalMax), 0);
        int rightMax = Math.max(findMaxPathSumSubsection(root.right, globalMax), 0);
        int curMax = Math.max(leftMax, rightMax) + root.val;
        globalMax[0] = Math.max(globalMax[0], curMax);
        return curMax;
    }

    //return the max path sum on the path from any node to any other node
    public static int findMaxPathSumAny(TreeNode root, int[] globalMax) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;
        //when we get left<0, the max will be 0;
        int leftMax = Math.max(findMaxPathSumAny(root.left, globalMax), 0);
        int rightMax = Math.max(findMaxPathSumAny(root.right, globalMax), 0);
        int curMax = Math.max(leftMax, rightMax) + root.val;
        globalMax[0] = Math.max(globalMax[0], leftMax + rightMax + root.val);
        return curMax;
    }

    //return the max path sum on the path from any leaf node to any other leaf node
    public static int findMaxPathSumLeaf(TreeNode root, int[] globalMax) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;
        int leftMax = findMaxPathSumLeaf(root.left, globalMax);
        int rightMax = findMaxPathSumLeaf(root.right, globalMax);

        if (root.left != null && root.right != null) {
            globalMax[0] = Math.max(globalMax[0], leftMax + rightMax + root.val);
        }
        if (root.left == null)
            return root.val + rightMax;
        else if (root.right == null)
            return root.val + leftMax;
        else {
            return root.val + Math.max(leftMax, rightMax);
        }
    }
}
