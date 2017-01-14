package Sort;

import java.util.Arrays;

/**
 * Created by rliu on 1/12/17.
 */
public class ComparisonSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 4, 8, 2, 7, 3, 0, 1, 5, 9};
        int[] arrSorted = new int[]{0, 1, 2, 3};
        //quickSort(arr, 0, arr.length - 1);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    slower O(n^2) time sort
     */

    //idea: push the larger one to the right side
    //Time: n+n+n...+n+n = n*n O(n^2)
    //space: 1
    //stable: Yes as when arr[j]==arr[j-1] we do nothing
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) { //iterate i times, but within the loop, j has nothing to do with i.
            for (int j = 1; j < arr.length; j++) { //move the biggest little by little to the right side, actually the right part is sorted
                if (arr[j - 1] > arr[j]) // when arr[j]>=arr[j-1] we stop, as we know the right side is already sorted
                    swap(arr, j - 1, j);
            }
        }
    }

    //idea: select the smallest of the right side and swap it with left side i place
    //Time: (n-1)+(n-2)+...+2+1=n*n/2 O(n^2)
    //space: 1
    //stable for primitive type: as we will swap the first smallest i to the front, aka 0...0 , we will move first 0 to the front
    //no stable for object type
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int sm = i; // i is the index need to be swap, here the sm is i not i+1
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[sm])
                    sm = j;
            }
            swap(arr, i, sm);
        }
    }

    //idea: insert to the place where it should be
    //Time: 1+2+...n-3+n-2+n= n*n/2 => O(n^2)
    //Space: O(1)
    //Stable: Yes
    //fast when list is partly sorted
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j])
                    swap(arr, j, j + 1);
            }
        }
    }

    /*
    faster sorting algorithm using the idea of binary deduction
     */

    //idea:1. binary deduction 2.merge from smallest sub problem 3.local order-> global order
    //time:O(nlogn)
    //space: O(n)
    public static void mergeSort(int[] arr, int start, int end) {
        if (start == end)
            return;

        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] rightCopy = new int[right - mid];
        for (int i = mid + 1; i <= right; i++) {
            rightCopy[i - mid - 1] = arr[i];
        }

        int l = mid;
        int r = right - mid - 1;
        int k = right;
        while (l >= left && r >= 0) {
            if (arr[l] > rightCopy[r])
                arr[k--] = arr[l--];
            else
                arr[k--] = rightCopy[r--];
        }

        while (r >= 0) {
            arr[k--] = rightCopy[r--];
        }
    }

    //1.Binary Deduction
    //2.put all smaller elements left and bigger elements right
    //3. global sorted ->  local sorted
    //average O(nlogn)
    //worst O(n^2)
    //not stable
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) //be aware to have this base case;
            return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = left + (right - left) / 2;//left+(int) (Math.random()*(right-left+1));
        swap(arr, left, pivot);
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (arr[l] <= arr[left])
                l++;
            else if (arr[r] > arr[left])
                r--;
            else
                swap(arr, l, r);
        }
        swap(arr, left, r);
        return r;
    }


    //heap is like tree but store the content as array O(nlogn+n)
    //not stable
    //heapify: O(n) time
    //      a        -> this level has 1 node and will sink at most logn time
    //   b      c    -> this level has 2^1 node and will sink at most
    // d   e   f  g
    //.............  ->                a^(-k)n node and will sink at most k time
    //first level will sink logn level, and the second level hash
    //heap sort -> heapify a max heap and swap j with top of heap, sink new peek down
    public static void heapSort(int[] arr) {
        heapify(arr);
        //sort down
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, j, 0);
            sink(arr, 0, j - 1); //set the boundary
        }
    }

    public static void heapify(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            sink(arr, i, arr.length - 1);
        }
    }

    public static void sink(int[] arr, int i, int end) {
        int j = 2 * i + 1;
        while (j <= end || j + 1 <= end) {
            if (j + 1 <= end && arr[j] < arr[j + 1]) j++; //good way to get larger of kids
            if (arr[i] >= arr[j]) break;
            swap(arr, i, j);
            i = j;
            j = 2 * i + 1;
        }
    }

    public static void swim(int[] arr, int j) {
        while ((j + 1) / 2 >= 1 && arr[j] < arr[(j + 1) / 2 - 1]) {
            swap(arr, j, (j + 1) / 2 - 1);
            j = (j + 1) / 2 - 1;

        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }
}
