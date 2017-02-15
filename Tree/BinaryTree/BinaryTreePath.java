package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/15/17.
 */
public class BinaryTreePath {
    public static void main(String[] args) {

    }

    public static void pathHelper(TreeNode root, List<String> res, ArrayList<Integer> list) {
        if (root.left == null && root.right == null) {
            list.add(root.val);
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append("->").append(list.get(i));
            }
            res.add(sb.toString());
            list.remove(list.size() - 1);
            return;
        }

        list.add(root.val);
        if (root.left != null)
            pathHelper(root.left, res, list);
        if (root.right != null)
            pathHelper(root.right, res, list);
        list.remove(list.size() - 1);

    }

    public static void pathHelperWithString(TreeNode root, List<String> res, String path) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
        }

        if (root.left != null)
            pathHelperWithString(root.left, res, path + root.val + "->");
        if (root.right != null)
            pathHelperWithString(root.right, res, path + root.val + "->");

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rt = new ArrayList<>();
        if (root == null)
            return rt;

        pathHelper(root, rt, new ArrayList<Integer>());
        return rt;
    }
}
