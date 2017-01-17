package Heap;

import java.util.PriorityQueue;

/**
 * Created by rliu on 1/16/17.
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Analysis: we can using the idea of quick sort, which is faster and can be done in place and sort the array partly
 * or we can using priority queue, and time complexity is (k+nlogk)
 */
public class KthLargestElementInArr {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 4, 5, 6, 1}, 6));
        System.out.println(findKthLargestAndSort(new int[]{3, 2, 4, 5, 6, 1}, 6));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(2);

        for (int i = 0; i < nums.length; i++) {
            if (k > 0) {
                k--;
                pq.offer(nums[i]);
            } else if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }


    //quick sort solution
    public static int findKthLargestAndSort(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, k - 1);
        return nums[k - 1];
    }

    public static void quickSort(int[] nums, int start, int end, int k) {
        if (start >= end)
            return;
        int pivot = partition(nums, start, end);
        if (pivot == k)
            return;
        else if (pivot > k)
            quickSort(nums, start, pivot - 1, k);
        else
            quickSort(nums, pivot + 1, end, k - pivot);
    }

    public static int partition(int[] nums, int start, int end) {
        int pivot = start + (end - start) / 2;
        swap(nums, start, pivot);

        int l = start + 1;
        int r = end;
        while (true) {
            while (l <= r && nums[l] >= nums[start])
                l++;
            while (l <= r && nums[r] < nums[start])
                r--;
            if (r < l)
                break;
            swap(nums, l, r);
        }
        swap(nums, start, r);
        return r;

    }

    public static void swap(int[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }
}
