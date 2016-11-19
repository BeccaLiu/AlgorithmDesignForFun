package Tree.BinarySerach;

/**
 * Created by rliu on 11/14/16.
 * Search in Rotated Sorted Array
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 1.You may assume no duplicate exists in the array.
 * <p>
 * Step 1: which part is mid in? monotone part or rotated part
 * Step 2: which part may target be in?
 * <p>
 * 2.if there is duplicates
 * Step 1: duplicates may affect the mid
 * * when this is a follow-up, try to think about what assumption the rules is breaking and how to fix it
 */
public class SearchRotatedArray {
    public static int searchRotatedNoDup(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] > arr[start]) {
                if (arr[start] <= target && target < arr[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (arr[mid] < target && target <= arr[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    public static int searchRotatedDup(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] > arr[start]) {
                if (arr[start] <= target && target < arr[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else if (arr[mid] < arr[start]) {
                if (arr[mid] < target && target <= arr[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                start++;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 0, 1, 2, 3, 4, 5};
        System.out.println(searchRotatedNoDup(arr, 6));
        int[] a = {6, 6, 6, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5};
        System.out.println(searchRotatedDup(a, 6));

    }
}
