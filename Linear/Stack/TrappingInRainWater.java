package Linear.Stack;

import java.util.ArrayDeque;

/**
 * Created by rliu on 11/12/16.
 * Trapping rain water
 * Given n non-negative integers to represent the histogram bar height, the width of each bar is 1
 * Calculate how much water it can trap after raining
 */
public class TrappingInRainWater {
    //using two pointer
    public static int primitiveIdea(int[] a) {
        int water = 0;
        for (int curr = 0; curr < a.length; curr++) {
            int i = curr;
            int left = curr;
            int right = curr;
            while (i >= 0)
                if (a[i--] > a[left])
                    left = i + 1;
            i = curr;
            while (i < a.length)
                if (a[i++] > a[right])
                    right = i - 1;
            System.out.println(left + "," + right + ":" + (Math.min(a[left], a[right]) - a[curr]));
            water += Math.min(a[left], a[right]) - a[curr];
        }
        return water;
    }

    //scan from beginning, and until meet the same height or height than the beginning, we know that we can calculate
    //still two boundary, one is left wall, we store it in stack, and one is the right wall, we using curr pointer
    //when the curr is less than stack.peek, we push it into stack
    //when the curr is more than stack.peek, we know we can calculate now
    public static int stackIdea(int[] a) {
        int water = 0;
        ArrayDeque<Integer> leftBounds = new ArrayDeque<>();
        for (int curr = 0; curr < a.length; curr++) {
            while (!leftBounds.isEmpty() && a[leftBounds.peek()] < a[curr]) {
                int c = leftBounds.pop();
                if (!leftBounds.isEmpty()) {
                    int j = leftBounds.peek();
                    water += (curr - j - 1) * (Math.min(a[j], a[curr]) - a[c]);
                }
            }
            leftBounds.push(curr);
        }
        return water;
    }

    public static void main(String[] args) {
        int[] b = {3, 1, 2, 3, 1, 1, 2, 1};
        System.out.print(stackIdea(b));

    }
}
