package Linear.String;

/**
 * Created by rliu on 12/20/16.
 * 283. Move Zeroes
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * <p>
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZero {
    public static void main(String[] args) {
        moveZeroesPrim(new int[]{1});
    }

    //primitive idea will be count 0 and add non-0 numbers to new array, but this question asked to do it in-place without a copy of array
    //Two pointer, one is the start of 0 and the end of 0, using the idea of Quick Sort
    public static void moveZeroesPrim(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int start = -1;
        int curr = 0;
        while (curr < nums.length) {
            if (nums[curr] == 0 && start < 0) {
                start = curr;
            } else if (nums[curr] != 0 && start >= 0) {
                //swap
                nums[start++] = nums[curr];
                nums[curr] = 0;
            }
            curr++;
        }
        System.out.println();
    }

}
