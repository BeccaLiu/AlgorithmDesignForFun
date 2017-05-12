package Linear.Array;

import java.util.HashMap;

/**
 * Created by rliu on 5/11/17.
 * 560. Subarray Sum Equals K
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * build the subarray list, and do the 2 sum
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        int[] subarraysum = new int[nums.length + 1];
        subarraysum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            subarraysum[i + 1] = subarraysum[i] + nums[i];
        }

        int count = 0;
        //using hashmap as it might be duplicate key is sum and value is count
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < subarraysum.length; i++) {
            Integer cnt = map.get(subarraysum[i] - k);
            if (cnt != null) {
                count += cnt;
            }
            map.put(subarraysum[i], map.getOrDefault(subarraysum[i], 0) + 1);

        }
        return count;

    }
}


