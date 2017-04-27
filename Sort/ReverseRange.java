package Sort;

import java.util.Arrays;

/**
 * Created by rliu on 4/26/17 12:24 PM.
 */
public class ReverseRange {
    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{4, 3, 2, 1}));
        System.out.println(reversePairsII(new int[]{1, 3, 2, 3, 1}));
    }

    public static long reversePairs(int[] A) {
        // Write your code here
        if (A == null || A.length == 0)
            return 0;
        long count = 0;

        for (int width = 1; width < A.length; width *= 2) {
            for (int i = 0; i < A.length; i = i + 2 * width) {
                if (i + width >= A.length)
                    continue;
                int n = Math.min(A.length, i + width);
                int[] copy = Arrays.copyOfRange(A, i, n);
                int m = 0;
                int j = i;
                while (m < copy.length && n < Math.min(i + 2 * width, A.length)) {
                    if (A[n] < copy[m]) {
                        count += copy.length - m;
                        A[j++] = A[n++];
                    } else {
                        A[j++] = copy[m++];
                    }
                }
                while (m < copy.length) {
                    A[j++] = copy[m++];
                }
            }
        }
        return count;
    }

    //Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
    //You need to return the number of important reverse pairs in the given array.

    public static int reversePairsII(int[] nums) {
        //using idea of merge sort
        if (nums == null || nums.length == 0)
            return 0;

        int count = 0;

        for (int width = 1; width < nums.length; width *= 2) {
            for (int i = 0; i < nums.length; i += 2 * width) {
                if (i + width >= nums.length)
                    continue;

                //first part count
                int n = i + width;
                int r = Math.min(i + 2 * width, nums.length);
                int m = i;
                while (m < i + width && n < r) {
                    if (nums[m] / 2.0 > nums[n]) { //1.divide by 2 nums[m]/2>nums[n] ranther than nums[m]>2*nums[n], 2. using /2.0 than /2
                        count += i + width - m;
                        n++;
                    } else
                        m++;
                }

                //second part merge
                int[] copy = Arrays.copyOfRange(nums, i, i + width);
                m = 0;
                n = i + width;
                int j = i;
                while (m < copy.length || n < r) {
                    if (m >= copy.length)
                        break;
                    else if (n == r || copy[m] < nums[n])
                        nums[j++] = copy[m++];
                    else
                        nums[j++] = nums[n++];
                }
            }
        }
        return count;


    }
}
