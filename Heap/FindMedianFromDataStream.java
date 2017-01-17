package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 1/16/17.
 */
public class FindMedianFromDataStream {
    PriorityQueue<Integer> sm; //always contains the smaller half using max heap
    PriorityQueue<Integer> lg; //always contains the larger half using min heap

    FindMedianFromDataStream() {
        lg = new PriorityQueue<Integer>();
        sm = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public static void main(String[] args) {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        System.out.println();
        FindMedianFromDataStream mf2 = new FindMedianFromDataStream();
        mf2.addNum(-3);
        System.out.println(mf2.findMedian());
        mf2.addNum(-2);
        System.out.println(mf2.findMedian());
        mf2.addNum(-1);
        System.out.println(mf2.findMedian());
        mf2.addNum(0);
        System.out.println(mf2.findMedian());
        mf2.addNum(1);
        System.out.println(mf2.findMedian());
        mf2.addNum(2);
        System.out.println(mf2.findMedian());

    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        //always maintain sm.size<=lg.size
        lg.offer(num);
        while (sm.size() + 2 <= lg.size()) {
            sm.offer(lg.poll()); //by doing this, dump the relatively min part of lg queue, to sm queue
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (lg.size() == sm.size())
            return (double) (lg.peek() + sm.peek()) / 2;
        else//as lg is always larger in size than sm, so we just peek the lg one.
            return lg.peek();
    }
}
