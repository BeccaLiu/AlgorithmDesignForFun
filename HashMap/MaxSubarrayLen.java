package HashMap;

import java.util.HashMap;

/**
 * Created by rliu on 2/12/17.
 * 325. Maximum Size Subarray Sum Equals k
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 */
public class MaxSubarrayLen {
    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[]{-1, 5, -3, 4, 1}, 3));
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = 0;

        HashMap<Integer, Integer> map = new HashMap<>(); //key is sumrange, and value is index

        //sumRange is (-1,index] range map.put(sumRange(index),index);
        //if get same sumrange, do not overwrite
        //k=SumRange(0,i)-SumRange(0,j); len=j-i;
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer index_a = map.get(sum - k);
            if (index_a != null) { //update the len if sum-k exist
                len = Math.max(len, i - index_a);
            }
            Integer index = map.get(sum); //put valid input
            if (index == null) {
                map.put(sum, i);
            }
        }
        return len;
    }
}
