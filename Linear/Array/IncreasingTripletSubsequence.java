package Linear.Array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rliu on 2/27/17.
 * 334. Increasing Triplet Subsequence
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{2, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1}));

    }

    //faster solution using just min and second Min
    //The main idea is keep two values when check all elements in the array:
    // the minimum value min until now and the second minimum value secondMin from the minimum value's position until now.
    // Then if we can find the third one that larger than those two values at the same time, it must exists the triplet subsequence and return true.
    // What need to be careful is: we need to include the condition that some value has the same value with minimum number, otherwise this condition will cause the secondMin change its value.
    //          2, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1
    //min       2  1  0  0  0  0  0  0  0  0  0  0
    //secondmin I  I  I  2  2  2  2  2  2  2  2  1

    //
    //     1, 0, 0, -1, 0, 0, 1000
    // min 1  0  0  -1  -1 -1
    // sec              0  0   true
    public static boolean increasingTripletF(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num; //num<=min do not update min
            else if (num < secondMin) secondMin = num; //here num>min<secondMin
            else if (num > secondMin) return true;
        }
        return false;
    }

    //unsolvable by sorting, to many corner cases, can only pass some test case;
    public static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        HashMap<Integer, int[]> map = new HashMap<>(); //using array to record the range of the first appearance index and last appearance
        for (int i = 0; i < nums.length; i++) {
            int[] r = map.get(nums[i]);
            if (map.get(nums[i]) == null)
                map.put(nums[i], new int[]{i, i});
            else
                map.get(nums[i])[1] = i;
        }

        Arrays.sort(nums);
        int maxLen = 0;
        int preIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) //skip case: 1,1,1,1,1
                continue;
            int[] range = map.get(nums[i]);
            if (range[1] > preIndex) {
                maxLen++;
                preIndex = range[0];
                if (maxLen >= 3)
                    return true;
            }
        }
        return false;
    }
}
