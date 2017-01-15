package Sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by rliu on 1/12/17.
 * Based on data distribution counting sort
 */
public class NonComparisonSort {
    public static int BUCKET_NUM = 30;

    public static void main(String[] args) {
        int[] random = new int[]{4, 4, 7, 5, 6, 4, 9, 10, 15};
        System.out.println(Arrays.toString(countingSort(random)));

        int[] distributed = new int[]{0, 10, 100, 99, 888, 887, 255, 411, 346, 99};
        System.out.println(Arrays.toString(radixSort(distributed)));
    }

    /* input:  4 4 7 5 4 6 1
     * output: 1     3 1 1 1
     *     idx:1 2 3 4 5 6 7
     * can be stable? 4 appears 3 times but want to knows which 4 is which 4
     *
     * => using bucket to stored the end index of a number but not frequency
     * by scan from right to left, read the endindex to know where to put number and end index--;
     * Time: O(n+range)
     * Space:O(n+r)
     */
    public static int[] countingSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return arr;

        int[] range = getRange(arr);
        int min = range[0];
        int max = range[1];

        //range=max-min
        //count the frequency first
        //then turn frequency to end index of a number, and the range for the number will be from [min,max], and range of end index of number will be 0- arr.length
        int[] endIndex = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            endIndex[arr[i] - min] += 1;
        }

        //turn the frequency to end index
        for (int i = 1; i < endIndex.length; i++) {
            endIndex[i] += endIndex[i - 1];
        }

        //return the result
        int[] rt = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            rt[--endIndex[arr[i] - min]] = arr[i];
        }
        return rt;
    }

    //分布离散的话, previous solution might not workable
    //define the how many bucket we need
    //key is to build the index from number to bucket
    //like 333 -> the 3 bucket
    //we need to define: 1. the bucket size  2. build the index
    //inside the bucket, we use linkedList, to keep it stable, the last
    //time and space O(n+r)
    public static int[] bucketSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return arr;

        int[] range = getRange(arr);
        int min = range[0];
        int max = range[1];

        LinkedList<Integer>[] bucket = new LinkedList[BUCKET_NUM];

        //scan from right to left is to make sure the output is stable
        for (int i = arr.length - 1; i >= 0; i--) {
            int bucketIndex = (arr[i] - min) * BUCKET_NUM / (max - min + 1);
            if (bucket[bucketIndex] == null) {
                bucket[bucketIndex] = new LinkedList<>();
                bucket[bucketIndex].add(arr[i]);
            } else {
                int j = 0;
                for (Integer curr : bucket[bucketIndex]) {
                    //find where to insert
                    if (arr[i] <= curr)
                        break;
                    j++;
                }
                bucket[bucketIndex].add(j, arr[i]);
            }
        }

        int[] rs = new int[arr.length];
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null) {
                for (Integer curr : bucket[i])
                    rs[j++] = curr;
            }
        }
        return rs;
    }

    //how to strictly control bucket number instead of manually predefining
    //stable: yes
    public static int[] radixSort(int[] arr) {

        int[] range = getRange(arr);
        int min = range[0];
        int max = range[1];

        int max_dig = 10;
        for (; max_dig >= 0; max_dig--) {
            if (max / Math.pow(10, max_dig) >= 1)
                break;
        }

        //the largest int will be 10 digits
        //as the range of each digit is from 0 to 9
        int[] count = new int[10];
        int[] res = new int[arr.length];
        //iterate the digit
        for (int i = 0; i <= max_dig; i++) {
            //counting sort for each digit
            //count
            for (int j = 0; j < arr.length; j++) {
                int digit = getDigit(arr[j], i);
                count[digit]++;
            }

            //build end index
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }

            //create result
            for (int j = arr.length - 1; j >= 0; j--) {
                int digit = getDigit(arr[j], i);
                res[--count[digit]] = arr[j];
            }
            arr = res;
            res = new int[arr.length];
            count = new int[10];
        }

        return arr;

    }

    public static int getDigit(int number, int place) {
        return (int) (number / Math.pow(10, place) % 10);
    }


    public static int[] getRange(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        return new int[]{min, max};
    }
}
