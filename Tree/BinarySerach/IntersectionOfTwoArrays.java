package Tree.BinarySerach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Created by rliu on 1/9/17.
 * 349. Intersection of Two Arrays
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
    }

    //in this way, time complexity is O(nlogn), however better solution is using hashset
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] shorter;
        int[] longer;
        if (nums1.length < nums2.length) {
            shorter = nums1;
            longer = nums2;
        } else {
            shorter = nums2;
            longer = nums1;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < shorter.length; i++) {
            if (i > 0 && shorter[i] == shorter[i - 1])
                continue;
            int left = 0;
            int right = longer.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (longer[mid] == shorter[i]) {
                    list.add(longer[mid]);
                    break; //if you do not return here, remember add break, and it is loop with in while, will never throw exception
                } else if (longer[mid] < shorter[i])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        int[] re = new int[list.size()];
        IntStream.range(0, list.size()).forEach(i -> re[i] = list.get(i));
        return re;
    }

    public static int[] hashSolution(int[] nums1, int[] nums2) {
        int[] shorter;
        int[] longer;
        if (nums1.length < nums2.length) {
            shorter = nums1;
            longer = nums2;
        } else {
            shorter = nums2;
            longer = nums1;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < longer.length; i++)
            set.add(longer[i]);
        HashSet<Integer> list = new HashSet<>();
        for (int i = 0; i < shorter.length; i++) {
            if (i > 0 && shorter[i] == shorter[i - 1])
                continue;
            if (set.contains(shorter[i]))
                list.add(shorter[i]);
        }
        int[] re = new int[list.size()];
        Iterator ite = list.iterator();
        for (int i = 0; ite.hasNext(); i++) {
            re[i] = (int) ite.next();
        }
        return re;
    }
}
