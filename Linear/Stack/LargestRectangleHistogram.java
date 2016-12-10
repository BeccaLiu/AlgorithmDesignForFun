package Linear.Stack;

import java.util.ArrayDeque;
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
    /* Brute force:  1. size is defined by width and height
     *               2. here we iterate all different height,
     *               3. restrict the same height, the using two pointer, find the boundary of two pointer of two width
     *               4. each time, the size (right - left - 1) * height we get will be the largest rectangle contains a[i]
     *               O(N^2)
     */
    public static void primitiveIdea(int[] a) {
        if (a == null || a.length == 0)
            return;
        int max = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < a.length; i++) {
            int left = i;
            int right = i;
            int height = a[i];
            while (left >= 0 && a[left] >= a[i])
                left--;
            while (right < a.length && a[right] >= a[i])
                right++;
            if ((right - left - 1) * height > max) {
                max = (right - left - 1) * height;
                start = left + 1;
                end = right - 1;
            }
        }
        System.out.println(start + "/" + end + ":" + max);
    }

    /* for a[] like 3,1,2,4,5,1
     * using the primitiveIdea, at height a[2]=2, we need two pointer, for the left pointer, we are actually scan and read some previous info
     * -> scan from left to right and get pre info -> using stack and keep right pointer
     * store index in stack
     * cur > stack.peek() offer
     * cur <= stack.peek() -> continuously poll
     * so the stack.peek() is worked like left pointer
     * if stack is empty, the left pointer is -1
     */
    public static void stackIdea(int[] a) {
        if (a == null || a.length == 0)
            return;
        int start = -1;
        int end = -1;
        int max = Integer.MIN_VALUE;
        ArrayDeque<Integer> leftBound = new ArrayDeque<>();
        //should be rightBound<=a.length but not rightBound<a.length
        for (int rightBound = 0; rightBound <= a.length; rightBound++) {
            //each element will be push once and pop once
            int rightHeight = rightBound == a.length ? 0 : a[rightBound];
            while (!leftBound.isEmpty() && rightHeight < a[leftBound.peek()]) {
                int fixHeight = a[leftBound.pop()];
                int left = leftBound.isEmpty() ? 0 : leftBound.peek() + 1;
                int size = fixHeight * (rightBound - left);
                if (size > max) {
                    max = size;
                    start = left;
                    end = rightBound - 1;
                }
            }
            leftBound.push(rightBound);
        }
        System.out.println(start + "/" + end + ":" + max);
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random random = new Random();
        IntStream.range(0, 10).parallel().forEach(i -> a[i] = random.nextInt(10) + 1);
        System.out.println(Arrays.toString(a));
        int[] b = {2, 4, 4, 6, 6, 2, 9, 2, 8, 11};
        a[9] = 11;
        primitiveIdea(a);
        //primitiveIdea(b);
        stackIdea(a);
        //stackIdea(b);
    }
}
