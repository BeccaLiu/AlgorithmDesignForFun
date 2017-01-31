package Tree.BinaryTree;

/**
 * Created by rliu on 1/30/17.
 * 285. Inorder Successor in BST
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 */
public class InorderSuccessorBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{6, 4, 8, 2, 5, 7, 9, 1, 3});
        TreeNode p = root.left.left.right;
        System.out.println(inorderSuccessor(root, p).val);
    }


    //logn solution, no need to iterate the whole tree which take O(n)
    //Idea, using logN to find the node, and also keep track of a tentative successor, as if p.right==null, the successor will be a already visited node
    //if p.right!=null, then successor will be the leftmost node of p.right
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return dfsHelper(root, p, null);
    }

    public static TreeNode dfsHelper(TreeNode root, TreeNode p, TreeNode succ) {
        if (p.val == root.val) {
            if (p.right == null)
                return succ;
            else {
                TreeNode temp = root.right;
                while (temp.left != null)
                    temp = temp.left;
                return temp;
            }
        }
        if (p.val < root.val) {
            succ = root;
            return dfsHelper(root.left, p, succ);
        } else {
            return dfsHelper(root.right, p, succ);
        }
    }
}
