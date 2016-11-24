package Recursion;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/23/16.
 * Merge Sort
 * 1.binary deduction(二分法)
 * 2.merge from the smallest subproblem
 * 3.local order->global order   vs QuickSort(global order-> local order)
 * Time Complexity: O(nlogn)
 * Space Complexity O(n) extra array to store the info
 * every node will be visited logn time.
 */
public class MergerSort {
    public static void main(String[] args) {
        int size = 25;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> arr[i] = random.nextInt(40));
        sort(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(arr));

    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] copy = new int[mid - start + 1];
        System.arraycopy(arr, start, copy, 0, mid - start + 1);
        int acur = 0;
        int bcur = mid + 1;
        for (int i = start; i <= end; i++) {
            if (acur < copy.length && bcur <= end) {
                if (arr[bcur] <= copy[acur])
                    arr[i] = arr[bcur++];
                else
                    arr[i] = copy[acur++];
            } else if (bcur > end)
                arr[i] = copy[acur++];
        }
    }

    public static void sort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int mid = start + (end - start) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }
}
