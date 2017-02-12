package MainJING;

import java.util.*;

/**
 * Created by rliu on 2/11/17.
 * give you list of points, check if they are symmetric by x = number
 * point contains (x,y)
 */
public class SymmetricList {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(3, 3));
        points.add(new Point(4, 2));
        points.add(new Point(4, 1));
        points.add(new Point(4, 0));
        points.add(new Point(5, 3));
        points.add(new Point(5, 3));
        points.add(new Point(6, 2));
        points.add(new Point(6, 1));
        points.add(new Point(7, 1));
        System.out.println(isSymmetricHash(points)); //true
    }

    public static boolean isSymmetric(List<Point> points) {
        if (points == null || points.size() == 0)
            return false;
        double xMin = points.get(0).x;
        double xMax = points.get(0).x;
        for (int i = 1; i < points.size(); i++) {
            double currX = points.get(i).x;
            if (currX < xMin)
                xMin = currX;
            if (currX > xMax)
                xMax = currX;
        }
        double xLine = (xMax + xMin) / 2;
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x != o2.x)
                    return (int) (o1.x - o2.x);
                else
                    return (int) (o1.x <= xLine ? (o1.y - o2.y) : (o2.y - o1.y));
            }
        });

        int start = 0;
        int end = points.size() - 1;
        while (start < end) {
            if (xLine - points.get(start).x != points.get(end).x - xLine || xLine != points.get(start).x && points.get(start).y != points.get(end).y)
                return false;
            start++;
            end--;
        }
        return true;
    }

    public static boolean isSymmetricHash(List<Point> points) {
        if (points == null || points.size() == 0)
            return false;
        double xMin = points.get(0).x;
        double xMax = points.get(0).x;
        for (int i = 1; i < points.size(); i++) {
            double currX = points.get(i).x;
            if (currX < xMin)
                xMin = currX;
            if (currX > xMax)
                xMax = currX;
        }
        double xLine = (xMax + xMin) / 2;

        HashMap<Point, Integer> map = new HashMap<>(); //key is point, value is count
        for (int i = 0; i < points.size(); i++) {
            Point curr = points.get(i);
            if (curr.x == xLine)
                continue;
            Integer count = map.get(curr);
            Point temp = new Point(2 * xLine - curr.x, curr.y);
            if (count == null) {
                Integer count2 = map.get(temp);
                if (count2 == null)
                    map.put(temp, 1);
                else
                    map.put(temp, count2 + 1);
            } else {
                if (count == 1)
                    map.remove(curr);
                else
                    map.put(curr, count - 1);
            }
        }
        return map.size() == 0;
    }

    public static boolean isSymmetricHashBetter(List<Point> points) {
        if (points == null || points.size() == 0) return false;
        HashMap<Point, Integer> hashmap = new HashMap<>(); //key is point, value is count
        double xMin = points.get(0).x;
        double xMax = points.get(0).x;
        for (int i = 1; i < points.size(); i++) {
            Point curPoint = points.get(i);
            double currX = curPoint.x;
            xMin = Math.min(xMin, currX);
            xMax = Math.max(xMax, currX);
            Integer number = hashmap.get(currX);
            if (number == null) {
                hashmap.put(curPoint, 1);
            } else {
                hashmap.put(curPoint, number + 1);
            }
        }
        double xLine = (xMax + xMin) / 2;

        for (Point p : hashmap.keySet()) {
            Point symmetriP = new Point(2 * xLine - p.x, p.y);
            if (!hashmap.containsKey(symmetriP)) {
                return false;
            } else {
                if (hashmap.get(symmetriP) != hashmap.get(p)) { //
                    return false;
                }
            }
        }
        return true;
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object curr) {
            Point p = (Point) curr;
            return p.x == this.x && p.y == this.y;
        }

        public int hashCode() {
            return (int) (x * y);
        }
    }
}
