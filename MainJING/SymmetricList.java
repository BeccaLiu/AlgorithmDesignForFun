package MainJING;

import java.util.*;

/**
 * Created by rliu on 2/11/17.
 * give you list of points, check if they are symmetric by x = number
 * point contains (x,y)
 * 356. Line Reflection
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
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
        System.out.println(isReflected());
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
                    return o1.x - o2.x;
                else
                    return o1.x <= xLine ? (o1.y - o2.y) : (o2.y - o1.y);
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
        int xMin = points.get(0).x;
        int xMax = points.get(0).x;
        for (int i = 1; i < points.size(); i++) {
            int currX = points.get(i).x;
            if (currX < xMin)
                xMin = currX;
            if (currX > xMax)
                xMax = currX;
        }
        int xLine = (xMax + xMin) / 2;

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
        int xMin = points.get(0).x;
        int xMax = points.get(0).x;
        for (int i = 1; i < points.size(); i++) {
            Point curPoint = points.get(i);
            int currX = curPoint.x;
            xMin = Math.min(xMin, currX);
            xMax = Math.max(xMax, currX);
            Integer number = hashmap.get(currX);
            if (number == null) {
                hashmap.put(curPoint, 1);
            } else {
                hashmap.put(curPoint, number + 1);
            }
        }
        int xLine = (xMax + xMin) / 2;

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

    public static boolean isReflected() {
        //int[][] points = new int[][]{{1, 1}, {2, 1}, {2, 2}, {3, 3}, {3, 3}, {4, 0}, {4, 1}, {5, 3}, {5, 3}, {6, 2}, {6, 1}, {7, 1}};
        int[][] points = {{16, 1}, {-16, 1}, {-16, 1}}; //will return true
        if (points == null || points.length == 0) return true;
        HashMap<Point, Integer> hashmap = new HashMap<>();
        int xMin = points[0][0];
        int xMax = points[0][0];
        for (int i = 0; i < points.length; i++) {
            xMin = Math.min(xMin, points[i][0]);
            xMax = Math.max(xMax, points[i][0]);
            Point p = new Point(points[i][0], points[i][1]);
            Integer count = hashmap.get(p);
            if (count == null)
                hashmap.put(p, 1);
        }
        double xLine = (double) (xMax + xMin) / 2;

        for (Map.Entry<Point, Integer> cur : hashmap.entrySet()) {
            int count = cur.getValue();
            Point point = cur.getKey();
            Integer symmetricCount = hashmap.get(new Point((int) (xLine * 2 - point.x), point.y));
            if (symmetricCount == null || symmetricCount != count)
                return false;
        }

        return true;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object curr) {
            Point p = (Point) curr;
            return p.x == this.x && p.y == this.y;
        }

        public int hashCode() {
            return (x * y);
        }
    }
}
