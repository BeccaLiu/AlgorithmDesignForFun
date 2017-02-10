package Tree;

import Tree.BinaryTree.TreeNode;

/**
 * Created by rliu on 2/9/17.
 */
public class ConstructBinaryTreeInorderAndPreorder {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || inorder.length != preorder.length)
            return null;
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);

    }

    public static TreeNode buildTreeHelper(int[] preorder, int[] inorder, int pleft, int pright, int ileft, int iright) {
        if (pleft > pright)
            return null;
        int rootVal = preorder[pleft];

        int i = ileft;
        for (; i < iright; i++) {
            if (inorder[i] == rootVal)
                break;
        }
        int leftSize = i - ileft;
        int rightSize = iright - i;

        TreeNode root = new TreeNode(rootVal);

        root.left = buildTreeHelper(preorder, inorder, pleft + 1, pleft + leftSize, ileft, i - 1);
        root.right = buildTreeHelper(preorder, inorder, pleft + leftSize + 1, pright, i + 1, iright);
        return root;
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] in = new int[]{4, 2, 5, 1, 6, 3, 7};
        buildTree(pre, in);
    }
}
