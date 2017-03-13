package Linear.QueuePointer;

import java.util.*;
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

    //duplicates input and sorted array using 3 pointers
    public static void threeSum3(int[] a, int target) {
        if (a.length < 3)
            return;
        int fix = 0;

        while (fix < a.length - 2) {
            int reminder = target - a[fix];
            int left = fix + 1;
            int right = a.length - 1;
            while (left < right) {
                if (a[left] + a[right] == reminder) {
                    int[] temp = new int[3];
                    temp[0] = fix;
                    temp[1] = left++;
                    temp[2] = right--;
                    System.out.println(Arrays.toString(temp));
                    while (left < right && a[left] == a[left - 1])
                        left++;
                    while (left < right && a[right] == a[right + 1])
                        right--;
                } else if (a[left] + a[right] < reminder)
                    left++;
                else
                    right--;

            }
            fix++;
            while (fix < a.length - 2 && a[fix] == a[fix - 1]) {
                fix++;
            }
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return list;
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                Integer index = map.get(nums[j]);
                if (index != null) {
                    List<Integer> temp = Arrays.asList(i, index, j);
                    list.add(temp);
                } else
                    map.put(target - nums[j], j);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        int[] temparr = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(temparr));
        int size = 10;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> arr[i] = random.nextInt(10));
        System.out.println(Arrays.toString(arr));
        System.out.println(threeSum(arr, 5));
        System.out.println(Arrays.toString(threeSum2(arr, 5)));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        threeSum3(arr, 5);

    }
}
