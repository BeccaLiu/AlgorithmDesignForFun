package Graph.backtracking;

import java.util.Arrays;

/**
 * Created by rliu on 1/21/17.
 * 377. Combination Sum IV
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
public class combinationSumIV {
    public static void main(String[] args) {
        //System.out.println(combinationSum4(new int[]{3, 33, 333}, 10000));
        System.out.println(combinationSum4Me(new int[]{3, 33, 333}, 10000));
        System.out.println(combinationSum4DP(new int[]{3, 33, 333}, 10000));
    }

    public static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        Arrays.sort(nums);
        return helper(target, nums, 0);
    }

    public static int helper(int target, int[] arr, int pos) {
        if (target == 0) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += helper(target - arr[i], arr, i); //for next level and current level, we do not want duplicate, so the pos will be current pos+1
            }
        }
        return count;
    }

    //time complexity: O(nums.length^n) each level has nums.length branches
    public static int combinationSum4Me(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        //[Key]if we do not fill memo with -1 first and using condition count[target]!=-1, we will receive Time exceed error for input [3, 33, 333],10000
        //as the res is 0 for input [3,33,333] the memo[target] is set to 0, but you can not differentiate the default 0 and result 0
        memo[0] = 1;
        return helperMemorized(target, nums, memo);
    }

    //if you draw the whole solution tree, you will find that there are identical subtree here, so we came up an idea of memorized the result of subtreee
    //number 1  2  3  4
    //count  1  2  4  1+2+4
    //for root=1 only has [1]
    //for root=2 has 2(1,2)=(1,0)->root(1)+1=2
    //for root=3 has 3(1,2,3)=(2,1,0)=root(2)+root(1)+1=2+1+1=4
    public static int helperMemorized(int target, int[] arr, int[] count) {
        if (count[target] != -1) {
            return count[target];
        }

        int rt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                rt += helperMemorized(target - arr[i], arr, count); //for next level and current level, we do not want duplicate, so the pos will be current pos+1
            }
        }
        count[target] = rt;
        return rt;
    }

    //find the induction rule-> root[i]=Sum(root[i-nums[j]],i-nums[j]>=0)
    public static int combinationSum4DP(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int k = 0; k < nums.length; k++) {
                if (i - nums[k] >= 0)
                    memo[i] += memo[i - nums[k]];
            }
        }
        return memo[target];
    }
}
