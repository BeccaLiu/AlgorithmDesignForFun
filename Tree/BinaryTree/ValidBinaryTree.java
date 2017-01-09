package Tree.BinaryTree;

/**
 * Created by rliu on 11/20/16.
 * Valid Binary Tree
 * Primitive idea will be using a array to store the inorder traverse, and the space complexity is O(n)
 * However, we can solve this problem using O(1) space by using a max to identify the max value of all the traversed node
 * Key: how to think of using a min and a max to define the range
 */
public class ValidBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.right.right = new TreeNode(13);
        System.out.print(isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    //however, as we are using recursive stack to track, so the space is O(logn)
    //it is hard to compare the cross layer value, so we using max and min to defined a valid range.
    public static boolean isValid(TreeNode curr, int min, int max) {
        if (curr == null)
            return true;
        //current level: check root.val
        if (curr.val >= max || curr.val <= min)
            return false;
        //recursion down
        return isValid(curr.left, min, curr.val) && isValid(curr.right, curr.val, max);
    }


    //this method is slower, because the previous method will stop recursive down when it found a invalid node,(it may not check every node) which the worst case is O(n) when the error happened at leaf.
    //but this method is recursive down to the leaf, and get back, it will check every node, which is definitely O(n)
    public static boolean isValidslower(TreeNode curr, int min, int max) {
        if (curr == null)
            return true;
        //recursion down
        return isValid(curr.left, min, curr.val) && isValid(curr.right, curr.val, max) && curr.val < max && curr.val > min;
        //but you can change it to : to make it same as previous
        //return curr.val < max && curr.val > min&&isValid(curr.left, min, curr.val) && isValid(curr.right, curr.val, max);
    }


}
