package Tree.BinarySerach;

/**
 * Created by rliu on 1/9/17.
 * 35.Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no *duplicates* in the array.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1 return left
 * [1,3,5,6], 7 → 4 return left when while loop end
 * [1,3,5,6], 0 → 0 return left
 * accordingly, insert into the place where the nearest number larger than target
 * Analysis Binary Search:
 * 1. when left=i,right=i+1 => mid=i, if(target==arr[mid]) return target, if(target<arr[mid]) right=i-1;left=mid=i; if(target>arr[mid]) mid=i, left=right=i+1(the following of this case is left=right case)
 * 2. when left=right=i => mid=i, if(target<arr[mid]) right=i-1;left=mid=i; if(target>arr[mid]) mid=right=i, left=i+1
 * so the place need to be insert is always the left node
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        System.out.print(searchInsert(new int[]{1, 3, 5, 6}, 5));

    }

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}
