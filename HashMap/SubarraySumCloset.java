package HashMap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rliu on 4/23/17 5:53 PM.
 * Subarray Sum Closest
 */
public class SubarraySumCloset {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(subarraySumClosest(new int[]{6, -4, -8, 3, 1, 7})));

    }

    public static int[] subarraySumClosest(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0)
            return new int[2];

        if (nums[0] == 0)
            return new int[]{0, 0};

        int[] rt = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();//key is sum, value is index

        int closet = Math.abs(nums[0]);
        int sum = nums[0];
        map.put(nums[0], 0);

        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum == 0) {
                rt[0] = 0;
                rt[1] = i;
                return rt;
            } else {
                if (Math.abs(sum) < closet) {
                    rt[0] = 0;
                    rt[1] = i;
                    closet = Math.abs(sum);
                } else {
                    Integer idx = map.get(sum);
                    if (idx != null) {
                        rt[0] = idx + 1;
                        rt[1] = i;
                        return rt;
                    }
                    for (int j = 1; j < closet; j++) {
                        Integer idxup = map.get(sum + j);
                        if (idxup != null) {
                            rt[0] = idxup + 1;
                            rt[1] = i;
                            closet = j;
                        }
                        Integer idxdow = map.get(sum - j);
                        if (idxdow != null) {
                            rt[0] = idxdow + 1;
                            rt[1] = i;
                            closet = j;
                        }
                    }
                }
                map.put(sum, i);
            }
        }
        return rt;
    }
}
