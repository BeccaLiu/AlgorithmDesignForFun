package Linear.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/20/17.
 * 163. Missing Ranges
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class MissingRange {
    public static void main(String[] args) {
        System.out.print(findMissingRanges(new int[]{1, 1, 1, 2, 2}, 1, 2));
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            int diff = upper - lower;
            if (diff == 0)
                list.add(lower + "");
            else
                list.add(lower + "->" + upper);
            return list;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (lower < nums[0]) {
                    int diff = nums[0] - lower;
                    if (diff == 1)
                        list.add(lower + "");
                    else
                        list.add(lower + "->" + (nums[0] - 1));
                }
            }
            if (i == nums.length - 1) {
                if (upper > nums[nums.length - 1]) {
                    int diff = upper - nums[nums.length - 1];
                    if (diff == 1)
                        list.add(upper + "");
                    else
                        list.add((nums[nums.length - 1] + 1) + "->" + upper);
                }
                break;
            }
            if (nums[i] == nums[i + 1])
                continue;

            int diff = nums[i + 1] - nums[i];
            if (diff == 1)
                continue;
            else if (diff == 2)
                list.add(nums[i] + 1 + "");
            else
                list.add((nums[i] + 1 + "->" + (nums[i + 1] - 1)));
        }
        return list;


    }
}
