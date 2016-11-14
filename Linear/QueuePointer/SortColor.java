package Linear.QueuePointer;

import java.util.Arrays;

/**
 * Created by rliu on 11/12/16.
 * Given an array with only 3 possible values 0,1,2, representing 3 different colors, red, white and blue.
 * sort the array to make the same color are adjacent with the order of red, white, and blue
 * Input: int[] colors      0 1 2 0 2 2 1 1 0 0
 * Corner Case: If input arr is empty or length is 1 just return
 * Key: 3-way quick sort
 * left: the right bound of process 0
 * right: the left bound of process 2
 * curr: unprocessed item
 */
public class SortColor {
    public static void sortColor(int[] colors) {
        int left = 0;
        int right = colors.length - 1;
        int curr = 0;

        while (curr <= right) {
            if (colors[curr] == 0) {
                swap(colors, curr++, left++);
            } else if (colors[curr] == 2) {
                swap(colors, curr, right--);
            } else {
                curr++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //TODO:
    public static void xorSwap(int[] arr, int a, int b) {
        if (arr[a] != arr[b]) {
            arr[a] ^= arr[b];
            arr[b] ^= arr[a];
            arr[a] ^= arr[b];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 1, 0, 2, 0, 1, 0, 2, 1};
        sortColor(arr);
        System.out.println(Arrays.toString(arr));
    }
}
