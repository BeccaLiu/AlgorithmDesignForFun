package Tree.BinaryTree;

/**
 * Created by rliu on 12/10/16.
 * Count complete treenode
 * Given a complete binary tree, count the number of nodes.
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * time complexity = logn+log(n-1)+log(n-2)+....+1?
 * TODO:
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        System.out.print(count(node));
        System.out.print(countNodes(node));
    }

    //count the number of node including input
    //[I am stuck here]: why my code is slow, is because I count left and also right, but with the correct code, count(root.left) =count(root)-1,we only need to count right to compare with left
    public static int count(TreeNode node) {
        if (node.left == null && node.right == null)
            return 1; //here should return 1 but not 0!!!!
        int heightLeft = getHeight(node.left);
        int heightRight = getHeight(node.right);
        if (heightLeft == heightRight) {
            return 1 + (int) Math.pow(2, heightLeft) - 1 + count(node.right);
        } else
            return 1 + (int) Math.pow(2, heightRight) - 1 + count(node.left);
    }

    //just by using logn can get height;
    public static int getHeight(TreeNode node) {
        if (node == null)
            return -1;
        return getHeight(node.left) + 1;
    }

    static int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public static int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }
}
