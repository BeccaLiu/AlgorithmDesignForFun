package Advance;

/**
 * Created by rliu on 11/24/16.
 * DP+Greedy
 * Given a integer array,find the contiguous subarray with the the largest sum
 * return the maximum sum
 * Analysis: primitive: two pointer - head+tail , O(n^2)
 * any repeated work? no cause every head and tail is different
 * any meaningless work?
 * find clue to reset head: when previous sum<0, it can only make the following sum smaller, we need to reset here
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, -4};
        System.out.println(maximumSum(a));
    }

    public static int maximumSum(int[] arr) {
        if (arr == null || arr.length <= 0)
            return Integer.MIN_VALUE;
        int max = arr[0];
        int sum = arr[0];

        for (int left = 1; left < arr.length; left++) {
            sum = Math.max(arr[left], arr[left] + sum);
            max = Math.max(sum, max);
        }
        return max;
    }
}
