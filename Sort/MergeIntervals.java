package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rliu on 1/15/17.
 * 56. Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(8, 10));
        list.add(new Interval(7, 11));
        System.out.println(merge(list));
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        List<Interval> rt = new ArrayList<Interval>();
        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= curr.end) {
                curr.end = Math.max(curr.end, intervals.get(i).end);
            } else {
                rt.add(curr);
                curr = intervals.get(i);
            }
            if (i == intervals.size() - 1)
                rt.add(curr);
        }
        return rt;

    }

}
