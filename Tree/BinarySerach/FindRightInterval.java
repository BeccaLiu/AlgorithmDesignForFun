package Tree.BinarySerach;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by rliu on 4/6/17.
 * 436. Find Right Interval
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 * <p>
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
 * <p>
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 */
public class FindRightInterval {
    public static void main(String[] args) {
        Interval[] itv = new Interval[]{new Interval(1, 4), new Interval(2, 3), new Interval(3, 4)};
        findRightInterval(itv);
    }

    public static int[] findRightInterval(Interval[] intervals) {
        int[] rt = new int[intervals.length];
        HashMap<Integer, Integer> startAndIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < intervals.length; i++)
            startAndIndexMap.put(intervals[i].start, i);

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            int idx = startAndIndexMap.get(intervals[i].start);
            int end = intervals[i].end;
            int l = i + 1;
            int r = intervals.length - 1;
            int mid = l;
            while (l <= r) {
                mid = l + (r - l) / 2;
                if (intervals[mid].start == end)
                    break;
                else if (intervals[mid].start < end)
                    l = mid + 1; //l pointer is always point to the first element larger than mid
                else
                    r = mid - 1;
            }
            if (mid < intervals.length && intervals[mid].start == end) { //find mid as the result
                rt[idx] = startAndIndexMap.get(end);
            } else if (l >= 0 && l < intervals.length) //can not find target end but find the next number larger than end
                rt[idx] = startAndIndexMap.get(intervals[l].start);
            else
                rt[idx] = -1; //can not find anything within range
        }

        return rt;
    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
