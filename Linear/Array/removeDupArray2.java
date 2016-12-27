package Linear.Array;

/**
 * Created by rliu on 12/26/16.
 * Follow up for "Remove Duplicates":
 * 80. Remove Duplicates from Sorted Array II
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
public class removeDupArray2 {
    public static void main(String[] args) {
        removeDuplicates(new int[]{1});
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int length = 0;
        int i = 0;
        while (i < nums.length) {
            int cur = nums[i];
            int len = 0;
            nums[length++] = cur;
            while (i < nums.length && nums[i] == cur) {
                i++;
                len++;
            }
            if (len > 1)
                nums[length++] = nums[i - 1];
        }
        return length;
    }

}
