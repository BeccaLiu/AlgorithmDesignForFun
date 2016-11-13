package Linear.QueuePointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/10/16.
 * Three Sum with duplicate input and unsorted
 * Obviously brute force method is O(N^3)
 */
public class ThreeSum {
    /*
     * Two pointer
     * Time Complexity: O(N^2)for two pointer, O(NlogN) for sorting
     * Space Complexity: O(N)
     */
    public static boolean threeSum(int[] a, int target) {
        int[] arr = Arrays.copyOf(a, a.length);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int start = i + 1;
            int end = arr.length - 1;
            int reminder = target - arr[i];
            while (start < end) {
                if (arr[start] + arr[end] == reminder)
                    return true;
                else if (arr[start] + arr[end] < reminder)
                    start++;
                else
                    end--;
            }
        }
        return false;
    }

    public static int[] threeSum2(int[] a, int target) {
        if (a.length < 3)
            return null;
        int[] rt = new int[3];
        for (int i = 0; i < a.length; i++) {
            int remind = target - a[i];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < a.length; j++) {
                if (map.containsKey(a[j])) {
                    rt[0] = i;
                    rt[1] = j;
                    rt[2] = map.get(a[j]);
                    return rt;
                } else {
                    map.put(remind - a[j], j);
                }
            }
        }
        return rt;

    }

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> arr[i] = random.nextInt(10));
        System.out.println(Arrays.toString(arr));
        System.out.println(threeSum(arr, 5));
        System.out.println(Arrays.toString(threeSum2(arr, 5)));
    }
}
