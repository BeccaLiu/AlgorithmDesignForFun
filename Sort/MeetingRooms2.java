package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 1/15/17.
 * 253.Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */
public class MeetingRooms2 {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(0, 30), new Interval(5, 10), new Interval(15, 20), new Interval(17, 25)};
        System.out.println(minMeetingRoomsFastest(intervals));
    }

    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= pq.peek())
                pq.poll();
            pq.add(intervals[i].end);

        }
        return pq.size();
    }

    public static int minMeetingRoomsFastest(Interval[] intervals) {
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
            else//if end larger than start like[1,20][30,35]
                endP++;
        }
        return room;
    }

    //using the idea of bucket
    //O(3n) solution
    public int minMeetingRoomsBucket(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        int min = 0;
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            min = Math.min(min, intervals[i].start);
            max = Math.max(max, intervals[i].end);
        }

        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < intervals.length; i++) {
            bucket[intervals[i].start - min]++;
            bucket[intervals[i].end - min + 1]--;
        }

        int rt = 0;
        int room = 0;
        //as we do not know the bucket size, so it can be inefficient
        for (int i = 0; i < bucket.length; i++) {
            room += bucket[i];
            if (room > rt)
                rt = room;
        }
        return rt;

    }
}
