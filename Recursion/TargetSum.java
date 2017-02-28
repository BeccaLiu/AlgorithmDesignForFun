package Recursion;

/**
 * Created by rliu on 2/26/17.
 * 494. Target Sum
 */
public class TargetSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays(arr, 3));

    }

    public static int findTargetSumWays(int[] nums, int S) {
        return dfsHelper(nums, 0, S, 0, true) + dfsHelper(nums, 0, S, 0, false);
    }

    public static int dfsHelper(int[] nums, int sum, int target, int index, boolean isAdd) {
        int tempSum = 0;
        if (isAdd) {
            tempSum = sum + nums[index];
        } else {
            tempSum = sum - nums[index];
        }

        if (index < nums.length - 1) {
            int a = dfsHelper(nums, tempSum, target, index + 1, true);
            int b = dfsHelper(nums, tempSum, target, index + 1, false);
            return a + b;
        } else
            return tempSum == target ? 1 : 0;

    }

    //this dp is a little bit different
    //https://discuss.leetcode.com/topic/76264/short-java-dp-solution-with-explanation
    public static int dpSolution(int[] nums, int S) {
        return 0;
    }
}
