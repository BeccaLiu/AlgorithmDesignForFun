package Sort;

import java.util.Arrays;

/**
 * Created by rliu on 5/3/17.
 */
public class CountInversion {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 1, 2};
        //System.out.println(sort(new int[]{1, 1, 2, 2, 3}, 0, 4));
        System.out.println(sort(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));

    }

    public static long sort(int[] arr, int l, int r) {
        if (l >= r)
            return 0;
        int mid = l + (r - l) / 2;
        long countL = sort(arr, l, mid);
        long countR = sort(arr, mid + 1, r);
        return countL + countR + merge(arr, l, r);
    }

    //set inversion count to long, just in case it is out of range
    public static long merge(int[] arr, int leftStart, int rightEnd) {
        int leftEnd = leftStart + (rightEnd - leftStart) / 2;
        int rightStart = leftEnd + 1;
        int[] temp = Arrays.copyOfRange(arr, leftStart, rightStart);
        int left = 0;
        int right = rightStart;
        long invCnt = 0;
        while (left <= temp.length - 1 && right <= rightEnd) {
            if (temp[left] > arr[right]) {
                invCnt += temp.length - left;
                arr[leftStart++] = arr[right++];
            } else {
                arr[leftStart++] = temp[left++];
            }
        }
        while (left <= temp.length - 1) {
            arr[leftStart++] = temp[left++];
        }
        return invCnt;
    }

}
