package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 1/16/17.
 * 239. Sliding Window Maximum
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 */
public class SlidingWindowMaxium {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.print(Arrays.toString(maxSlidingWindow(nums, 3)));

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] rt = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.add(nums[i]);
                if (i + 1 == k)
                    rt[0] = pq.peek();
            } else {
                pq.remove(nums[i - k]);
                pq.add(nums[i]);
                rt[i - k + 1] = pq.peek();
            }
        }
        return rt;

    }
}
