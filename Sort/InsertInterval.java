package Sort;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/15/17.
 */
public class InsertInterval {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 3));
        list.add(new Interval(4, 7));
//        list.add(new Interval(7, 10));
        list.add(new Interval(8, 11));
        System.out.println(insert(list, new Interval(13, 15)));
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0 || newInterval.start > intervals.get(intervals.size() - 1).end) {
            intervals.add(newInterval);
            return intervals;
        }
        if (newInterval.end < intervals.get(0).start) {
            intervals.add(0, newInterval);
            return intervals;
        }

        List<Interval> rt = new ArrayList<>();
        boolean inserted = false;

        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            //interval is at the left side of current interval
            //new interval is at the right side of current interval
            if (newInterval.end < curr.start || newInterval.start > curr.end) {
                rt.add(curr);
            } else {
                //new interval is intersect with current interval
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
            //first time insert newinterval
            if (!inserted && i != intervals.size() - 1 && newInterval.end < intervals.get(i + 1).start) {
                rt.add(newInterval);
                inserted = !inserted;
            }
        }
        if (!inserted)
            rt.add(newInterval);
        return rt;
    }
}
