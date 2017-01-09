package Tree.BinarySerach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * Created by rliu on 1/9/17.
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * Ans:using binary search directly
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArrays2 {
    public static void main(String[] args) {
        System.out.println(intersectFaster(new int[]{1, 2, 2, 1}, new int[]{1, 2, 2, 2, 3}));
    }

    //although the time complexity is O(n) but frequently modified the hashset is inefficient
    public static int[] intersect(int[] nums1, int[] nums2) {
        int[] shorter;
        int[] longer;
        if (nums1.length < nums2.length) {
            shorter = nums1;
            longer = nums2;
        } else {
            shorter = nums2;
            longer = nums1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < longer.length; i++)
            if (map.containsKey(longer[i]))
                map.put(longer[i], map.get(longer[i]) + 1);
            else
                map.put(longer[i], 1);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < shorter.length; i++) {
            if (map.containsKey(shorter[i]) && map.get(shorter[i]) > 0) {
                list.add(shorter[i]);
                map.put(shorter[i], map.get(shorter[i]) - 1);
            }
        }
        int[] re = new int[list.size()];
        IntStream.range(0, list.size()).forEach(i -> re[i] = list.get(i));
        return re;
    }

    //TODO: why this way is faster by using sort which is nlogn it take 4ms, however, previous hashmap solution take 10 ms?
    public static int[] intersectFaster(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int pointer1 = 0;
        int pointer2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (pointer1 < nums1.length && pointer2 < nums2.length) {
            if (nums1[pointer1] == nums2[pointer2]) {
                list.add(nums1[pointer1]);
                pointer1++;
                pointer2++;
            } else if (nums1[pointer1] > nums2[pointer2])
                pointer2++;
            else
                pointer1++;
        }
        int[] re = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            re[i] = list.get(i);
        //IntStream.range(0, list.size()).parallel().forEach(i -> re[i] = list.get(i));
        //using instream acutally slow down the process, even using parallel do not speed up a little bit
        //in leetcode, it is around 88ms, however the traditional list to arr code template is 4ms
        return re;
    }
}
