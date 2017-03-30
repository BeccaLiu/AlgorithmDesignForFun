package HashMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * Created by rliu on 3/29/17.
 * 149. Max Points on a line
 */
public class MaxPointsOnALine {
    public static void main(String[] args) {

        Point[] points = new Point[]{new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151)};
        maxPoints(points);

    }

    public static int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;

        int max = 2;

        for (int i = 0; i < points.length; i++) {
            HashMap<BigDecimal, Integer> map = new HashMap<>();
            int sameX = 1;
            int sameP = 0;

            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    double yDiff = points[j].y - points[i].y;
                    double xDiff = points[j].x - points[i].x;
                    if (yDiff == 0 && xDiff == 0)
                        sameP++;
                    if (xDiff == 0)
                        sameX++;
                    else {
                        BigDecimal slop = new BigDecimal(yDiff).divide(new BigDecimal(xDiff), 64, RoundingMode.HALF_UP);
                        Integer cnt = map.get(slop);
                        if (cnt == null)
                            map.put(slop, 2);
                        else
                            map.put(slop, cnt + 1);
                    }

                }
            }

            max = Math.max(max, sameX);
            for (Integer cnt : map.values())
                max = Math.max(max, cnt + sameP);
        }


        return max;
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
