package Linear.Array;

import Tree.BinaryTree.TreeNode;

/**
 * Created by rliu on 1/27/17.
 */
public class ConvertSortedArrayToTree {
    public static void main(String[] args) {
        System.out.println(sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6}));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public static TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if (l > r)
            return null;
        //using l==r will stackoverflow
        //as for 1,2,3,4,5,6 ->(0,5)mid=3  ->(0,1) mid=1 -> (0,-1) mid=-1, there might not be a chance that l==r,
        //when l=0,r=-1, we should return, but if you set l==r, mid=(0+1)/2=0 and next itr will be (0,-1) again it will not return the node you want
//        if (l == r)
//            return new TreeNode(l);

        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, l, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, r);
        return root;
    }
}
