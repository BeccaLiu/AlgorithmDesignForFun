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
        System.out.println(canAttendMeetingsFaster(intervals));
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

    public static boolean canAttendMeetingsFaster(Interval[] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int endP = 0;
        int room = 0;
        //for every end, there can be only one start, which means no overlapping, aka [0,10][5,15] for end 10 there are 0, 5 smaller than 10 which means there is a overlapping
        for (int i = 0; i < start.length; i++) {
            if (end[endP] > start[i])
                room++;
            else
                endP++;
            if (room > 1)
                return false;
        }
        return true;
    }

}
