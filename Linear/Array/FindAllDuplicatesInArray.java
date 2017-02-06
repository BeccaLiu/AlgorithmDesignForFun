package Linear.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/5/17.
 * 442. Find All Duplicates in an Array
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 */
public class FindAllDuplicatesInArray {
    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{4, 3, 2, 7, 9, 8, 2, 3, 1, 9}));
    }

    //the key is first some elements appear twice and others appear once + all the number is from 1 to n in size n array
    //means the appear twice number keep the spot of disappear number, so we just moving the number to their destination, and find the difference
    public static List<Integer> findDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                while (nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                list.add(nums[i]);
        }
        return list;
    }

    public static void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}

