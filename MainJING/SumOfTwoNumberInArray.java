package MainJING;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rliu on 2/22/17.
 */
public class SumOfTwoNumberInArray {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE << 1));
        System.out.println(-10 >>> 1);
        //System.out.println(numberOfPairs(new int[]{6, 6, 3, 9, 3, 5, 1}, 12));
    }

    public static int numberOfPairs(int[] a, long k) {
        if (a == null || a.length == 0)
            return 0;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(a[i]))
                map.put(a[i], new HashSet<>());
            map.get(a[i]).add(i);
        }

        int count = 0;
        for (int anA : a) {
            int red = (int) k - anA;


        }
        return 0;

    }
}
