package Tree.BinaryTree;

/**
 * Created by rliu on 11/22/16.
 * Sorted array to BST
 * Given a sorted array, convert it to a height balanced BST
 * Height balanced BST: keep its height as small as possible->the difference between left and right is as small as possible
 * using recursive function when facing identical sub problem
 */
public class SortedArrayToBST {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode tree = buildBST(arr, 0, arr.length - 1);
    }

    public static TreeNode buildBST(int[] arr, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode rt = new TreeNode(arr[mid]);
        rt.left = buildBST(arr, start, mid - 1);
        rt.right = buildBST(arr, mid + 1, end);
        return rt;
    }
}
