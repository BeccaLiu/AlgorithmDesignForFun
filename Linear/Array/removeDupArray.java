package Linear.Array;

/**
 * Created by rliu on 12/26/16.
 * 26. Remove Duplicates from Sorted Array
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */
public class removeDupArray {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 2, 2, 3}));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int cur = nums[0];
        int length = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != cur) {
                nums[length] = nums[i];
                cur = nums[i];
                length++;
            }
        }
        return length;
    }
}
