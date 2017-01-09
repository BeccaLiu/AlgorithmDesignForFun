package Tree.BinarySerach;

/**
 * Created by rliu on 1/9/17.
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * => to find the pivot point
 * pivot is where decreasing happened
 */
public class FindMinimumInRotatedArray {
    public static void main(String[] args) {
        System.out.print(findMin(new int[]{5, 6, 7, 0, 1, 2, 4}));
    }

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            //case 1: increasing scoop, the leftmost is the smallest
            if (nums[left] < nums[right])
                return nums[left];
            else {
                //case 2: decreasing scoop
                int mid = left + (right - left) / 2;
                if ((mid == 0 || nums[mid - 1] > nums[mid]) && (mid == nums.length - 1 || nums[mid] < nums[mid + 1]))
                    return nums[mid];
                else if (nums[left] > nums[mid] && nums[mid] < nums[right])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return nums[left];
    }
}
