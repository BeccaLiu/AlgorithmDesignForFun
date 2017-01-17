package Sort;

import java.util.Arrays;

/**
 * Created by rliu on 1/15/17.
 * 280. Wiggle Sort
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {
    public static void main(String[] args) {
        wiggleSort(new int[]{3, 5, 2, 1, 6, 4});
        wiggleSortWithoutSort(new int[]{3, 5, 2, 1, 6, 4});
    }

    //analysis: to meet the requirement, we can know all the nums[i], i%2==0 is the smaller half of the sorted array
    //and nums[j], j%2==1 is the larger half of the sorted array
    //ex: [3, 5, 2, 1, 6, 4] -> [1,2,3,4,5,6] -> answer could be 1,4,2,5,3,6, just intersect 1,2,3 and 4,5,6
    //1,2,3 can in order or without order,as second half aka 4,5,6 is always larger than any number in first half aka 1,2,3
    //[I am stuck here]: actually my solution is to complicated
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            //just swap i and i+1;
            swap(nums, i, i + 1);
        }
        System.out.print(Arrays.toString(nums));
    }

    public static void wiggleSortWithoutSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0 && nums[i] > nums[i + 1])
                swap(nums, i, i + 1);
            if (i % 2 == 1 && nums[i] < nums[i + 1])
                swap(nums, i, i + 1);
        }
        System.out.print(Arrays.toString(nums));
    }

    public static void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
