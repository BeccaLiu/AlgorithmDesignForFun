package Tree.BinarySerach;

/**
 * Created by rliu on 1/11/17.
 * 34. Search for a Range
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class SearchForARange {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int[] range = new int[]{nums.length, -1};
        int target = 1;
        searchRangeRec(nums, target, 0, nums.length - 1, range);
        //searchRange(nums,target);
        if (range[0] > range[1]) range[0] = -1;
        System.out.println(range[0] + " " + range[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        int found = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                found = mid;
                break;
            } else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        if (found == -1)
            return new int[]{-1, -1};

        left = found;
        right = found;
        while (left > 0 && nums[left] == nums[left - 1])
            left = left - 1;
        while (right < nums.length - 1 && nums[right] == nums[right + 1])
            right = right + 1;
        return new int[]{left, right};

    }


    public static void searchRangeRec(int[] nums, int target, int left, int right, int[] range) {
        if (right < left)
            return;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            //mid need to smaller than range[0] or bigger than range[1]
            if (mid < range[0]) {
                range[0] = mid;
                searchRangeRec(nums, target, left, mid - 1, range);
            }
            if (mid > range[1]) {
                range[1] = mid;
                searchRangeRec(nums, target, mid + 1, right, range);
            }
        } else if (nums[mid] > target)
            searchRangeRec(nums, target, left, mid - 1, range);
        else
            searchRangeRec(nums, target, mid + 1, right, range);
    }
}
