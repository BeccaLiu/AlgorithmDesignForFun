package Advance;

/**
 * Created by rliu on 1/24/17.
 * 213. House Robber II
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobberII {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{0}));
    }

    //if we can only rob the first or the last, so we can rob first and last separately and get the larger one
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        //rob first house [0,n-1]
        int[] rob = new int[nums.length];
        rob[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (i == 1)
                rob[i] = Math.max(nums[0], nums[1]);
            else
                rob[i] = Math.max(rob[i - 1], rob[i - 2] + nums[i]);
        }
        //do not rob first house, only rob [1,n]
        int max = rob[nums.length - 2];
        rob[1] = nums[1];
        for (int i = 2; i <= nums.length - 1; i++) {
            if (i == 2)
                rob[i] = Math.max(nums[1], nums[2]);
            else
                rob[i] = Math.max(rob[i - 1], rob[i - 2] + nums[i]);
        }
        max = Math.max(max, rob[nums.length - 1]);
        return max;
    }
}
