package Linear.QueuePointer;

import java.util.Arrays;

/**
 * Created by rliu on 1/2/17.
 */
public class ThreeSumCloset {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{6, -18, -20, -7, -15, 9, 18, 10, 1, -20, -17, -19, -3, -5, -19, 10, 6, -11, 1, -17, -15, 6, 17, -18, -3, 16, 19, -20, -3, -17, -15, -3, 12, 1, -9, 4, 1, 12, -2, 14, 4, -4, 19, -20, 6, 0, -19, 18, 14, 1, -15, -5, 14, 12, -4, 0, -10, 6, 6, -6, 20, -8, -6, 5, 0, 3, 10, 7, -2, 17, 20, 12, 19, -13, -1, 10, -1, 14, 0, 7, -3, 10, 14, 14, 11, 0, -4, -15, -8, 3, 2, -5, 9, 10, 16, -4, -3, -9, -8, -14, 10, 6, 2, -12, -7, -16, -6, 10}, -52));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            //you are making wrong assumption
//            if (target < nums[i])
//                break;
            int left = i + 1;
            int right = nums.length - 1;
            int newTarget = target - nums[i];
            while (left < right) {
                int tempSum = nums[left] + nums[right];
                int diff = Math.abs(newTarget - tempSum);
                if (diff == 0)
                    return target;
                else {
                    if (diff < Math.abs(sum - target))
                        sum = tempSum + nums[i];
                }
                if (tempSum <= newTarget)
                    left++;
                else
                    right--;
            }
        }
        return sum;
    }
}
