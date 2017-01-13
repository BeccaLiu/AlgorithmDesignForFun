package Linear.QueuePointer;

import java.util.Arrays;

/**
 * Created by rliu on 11/12/16.
 * Sort Color
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

    //TODO: Java bitwise operation
    //[<<]: add 0 to the rightmost digit  5<<2  0101->0100
    //[>>]: add 0 to the leftmost digit  5>>2  0101->0001
    //[>>>]: no sign , add sign to the leftmost digit
    //       {5>>3==5>>>3,   0000 0000 0000 0000 0000 0000 0000 0101 ->  000 0000 0000 0000 0000 0000 0000 0000 0 5>>>3 = 0
    //         but -5>>3     1111 1111 1111 1111 1111 1111 1111 1011 ->  000 1111 1111 1111 1111 1111 1111 1111 1 -5>>3 = -1 high-order bit 1 represent negative
    //              5>>>3    1111 1111 1111 1111 1111 1111 1111 1011 ->  000 1111 1111 1111 1111 1111 1111 1111 1 -5>>>3 = 536870911
    //[&] 0&0||0&1=0, 1&1=1;
    //[|] 0|0=0, 1|0||1|1=1;
    //[^] 0^1=1  0^0||1^1=0;
    //[~] ~0=1 ~1=0;
    public static void xorSwap(int[] arr, int a, int b) {
        if (arr[a] != arr[b]) {
            arr[a] ^= arr[b];  //store the diff between the ori arr[a] and arr[b] at arr[a];  arr[a]=diff arr[b]=b
            arr[b] ^= arr[a]; //apply the diff to b will make it to a : arr[a]=diff arr[b]=a
            arr[a] ^= arr[b]; //apply the diff to a will make it to b : arr[a]=b    arr[b]=a
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 1, 0, 2, 0, 1, 0, 2, 1};
        sortColor(arr);
        System.out.println(Arrays.toString(arr));
    }
}
