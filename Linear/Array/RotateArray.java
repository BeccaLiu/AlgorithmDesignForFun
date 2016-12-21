package Linear.Array;

/**
 * Created by rliu on 12/20/16.
 * 189. Rotate Array
 * Rotate an array of n elements to the right by k steps.
 * <p>
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */
public class RotateArray {
    public static void main(String[] args) {
        rotate1(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }

    public static void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;
        int len = nums.length;
        k = k % nums.length; //do not miss this, for cases like [1,2],3
        int[] arr = new int[len - k];

        for (int i = 0; i < len; i++) {
            if (i < k && i < len - k) {
                arr[i] = nums[i];
                nums[i] = nums[len - k + i];
            } else if (i >= k && i < len - k) {
                arr[i] = nums[i];
                nums[i] = arr[i - k];
            } else if (i < k && i >= len - k) {
                nums[i] = nums[i + len - k];
            } else
                nums[i] = arr[i - k];
        }
        System.out.println();
    }


}
