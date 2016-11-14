package Tree.BinarySerach;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/13/16.
 * Given a sorted array and target value, write an algorithm to find its index
 * if not found find the index where it should be inserted in order
 */
public class FindTarget {
    public static int findOrInsert(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return start;
    }

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).parallel().forEach(i -> arr[i] = random.nextInt(50));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int target = random.nextInt(50);
        System.out.println(target + ":" + findOrInsert(arr, target));
    }

}
