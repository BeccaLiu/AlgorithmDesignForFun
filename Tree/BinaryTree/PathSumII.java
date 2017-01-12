package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/9/17.
 */
public class PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{5, 4, 8, 11, 0, 13, 9, 7, 2});

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        hasPathSum(root, sum, 0, list, new ArrayList<Integer>());
        return list;
    }

    public void hasPathSum(TreeNode root, int sum, int curSum, List<List<Integer>> rt, ArrayList<Integer> list) {
        if (root.left == null && root.right == null) {
            if (curSum + root.val == sum) {
                list.add(root.val);
                rt.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1);
                //remember to remove at each iteration
            }
            return;
        }
        list.add(root.val);
        if (root.right != null)
            hasPathSum(root.right, sum, curSum + root.val, rt, list);
        if (root.left != null)
            hasPathSum(root.left, sum, curSum + root.val, rt, list);
        list.remove(list.size() - 1); //remember to remove at each iteration
    }
}
