package Linear.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 4/25/17 9:19 PM.
 * Print Numbers by Recursion
 * however this cost a lot of recursion memory as the recursion depth maybe very large. Can you do it in another way to recursive with at most N depth?
 */
public class PrintNumbersRecursion {
    public static void main(String[] args) {
        numbersByRecursion(2);
    }

    public static List<Integer> numbersByRecursion(int n) {
        // write your code here
        if (n == 0)
            return new ArrayList<Integer>();

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }

        recursion(list, n, 1);
        return list;


    }

    public static void recursion(List<Integer> list, int n, int idx) {
        if (idx >= n)
            return;

        int size = list.size();
        int base = (int) Math.pow(10, idx);
        for (int i = 1; i <= 9; i++) {
            int start = base * i;
            list.add(start);
            for (int j = 0; j < size; j++) {
                list.add(start + list.get(j));
            }
        }
        recursion(list, n, idx + 1);
    }
}
