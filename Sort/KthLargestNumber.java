package Sort;

import java.util.Random;

/**
 * Created by rliu on 4/26/17 7:22 PM.
 * Find K-th largest element in an array.
 * using quick sort idea and [shuffle to get random number:wrong, shuffle is using random]
 */
public class KthLargestNumber {
    public static void main(String[] args) {
        System.out.println(kthLargestElement(105, new int[]{595240, 373125, 463748, 417209, 209393, 747977, 864346, 419023, 925673, 307640, 597868, 833339, 130763, 814627, 766415, 79576, 459038, 990103, 944521, 708820, 473246, 499960, 742286, 758503, 270229, 991199, 770718, 529265, 498975, 721068, 727348, 29619, 712557, 724373, 823743, 318203, 290432, 476213, 412181, 869308, 496482, 793858, 676162, 165869, 160511, 260864, 502521, 611678, 786798, 356560, 916620, 922168, 89350, 857183, 964051, 979979, 916565, 186532, 905289, 653307, 351329, 195491, 866281, 183964, 650765, 675046, 661642, 578936, 78684, 50105, 688326, 648786, 645823, 652329, 961553, 381367, 506439, 77735, 707959, 373271, 316194, 185079, 686945, 342608, 980794, 78777, 687520, 27772, 711098, 661265, 167824, 688245, 286419, 400823, 198119, 35400, 916784, 81169, 874377, 377128, 922531, 866135, 319912, 867697, 10904}));
    }

    public static int kthLargestElement(int k, int[] nums) {
        // write your code here

        return quickSwap(nums, nums.length - k, 0, nums.length - 1);

    }

    public static int quickSwap(int[] nums, int k, int low, int high) {
        int i = low + 1;
        int j = high;
        while (i <= j) {
            while (i <= high && nums[i] <= nums[low]) {
                i++;
            }
            while (j > low && nums[j] > nums[low]) {
                j--;
            }
            if (i < j)
                swap(nums, i++, j--);
        }
        if (j == 2)
            System.out.println();
        swap(nums, j, low);
        if (j == k)
            return nums[j];
        else if (j < k) {
            return quickSwap(nums, k, j + 1, high);
        } else
            return quickSwap(nums, k, low, j - 1);

    }

    public static void shuffleArray() {
        int[] a = new int[10];
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i); //return random [0,n-i) 重点是每次random的时候，给的range包括他自己
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }
}
