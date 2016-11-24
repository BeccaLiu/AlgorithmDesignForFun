package Tree.BinaryTree;

/**
 * Recursion
 * Created by rliu on 11/20/16.
 * Symmetric tree
 * given a binary tree, check whether it is a mirror of itself
 * primitive idea, we can using inorder traverse to store the node value in array, and iterate the first half of the array, check whether it is mirror of second half of array
 * which take O(N) space to store the node value and O(n/2) to iterate the array.
 * However, can we do it in-place and using O(logn) space which is the function stack size
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(7);
        //root.right.right.right = new TreeNode(9);
        System.out.print(isSymmetric(root.left, root.right));
    }

    //Key: how to organize all the condition
    //first process the root node, then recurssive down to the leaf node
    public static boolean isSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        else if (a == null || b == null)
            return false;
        else if (a.val != b.val)
            return false;
        return isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left);
    }

}
