package Sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rliu on 1/15/17.
 * 252. meeting rooms
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 */
public class MeetingRooms {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(0, 30), new Interval(5, 10), new Interval(15, 20), new Interval(35, 60)};
        System.out.println(canAttendMeetings(intervals));
    }

    public static boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return true
                    ;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start <= intervals[i - 1].end)
                return false;
        }
        return true;
    }
}
