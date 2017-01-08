package Tree.BinaryTree;

/**
 * Created by rliu on 1/7/17.
 * 129. Sum Root to Leaf Numbers
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        System.out.print(sumNumbers(new TreeNode(new int[]{1, 2, 3, 4, 5})));

    }

    public static int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int[] sum = new int[1];
            sumNumber(root, root.val, sum);
            return sum[0];
        }
    }

    public static void sumNumber(TreeNode root, int pathSum, int[] sum) {
        if (root.left == null && root.right == null) {
            sum[0] += pathSum;
            return;
        } else {

            if (root.left != null)
                sumNumber(root.left, pathSum * 10 + root.left.val, sum);
            if (root.right != null)
                sumNumber(root.right, pathSum * 10 + root.right.val, sum);
        }
    }
}
