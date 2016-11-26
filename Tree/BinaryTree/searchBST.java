package Tree.BinaryTree;

/**
 * Created by rliu on 11/21/16.
 * search and insert node in BST
 * BST: no duplicate
 */
public class searchBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.right.right = new TreeNode(13);
        System.out.println(searchRec(root, 7).val);
        System.out.println(searchIter(root, 7).val);
        System.out.print(deleteBST(root, 8));
        System.out.println();
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


    //To delete: 1. no children
    //           2. one children
    //           3. two children
    public static TreeNode deleteBST(TreeNode root, int val) {
        if (root == null)
            return root;
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        dummy.right = root;
        TreeNode pre = dummy;
        TreeNode cur = root;
        //first: find the target node
        while (cur != null) {
            if (cur.val == val)
                break;
            else if (cur.val < val) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }
        }
        // not find target node
        if (cur == null)
            return root;

        //has no child
        if (cur.left == null && cur.right == null)
            cur = null;
            //only has one child
        else if (cur.left == null || cur.right == null) {
            deleteNodeWithOneKids(pre, cur);
        } else {
            //has two children
            TreeNode prev = cur;
            TreeNode max = cur.left;
            while (max.right != null) {
                prev = max;
                max = max.right;

            }
            cur.val = max.val;
            if (max.left == null && max.right == null)
                deleteNodeWithZerokids(prev, max);
            else
                deleteNodeWithOneKids(prev, max);

        }
        return root;
    }

    public static void deleteNodeWithZerokids(TreeNode pre, TreeNode cur) {
        if (pre.left == cur)
            pre.left = null;
        else
            pre.right = null;
    }

    public static void deleteNodeWithOneKids(TreeNode pre, TreeNode cur) {
        if (cur.left == null) {
            if (pre.right == cur) {
                pre.right = cur.right;
            } else {
                pre.left = cur.right;
            }
        } else if (cur.right == null) {
            if (pre.right == cur) {
                pre.right = cur.left;
            } else {
                pre.left = cur.left;
            }
        }
    }

}
