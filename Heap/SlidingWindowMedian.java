package Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 5/1/17 4:18 PM.
 * Sliding Window Median
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        System.out.println(medianSlidingWindow(new int[]{7, 2, 1, 7, 2}, 2));

    }

    public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return ret;
        if (k <= 1) {
            for (int i : nums)
                ret.add(i);
            return ret;
        }

        PriorityQueue<Integer> max = new PriorityQueue<Integer>(k - k / 2, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        }); //>median
        PriorityQueue<Integer> min = new PriorityQueue<Integer>(k / 2); //<=median size k-k/2

        int sizeMax = k - k / 2;
        int sizeMin = k / 2;

        for (int i = 0; i < nums.length; i++) {
            //remove
            if (i - k >= 0) {
                if (nums[i - k] <= max.peek())
                    max.remove(nums[i - k]);
                else
                    min.remove(nums[i - k]);
            }

            //add
            if (max.size() < sizeMax) { //left has space
                if (min.size() != 0 && nums[i] > min.peek()) { //left has space but, I need to add to right
                    max.offer(min.poll());
                    min.offer(nums[i]);
                } else
                    max.offer(nums[i]);
            } else if (min.size() < sizeMin) { //right has space
                if (max.size() != 0 && nums[i] <= max.peek()) { //right has space, but I need to add to left
                    min.offer(max.poll());
                    max.offer(nums[i]);
                } else
                    min.offer(nums[i]);
            }
            //add to result
            if (max.size() + min.size() == k) {
                ret.add(max.peek());
            }
        }
        return ret;
    }
}
