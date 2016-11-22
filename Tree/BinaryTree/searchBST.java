package Tree.BinaryTree;

/**
 * Created by rliu on 11/21/16.
 * search and insert node in BST
 */
public class searchBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.right.right = new TreeNode(13);
        System.out.println(searchRec(root, 7));
        System.out.print(searchIter(root, 7));
        System.out.print(deleteBST(root, 10));
    }

    public static TreeNode searchRec(TreeNode root, int target) {
        if (root == null || root.val == target)
            return root;
        if (target > root.val)
            return searchRec(root.right, target);
        else
            return searchRec(root.left, target);
    }

    public static TreeNode searchIter(TreeNode root, int target) {
        if (root == null || root.val == target)
            return root;
        while (root != null) {
            if (root.val == target)
                return root;
            else if (root.val < target)
                root = root.right;
            else
                root = root.left;
        }
        return null;
    }

    public static TreeNode insertBSTIt(TreeNode root, int target) {
        if (root == null || root.val == target)
            return root;
        TreeNode newNode = new TreeNode(target);
        while (root != null) {
            if (root.val == target)
                return root;
            else if (root.val < target) {
                if (root.right == null)
                    root.right = newNode;
                else
                    root = root.right;
            } else if (root.left == null) {
                if (root.left == null)
                    root.left = newNode;
                else
                    root = root.left;
            }
        }
        return newNode;
    }

    public static TreeNode insertBSTRe(TreeNode root, int target) {
        if (root == null)
            return new TreeNode(target);
        if (root.val > target)
            root.left = insertBSTRe(root.left, target);
        else
            root.right = insertBSTRe(root.right, target);
        return root;
    }

    public static TreeNode deleteBST(TreeNode root, int target) {
        if (root == null)
            return root;
        TreeNode pre;
        TreeNode cur = root;
        while (cur != null) {

            if (cur.val == target)
                break;
            else if (cur.val < target) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }
        }
        //can not find it
        if (cur == null)
            return root;
        if (cur.left == null && cur.right == null)
            cur = null;
        else if (cur.left == null)
            cur = cur.right;
        else if (cur.right == null)
            cur = cur.left;
        else {
            TreeNode curr = cur.left;
            TreeNode prev = cur.left;
            while (curr.right != null) {
                prev = curr;
                curr = curr.right;

            }
            cur.val = curr.val;
            prev.right = null;

        }
        return root;
    }

}
