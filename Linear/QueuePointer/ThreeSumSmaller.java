package Linear.QueuePointer;

import java.util.Arrays;

/**
 * Created by rliu on 12/10/16.
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in O(n^2) runtime?
 * Primitive Idea is a O(n^3) solution, using three for loop and permutation all the possibility, calculate the sum, compare with target, and count the triplets
 */
public class ThreeSumSmaller {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 1, 3};
        Arrays.sort(nums); //take nlogn to sort
        int target = 2;
        int count = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;
                    left++;
                } else
                    right--;
            }
        }
        System.out.print(count);

    }
}
