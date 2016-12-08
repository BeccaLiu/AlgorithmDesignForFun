package Linear;

import java.util.Arrays;

/**
 * Created by rliu on 12/7/16.
 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * Result: [3, 9, 15, 33]
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * Result: [-23, -5, 1, 7]
 * Primitive Idea, using O(n) to calculate array, and using O(nlogn) to sort
 * [I am stuck here]: forget about the feature that when a>0, the f(x) will open upward and the smallest f(x) will be at x=-b/a
 * when a<0, the f(x) will open downwards and the biggest y will be at x=-b/a
 */
public class SortTransformArray {
    public static void main(String[] args) {
        int a = -1;
        int b = 3;
        int c = 5;
        int[] arr = {-5, -4, -2, 2, 4, 5};
        System.out.println(Arrays.toString(binarySearchMethod(arr, a, b, c)));
        System.out.println(Arrays.toString(twoPointer(arr, a, b, c)));

    }

    public static int[] processLine(int[] arr, int a, int b, int c) {
        int[] rt = new int[arr.length];
        int i = 0;
        while (i <= arr.length - 1) {
            if (b >= 0) {
                rt[i] = b * arr[i++] + c;
            } else
                rt[arr.length - 1 - i] = b * arr[i++] + c;
        }
        return rt;
    }

    //using find the asymmetric mid line using binary search and using two pointer to rearrange the array
    public static int[] binarySearchMethod(int[] arr, int a, int b, int c) {
        int[] rt = new int[arr.length];
        int i = 0;
        if (a == 0)
            return processLine(arr, a, b, c);

        float asymmetryAxis = (float) -b / (2 * a);
        int index = getMidIndex(arr, asymmetryAxis);
        int l;
        int r;
        if (index <= asymmetryAxis) {
            l = index;
            r = index + 1;
        } else {
            l = index - 1;
            r = index;
        }

        if (a > 0) {
            while (l >= 0 && r <= arr.length - 1) {
                if (Math.abs(arr[l] - asymmetryAxis) <= Math.abs(arr[r] - asymmetryAxis)) {
                    rt[i++] = (int) (a * Math.pow(arr[l], 2) + b * arr[l] + c);
                    l--;
                } else {
                    rt[i++] = (int) (a * Math.pow(arr[r], 2) + b * arr[r] + c);
                    r++;
                }
            }
            while (l >= 0) {
                rt[i++] = (int) (a * Math.pow(arr[l], 2) + b * arr[l] + c);
                l--;
            }
            while (r <= arr.length - 1) {
                rt[i++] = (int) (a * Math.pow(arr[r], 2) + b * arr[r] + c);
                r++;
            }
        } else {
            while (l >= 0 && r <= arr.length - 1) {
                if (Math.abs(arr[l] - asymmetryAxis) <= Math.abs(arr[r] - asymmetryAxis)) {
                    rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[l], 2) + b * arr[l--] + c);
                } else {
                    rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[r], 2) + b * arr[r++] + c);
                }
            }
            while (l >= 0) {
                rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[l], 2) + b * arr[l--] + c);
            }
            while (r <= arr.length - 1) {
                rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[r], 2) + b * arr[r++] + c);
            }
        }

        return rt;
    }

    public static int getMidIndex(int[] arr, float k) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] > k) {
                end = mid - 1;
            } else
                start = mid + 1;
        }
        return start;
    }

    //better solution: as we know the left and right of the arr will be either largest or smallest according to a, we not need to have binary search\
    public static int[] twoPointer(int[] arr, int a, int b, int c) {
        if (a == 0)
            return processLine(arr, a, b, c);
        int left = 0;
        int right = arr.length - 1;
        int[] rt = new int[arr.length];
        int i = 0;
        float asymmetryAxis = (float) -b / (2 * a);
        if (a > 0) {
            while (left <= right) {
                if (Math.abs(arr[left] - asymmetryAxis) >= Math.abs(arr[right] - asymmetryAxis)) {
                    rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[left], 2) + b * arr[left++] + c);
                } else
                    rt[arr.length - 1 - i++] = (int) (a * Math.pow(arr[right], 2) + b * arr[right--] + c);
            }
        } else {
            while (left <= right) {
                if (Math.abs(arr[left] - asymmetryAxis) < Math.abs(arr[right] - asymmetryAxis)) {
                    rt[i++] = (int) (a * Math.pow(arr[right], 2) + b * arr[right--] + c);

                } else
                    rt[i++] = (int) (a * Math.pow(arr[left], 2) + b * arr[left++] + c);

            }
        }
        return rt;
    }
}
