package Linear.QueuePointer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Created by rliu on 5/4/17.
 * In this problem, you are given  integers. You need to find the maximum number of unique integers among all the possible contiguous subarrays of size .
 */
public class DataStreamMaximumUnique {
    public static void main(String[] args) {
        getUniqueMax(new int[]{5, 3, 5, 2, 3, 2});
    }

    public static void getUniqueMax(int[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            int num = args[i];
            deque.offer(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (deque.size() > 3) {
                Integer pollOut = deque.pollFirst();
                Integer count = map.get(pollOut);
                if (count <= 1) {
                    map.remove(pollOut);
                } else
                    map.put(pollOut, count - 1);
            }
            max = Math.max(map.size(), max);
        }
        System.out.println(max);
    }
}
