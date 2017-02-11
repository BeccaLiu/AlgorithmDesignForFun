package MainJING;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        points.add(new Point(4, 2));
        points.add(new Point(4, 1));
        points.add(new Point(4, 0));
        points.add(new Point(5, 3));
        points.add(new Point(6, 2));
        points.add(new Point(6, 1));
        points.add(new Point(7, 1));
        System.out.println(isSymmetric(points)); //true
    }

    public static boolean isSymmetric(List<Point> points) {
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
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x != o2.x)
                    return o1.x - o2.x;
                else
                    return o1.x <= xLine ? o1.y - o2.y : o2.y - o1.y;
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

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
