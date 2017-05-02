package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
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

    //can not process large input as remove from heap take O(n) take time
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

    //the max is always at the head of queue
    public static int[] maxSlidingDeque(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        LinkedList<Integer> deque = new LinkedList<Integer>();
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            // 每当新数进来时，如果发现队列头部的数的下标，是窗口最左边数的下标，则扔掉
            if (!deque.isEmpty() && deque.peekFirst() == i - k) deque.poll();
            // 把队列尾部所有比新数小的都扔掉，保证队列是降序的
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.removeLast();
            // 加入新数
            deque.offerLast(i);
            // 队列头部就是该窗口内第一大的
            if ((i + 1) >= k) res[i + 1 - k] = nums[deque.peek()];
        }
        return res;

    }
}
