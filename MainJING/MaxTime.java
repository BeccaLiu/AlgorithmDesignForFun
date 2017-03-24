package MainJING;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rliu on 3/23/17.
 */
public class MaxTime {
    public static void main(String[] args) {
        System.out.println(maxTime(1, 8, 3, 2));
        System.out.println(maxTime(2, 4, 0, 0));
        System.out.println(maxTime(3, 0, 7, 0));
        System.out.println(maxTime(9, 1, 9, 7));

    }

    public static String maxTime(int A, int B, int C, int D) {
        // write your code in Java SE 8
        int[] input = new int[]{A, B, C, D};
        List<List<Integer>> res = new ArrayList<>();
        permutation(input, new ArrayList<>(), res, new boolean[input.length]);

        if (res.size() == 0)
            return "NOT POSSIBLE";

        Collections.sort(res, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o2, List<Integer> o1) {

                return o1.get(0) != o2.get(0) ? o1.get(0) - o2.get(0) : o1.get(1) != o2.get(1) ? o1.get(1) - o2.get(1) : o1.get(2) != o2.get(2) ? o1.get(2) - o2.get(2) : o1.get(3) - o2.get(3);
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append(res.get(0).get(0));
        sb.append(res.get(0).get(1));
        sb.append(":");
        sb.append(res.get(0).get(2));
        sb.append(res.get(0).get(3));

        return sb.toString();

    }

    public static void permutation(int[] input, List<Integer> list, List<List<Integer>> res, boolean[] isUsed) {
        if (list.size() == input.length) {
            if (isTime(list))
                res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                list.add(input[i]);
                permutation(input, list, res, isUsed);
                list.remove(list.size() - 1);
                isUsed[i] = false;
            }
        }
    }

    public static boolean isTime(List<Integer> list) {
        int t0 = list.get(0);
        int t1 = list.get(1);
        int t2 = list.get(2);
        int t3 = list.get(3);
        if (t0 > 2) return false;
        else if (t0 == 2) {
            if (t1 > 3) return false;
        }
        return t2 <= 5;

    }
}
