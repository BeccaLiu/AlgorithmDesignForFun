package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 4/22/17 11:26 PM.
 */
public class MaximumSubarrayDifference {
    public static void main(String[] args) {
        System.out.println(2147483647 + 1);
        System.out.println(maxDiffSubArrays(new int[]{-2, 1, -3, 4, -1, 2, 1, 5}));

    }

    public static int maxDiffSubArrays(int[] nums) {
        // write your code here
        int max = Integer.MIN_VALUE;
        int[] ls = new int[nums.length]; //largest left smallest right
        int[] sl = new int[nums.length];//smallest left largest right
        ls[0] = nums[0];
        sl[0] = nums[0];

        int sumLg = nums[0];
        int sumSm = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            sumLg = sumLg < 0 ? nums[i] : nums[i] + sumLg;//!!!!!
            sumSm = sumSm < 0 ? nums[i] + sumSm : nums[i];
            ls[i] = Math.max(sumLg, ls[i - 1]);
            sl[i] = Math.min(sumSm, sl[i - 1]);
        }
        System.out.println(Arrays.toString(ls));

        sumLg = nums[nums.length - 1];
        sumSm = nums[nums.length - 1];
        ls[nums.length - 1] = sl[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            max = Math.max(max, Math.max(Math.abs(sl[i] - sl[i + 1]), Math.abs(ls[i] - ls[i + 1])));

            sumLg = sumLg < 0 ? nums[i] : nums[i] + sumLg;//!!!!!
            sumSm = sumSm < 0 ? nums[i] + sumSm : nums[i];

            ls[i] = Math.min(ls[i + 1], sumSm);
            sl[i] = Math.max(sumLg, sl[i + 1]);

        }
        return max;
    }
}
