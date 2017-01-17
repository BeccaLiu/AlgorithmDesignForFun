package Sort;

import java.util.Arrays;

import static Sort.WiggleSort.swap;


/**
 * Created by rliu on 1/15/17.
 * 324. Wiggle Sort II
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * nums[even]<nums[odd]
 * 1. initial thinking is nlogn sort the nums and swap. but not using greedy strategy, but using additional array
 * ex 4 5 5 6 -> 4 5  + 5 6  ->  using l and r pointer copy from right side and 5 6 4 5
 * l      r
 */
public class WiggleSort2 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 1, 1, 6, 4};
        int[] b = new int[]{1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2};
        int[] c = new int[]{4, 5, 5, 6};
        int[] d = new int[]{3, 3, 3, 2, 2, 2, 3, 2, 1, 1, 2, 1, 2, 3, 3, 3, 1, 2};
        wiggleSortInit(a);
        wiggleSortInit(b);
        wiggleSortInit(c);
        wiggleSortInit(d);
    }

    public static void wiggleSortInit(int[] nums) {
        Arrays.sort(nums);
        int[] rt = Arrays.copyOf(nums, nums.length);
        //always keep left part size>=right part side aka: 4 5 5 6 6 split as 4 5 5, 6 6 but not 4 5 , 5 5 6
        int left = (nums.length - 1) / 2;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0)
                nums[i] = rt[left--];
            else
                nums[i] = rt[right--];
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void wiggleSort(int[] nums) {
        int k = nums.length - 1;
        while (nums[0] > nums[k]) {
            swap(nums, 0, k--);
        }
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0 && nums[i - 1] <= nums[i]) {
                if (nums[i - 1] == nums[i]) {
                    int j = nums.length - 1;
                    while (nums[i - 1] <= nums[i]) {
                        swap(nums, i, j--);
                    }
                } else
                    swap(nums, i - 1, i);
            }
            if (i % 2 == 1 && nums[i - 1] >= nums[i]) {
                if (nums[i - 1] == nums[i]) {
                    int j = nums.length - 1;
                    while (nums[i - 1] >= nums[i]) {
                        swap(nums, i, j--);
                    }
                } else
                    swap(nums, i - 1, i);
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
