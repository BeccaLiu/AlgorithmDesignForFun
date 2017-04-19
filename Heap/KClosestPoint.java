package Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by rliu on 4/18/17.
 */
public class KClosestPoint {
    public static void main(String[] args) {

    }

    public static List<Point> closestPoint(List<Point> points, Point center, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double disDif = getDistance(o1, center) - getDistance(o2, center);
                if (disDif == 0)
                    return 0;
                else if (disDif > 0)
                    return 1;
                else
                    return -1;
            }
        });

        for (Point p : points)
            pq.offer(p);

        List<Point> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }

    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
