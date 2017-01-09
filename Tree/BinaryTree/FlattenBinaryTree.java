package Tree.BinaryTree;

/**
 * Created by rliu on 1/8/17.
 * 114. Flatten Binary Tree to Linked List
 * Given a binary tree, flatten it to a linked list in-place.
 */
public class FlattenBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 5, 3, 4, 6});
        flattenHelper(root);
    }

    //Analysis: when we link two list, what we need to know?
    //1. current root node 2.left node 3.right node 4.leaf of left node 5.leaf of right node
    //what we need to do repeatedly, for any node,
    // left leaf node -> right node;
    // root.right=root.left;
    //root.left==null;
    // for any root node, in current recursive level, we know only left node, and right node, if we want to know the leaf, we need passing the parameter or return some value
    // if the leaf node will only be using currently, we choose return value;
    // if the leaf node will be using in other recursive, we will using array to store it.
    public static TreeNode flattenHelper(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) //deal with left and right is null here, aka this is the leaf node.
            return root;

        TreeNode leftLeaf = flattenHelper(root.left);
        TreeNode rightLeaf = flattenHelper(root.right);
        //three cases: left is null, right is null, left and right both not null
        if (leftLeaf == null)
            return rightLeaf;
        if (rightLeaf == null) {
            root.right = root.left;
            root.left = null;
            return leftLeaf;
        }
        leftLeaf.right = root.right;
        root.right = root.left;
        root.left = null;
        return rightLeaf;
    }
}
