package Tree.BinaryTree;

/**
 * Created by rliu on 1/30/17.
 * 285. Inorder Successor in BST
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
 *
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
        if (p == null || root == null)
            return null;
        TreeNode successor = null;
        //find the successor that is parent of p for [1,2,3,4,5,6]
        while (p != root) {
            if (root == null)
                return null;
            if (p.val < root.val) {
                successor = root;
                root = root.left;
            } else
                root = root.right;
        }
        //if p=2 now the successor is 1
        //if p.right!=null which means the most closet node larger than p is at the right of p, we find the most left node of p.right
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null)
                successor = successor.left;
        }
        return successor;
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
