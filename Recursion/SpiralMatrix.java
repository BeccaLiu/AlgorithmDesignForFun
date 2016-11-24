package Recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rliu on 11/23/16.
 * Spiral Matrix I II
 * Time complexity: O(n*n)
 * Space O(1)
 * stack space O(n/2)
 * there are three base cases: 1. one column left 2. one row left 3. nothing left
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] arr = new int[n][m];
        generateMatrix(arr, 0, m - 1, 0, n - 1, 1);
        Arrays.toString(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%3d", arr[i][j]);
            System.out.println();
        }

        System.out.print(printMatrix(new ArrayList<>(), arr, 0));
    }

    public static void generateMatrix(int[][] arr, int left, int right, int up, int down, int val) {
        if (left > right || up > down) {
            //  arr[left][up] = val;
            return;
        }
        //left == right, copy the whole
        else if (left == right) {
            for (int i = up; i <= down; i++)
                arr[i][right] = val++;
            return;
        } else if (up == down) {
            for (int i = left; i <= right; i++)
                arr[up][i] = val++;
            return;
        }

        for (int i = left; i < right; i++)
            arr[up][i] = val++;
        for (int i = up; i < down; i++)
            arr[i][right] = val++;
        for (int i = right; i > left; i--)
            arr[down][i] = val++;
        for (int i = down; i > up; i--)
            arr[i][left] = val++;
        generateMatrix(arr, left + 1, right - 1, up + 1, down - 1, val);
    }

    public static ArrayList<Integer> printMatrix(ArrayList<Integer> res, int[][] arr, int offset) {

        if (offset > arr.length - 1 - offset || offset > arr[0].length - 1 - offset) {
            return res;
        } else if (offset == arr[0].length - offset - 1) {
            for (int i = offset; i < arr.length - offset; i++)
                res.add(arr[i][offset]);
            return res;
        } else if (offset == arr.length - offset - 1) {
            for (int i = offset; i < arr[0].length - offset; i++)
                res.add(arr[offset][i]);
            return res;
        }


        int width = arr[0].length - 1;
        int height = arr.length - 1;
        for (int i = offset; i < width - offset; i++)
            res.add(arr[offset][i]);
        for (int i = offset; i < height - offset; i++)
            res.add(arr[i][width - offset]);
        for (int i = width - offset; i > offset; i--)
            res.add(arr[height - offset][i]);
        for (int i = height - offset; i > offset; i--)
            res.add(arr[i][offset]);

        return printMatrix(res, arr, offset + 1);
    }
}
