package Advance;

/**
 * Created by rliu on 11/30/16.
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaximumProductSubarray {
    //subarray: 2 pointer -head+ tail
    //move head:expand current result
    //move tail:reset the subarray
    //as two smallest product may be larger: -4*-4=16
    //two possible track:min and max
    //Time:O(n)
    //Space:O(1)
    public static void main(String[] args) {
        int[] arr = {-2, -2, 4, -2, 2, -3, 0, 8};
        System.out.println(maxProd(arr));
    }

    public static int maxProd(int[] arr) {
        int minProd = arr[0];
        int maxProd = arr[0];
        int max = Integer.MIN_VALUE;
        int tempStart = 0;
        int start = 0;
        int end = 0;
        for (int i = 1; i < arr.length; i++) {
            int tempMax = Math.max(maxProd * arr[i], minProd * arr[i]);
            int tempMin = Math.min(maxProd * arr[i], minProd * arr[i]);
            maxProd = Math.max(tempMax, arr[i]);
            minProd = Math.min(tempMin, arr[i]);
            //when the tempMax is current index value, we know that we want to reset the start pointer
            if (maxProd == arr[i])
                tempStart = i;

            if (maxProd > max) {
                max = maxProd;
                start = tempStart;
                end = i;
            }
        }
        System.out.println(start + "/" + end);
        return max;
    }
}
