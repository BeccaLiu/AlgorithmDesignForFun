package Advance;

/**
 * Created by rliu on 1/24/17.
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        for (int i = 1; i < nums.length; i++)
            if (i == 1)
                nums[1] = Math.max(nums[0], nums[1]);
            else
                nums[i] = Math.max(nums[i - 1], nums[i - 2] + nums[i]);
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{7, 6}));
        System.out.println(rob(new int[]{7}));
        System.out.println(rob(new int[]{2, 1, 1, 2}));
        System.out.println(rob(new int[]{7, 6, 2, 1, 6, 9}));
    }
}
