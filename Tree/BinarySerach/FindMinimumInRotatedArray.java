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

        System.out.println(findMin(new int[]{5, 6, 7, 0, 1, 2, 4}));
        System.out.print(findMinDup(new int[]{7, 7, 7, 7, 7, 0, 1, 2}));
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

    public static int findMinDup(int[] num) {
        // write your code here
        int start = 0;
        int end = num.length - 1;
        while (start < end) {

            int mid = start + (end - start) / 2;
            if (num[mid] > num[end]) { //means min is at right side,and definitely not equals to mid, as mid is bigger than something
                start = mid + 1;
            } else if (num[mid] < num[end]) {// means min is at left side, but mid could be potential min,
                end = mid;
            } else {
                end--;
            }
        }
        return num[start];
    }
}
