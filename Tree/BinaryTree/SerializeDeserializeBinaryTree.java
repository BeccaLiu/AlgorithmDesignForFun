package Tree.BinaryTree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 2/2/17.
 * 297. Serialize and Deserialize Binary Tree
 * key is to using queue
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(deserialize("[1,null,3,40,null,55]"));
    }

    public static String serialize(TreeNode root) {
        if (root == null)
            return "";
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(root.val).append(",");

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                    sb.append(curr.left.val).append(",");
                } else
                    sb.append("null,");
                if (curr.right != null) {
                    queue.offer(curr.right);
                    sb.append(curr.right.val).append(",");
                } else
                    sb.append("null,");
            }
        }
        int end = sb.length() - 1;
        while (!Character.isDigit(sb.charAt(end))) {
            end--;
        }
        sb.replace(end + 1, sb.length(), "]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)
            return null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        int start = getSplitIdx(data, 0);

        String rootVal = data.substring(1, start);
        TreeNode root;
        if (rootVal.equals(null))
            root = null;
        else {
            root = new TreeNode(Integer.parseInt(rootVal));
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                int end = getSplitIdx(data, start);
                String left = end > start + 1 ? data.substring(start + 1, end) : "null";
                int end2 = getSplitIdx(data, end);
                String right = end2 > end + 1 ? data.substring(end + 1, end2) : "null";
                if (left.equals("null"))
                    curr.left = null;
                else {
                    curr.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(curr.left);
                }
                if (right.equals("null"))
                    curr.right = null;
                else {
                    curr.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(curr.right);
                }
                start = end2;
            }
        }
        return root;
    }

    public static int getSplitIdx(String data, int start) {
        int end = start + 1;
        while (end < data.length() && !(data.charAt(end) == ',' || data.charAt(end) == ']'))
            end++;
        return end;
    }

}
