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
 * Key when to move head, when to move tail
 * currentSum=currentSum>0?currentSum+arr[i]:arr[i]
 * globalMax=currentSum>globalMax?currentSum:globalMax
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, -4};
        System.out.println(maximumSum(a));
        System.out.println(maxSubArrayDC(a, 0, a.length - 1));
    }

    public static int maximumSum(int[] arr) {
        if (arr == null || arr.length <= 0)
            return Integer.MIN_VALUE;
        int max = arr[0];
        int sum = arr[0];
        int start = 0;
        int end = 0;
        int curStart = 0;

        for (int curr = 1; curr < arr.length; curr++) {
            //curr is the mark the current end pointer
            //currStart is mark the current start pointer
            //every time when sum is decreasing, we reset the sum, and reset the currStart pointer
            if (arr[curr] > arr[curr] + sum)
                curStart = curr;
            sum = Math.max(arr[curr], arr[curr] + sum);

            if (sum > max) { //every time we update the sum, we update the overall start and end pointer
                start = curStart;
                end = curr;
            }
            max = Math.max(sum, max);
        }
        System.out.println(start + ":" + end);
        return max;
    }

    //divide and conquer solution which take logn time
    public static int maxSubArrayDC(int[] nums, int start, int end) {
        if (start == end)
            return nums[start];

        int mid = start + (end - start) / 2;
        int left = maxSubArrayDC(nums, start, mid);
        int right = maxSubArrayDC(nums, mid + 1, end);
        int both = midCrossingSum(nums, start, end, mid);
        return Math.max(Math.max(left, right), both);
    }

    public static int midCrossingSum(int[] nums, int i, int j, int m) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int l = m;
        int r = m + 1;

        int sum = 0;
        while (r <= j) {
            sum += nums[r++];
            if (sum > rightSum)
                rightSum = sum;
        }

        sum = 0;
        while (l >= i) {
            sum += nums[l--];
            if (sum > leftSum)
                leftSum = sum;
        }
        return leftSum + rightSum;
    }

    public int maxSubArray(int[] nums, int i, int j) {
        if (nums == null || nums.length == 0)
            return 0;
        int tail = i + 1;
        int globalMax = nums[i];
        int currentSum = nums[i];

        while (tail <= j) { //with idea of greedy and dp
            currentSum = currentSum < 0 ? nums[tail] : currentSum + nums[tail];
            tail++;
            globalMax = currentSum > globalMax ? currentSum : globalMax;
        }
        return globalMax;

    }
}
