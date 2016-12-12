package Tree.BinaryTree;

/**
 * Created by rliu on 12/11/16.
 * Invert a binary tree.
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * to
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{4, 2, 7, 1, 3, 6, 9});
        invertTree(root);
        InOrderIterator iterator = new InOrderIterator(root);
        while (iterator.hasNext())
            System.out.print(iterator.next().val + " ");

    }

    public static void invertTree(TreeNode root) {
        if (root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
    }

}
