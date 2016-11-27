package Graph;

import java.util.PriorityQueue;

/**
 * Created by rliu on 11/26/16.
 * kth largest element in an array
 * find the kth largest element in an unsorted array, note it is not the kth distinct largest element, array contains duplicate
 * Primitive Idea: QuickSort or MergeSort, Time Complexity: O(nlogn)
 * Key: using heap: the faster way to get the max and min
 */
public class KLargestInArray {
    public static void main(String[] args) {
        int[] a = {-2, 0, 1, 0, 2, 3, -3, -2, 1};
        System.out.println(largestHeap(a, 9));
        System.out.println(quickSelect(a, 9));

    }

    //TODO:Time: O(logk!+(n-k)logk)
    //Space Complexity: O(k) for heap
    public static int largestHeap(int[] arr, int k) {
        if (k < 0 || k > arr.length)
            throw new IllegalStateException();
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int i = 0; i < arr.length; i++) {
            if (heap.size() < k)
                heap.add(arr[i]);
            else if (arr[i] > heap.peek()) {
                heap.poll();
                heap.add(arr[i]);
            }
        }
        return heap.peek();
    }

    //can we not use heap? -> quick select -> as in quick sort, the pivot number will be at its fixed place in array, if k> pivot index, sort right side
    //time
    //Space:O(1)
    //as there are duplicate, so we using 3 way quick sort
    public static int quickSelect(int[] arr, int k) {
        if (k < 0 || k > arr.length)
            throw new IllegalStateException();

        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int pivot = arr[start];
            int i = start; //[start,i-1] > pivot
            int j = end; //[j+1,end] <pivot
            int pos = start + 1; //[i,pos-1] =pivot

            while (pos <= j) {
                if (arr[pos] == pivot)
                    pos++;
                else if (arr[pos] > pivot)
                    swap(arr, pos, i++);
                else
                    swap(arr, pos, j--);
            }
            if (k > i && k - 1 <= j)
                return arr[j];
            else if (k <= i)
                end = i - 1;
            else
                start = j + 1;
        }
        return Integer.MIN_VALUE;
    }

    public static void swap(int[] a, int i, int j) {
        if (a[i] == a[j])
            return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
