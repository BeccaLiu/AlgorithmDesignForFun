package Tree.BinaryTree;

/**
 * Created by rliu on 1/9/17.
 * 110. Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(isBalanced(root));
    }

    //for each node, need to get Height once, the time complexity is O(n*h);
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        return Math.max(leftH, rightH) + 1;

    }


    //for each layer, if the left node or right node is not balance, we just return not balance
    //if the left node and right node is balance, we need to know the height of left and right, to determin, if current level is balance;
    //so we need two information, one is height which is int, the other is balanced or not which is boolean,
    //how to return the two information just using one value?
    //we know the height can not be negative, so we set height to negative to inform that the node is not balanced
    public static int getHeightfromLeaf(TreeNode root) {
        if (root == null)
            return 0;
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        if (leftH == Integer.MIN_VALUE || rightH == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        if (Math.abs(leftH - rightH) > 1)
            return Integer.MIN_VALUE;
        return Math.max(leftH, rightH) + 1;
    }

}
