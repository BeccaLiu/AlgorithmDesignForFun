package Linear;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/12/16.
 * Largest Rectangle in Histogram
 * Given n non-negative integers to represent the histogram bar height, the width of each bar is 1
 * calculate the area of the largest rectangle in the histogram
 * Key: reduce duplicate work
 * duplicate work: from left scan to right, we already know what's smaller than current in left
 * TODO:O(N) solution
 */
public class LargestRectangleHistogram {
    /* Brute force: 1. using two pointer, find the boundary of two pointer
    *              2. start from the mid
    *              O(N^2)
    */
    public static void primitiveIdea(int[] a) {
        int max = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < a.length; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && a[left] >= a[i])
                left--;
            while (right < a.length && a[right] >= a[i])
                right++;
            if ((right - left - 1) * a[i] > max) {
                max = (right - left - 1) * a[i];
                start = left + 1;
                end = right - 1;
            }
        }
        System.out.println(start + "/" + end + ":" + max);
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random random = new Random();
        IntStream.range(0, 10).parallel().forEach(i -> a[i] = random.nextInt(10) + 1);
        System.out.println(Arrays.toString(a));
        int[] b = {5, 3, 4, 6, 7, 3};
        primitiveIdea(a);
        primitiveIdea(b);
    }
}
