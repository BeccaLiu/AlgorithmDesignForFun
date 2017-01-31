package Linear.Array;

/**
 * Created by rliu on 1/30/17.
 * 209. Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinSubArrayLen {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{1, 1, 1, 1, 1, 1, 1}));
    }

    //another question using the idea of sliding windows
    //using two pointer, O(n) solution
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)
            return 0;
        int len = Integer.MAX_VALUE;

        int slow = 0;
        int fast = 0;
        int sum = 0;
        while (fast < nums.length) {
            sum += nums[fast];
            if (sum >= s) {
                while (sum >= s) {
                    len = Math.min(len, fast - slow + 1);
                    sum -= nums[slow];
                    slow++;
                }
            }
            fast++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;

    }


}
