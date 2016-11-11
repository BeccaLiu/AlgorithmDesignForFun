package Linear;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/10/16.
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

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> arr[i] = random.nextInt(10));
        System.out.println(Arrays.toString(arr));
        System.out.println(threeSum(arr, 5));

    }
}
