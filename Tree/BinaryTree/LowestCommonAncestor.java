package Tree.BinaryTree;

/**
 * Created by rliu on 1/12/17.
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * *_______6______
 * /              \
 * ___2__          ___8__
 * /      \        /      \
 * 0      _4       7       9
 * /  \
 * 3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * 236. Lowest Common Ancestor of a Binary Tree
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{6, 2, 8, 0, 4, 7, 9, 3, 5});
        TreeNode p = root.left;
        TreeNode q = root.left.right;

        TreeNode[] ancestor = new TreeNode[1];
        lowestCommonAncestor236(root, p, q, ancestor);
        System.out.println(ancestor[0].val);

    }

    //key is still like left and right split and root is ancestor, or root if p and q is a the other side
    //ancestor[] is a place where my function keep the result
    //every time running dfs, to find if find p or find q
    //if at a level of recursion,
    //1. we find the one of the node, we need to check if root is another node, then the ancestor is the root
    //2. if we find both node, we need to check if ancestor is already set, if not, the root is the ancestor, ancestor[] already has the value, we know the ancestor has been found in previous recursion.
    //we need to run dfs on all nodes, so the time complexity is O(n)
    //compare to BST question, which we know where is p and q, here we do not have the feature of BST, we still need to know where is p, where is q, and relationship with root
    public static boolean lowestCommonAncestor236(TreeNode root, TreeNode p, TreeNode q, TreeNode[] ancestor) {
        if (root == null)
            return false;
        boolean findAtLeft = lowestCommonAncestor236(root.left, p, q, ancestor);
        boolean findAtRight = lowestCommonAncestor236(root.right, p, q, ancestor);
        if ((findAtLeft && findAtRight && ancestor[0] == null) || (root == p || root == q) && (findAtLeft || findAtRight)) {//left has exist and right has exist and ancestor never be updated before
            ancestor[0] = root;
        }

        return findAtLeft || findAtRight || root == p || root == q; //check if we found root;

    }

    public TreeNode lowestCommonAncestor235(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return root;
        if (p == q)
            return p;
        while (root != p && root != q) { //when root fist find p or q, like previous example 2 and 4 's ancestor is 2
            if (p.val < root.val && q.val < root.val)
                //both node is at left bound
                root = root.left;
            else if (p.val > root.val && q.val > root.val)
                //both node is at right bound
                root = root.right;
            else if ((p.val > root.val && q.val < root.val) || (p.val < root.val && q.val > root.val))
                //if p and q is split by root.val, we know that root is ancestor
                return root;
        }
        return root;
    }
}
