package Linear.QueuePointer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by rliu on 11/11/16.
 * Moving average from data stream
 * Given an integer stream and a window size
 * calculate the moving average of all integers in the sliding window
 * movingAverage(size): initial the data structure with size
 * next(val): calculate the next average value after adding val
 */
public class MovingAverage {
    Queue<Integer> queue;
    int total;
    int windowSize;

    public MovingAverage(int size) {
        queue = new ArrayDeque<>();
        total = 0;
        windowSize = size;
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(3));
        System.out.println(ma.next(1));
        System.out.println(ma.next(2));
        System.out.println(ma.next(1));
        System.out.println(ma.next(3));
        System.out.println(ma.next(8));
    }

    public float next(int val) {
        if (queue.size() == windowSize) {
            total -= queue.remove();
            queue.offer(val);
            total += val;
            return (float) total / queue.size();
        } else {
            queue.offer(val);
            total += val;
            return (float) total / queue.size();
        }
    }
}
