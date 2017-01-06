package Linear.QueuePointer;

import java.util.*;

/**
 * Created by rliu on 12/26/16.
 * 18. 4Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all *unique* quadruplets in the array which gives the sum of target.
 */
public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSumSplit(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return list;
        Arrays.sort(nums);
        //[I am stuck here]: do not understand the physical meaning of i,j,m,n
        //i is the index of the first integer, i will be different each round
        for (int i = 0; i + 3 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) //skip some i will faster
                continue;
            for (int j = i + 1; j + 2 < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) //skip some j will faster
                    continue;
                int res = target - (nums[i] + nums[j]);
                int m = j + 1;
                int n = nums.length - 1;
                while (m < n) { //using idea of two pointer
                    int sum = nums[m] + nums[n];
                    if (sum == res) {
                        ArrayList al = new ArrayList();
                        al.add(nums[i]);
                        al.add(nums[j]);
                        al.add(nums[m++]);
                        al.add(nums[n--]);
                        list.add(al);
                        while (m < n && nums[m] == nums[m - 1]) //skip some m
                            m++;
                        while (m < n && nums[n] == nums[n + 1]) //skip some n
                            n--;
                    } else if (sum < res)
                        m++;
                    else
                        n--;
                }
            }
        }
        return list;
    }

    //using the idea of 4 sum2 to fasten the solution
    //however we can not process the duplicate easily
    public static List<List<Integer>> fourSumSplit(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return list;

        ArrayList<int[]> arr = new ArrayList<int[]>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                arr.add(new int[]{nums[i] + nums[j], i, j});
            }
        }
        Collections.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            int tempSum = arr.get(left)[0] + arr.get(right)[0];
            if (tempSum == target) {
                int i = arr.get(left)[1];
                int j = arr.get(left)[2];
                int m = arr.get(right)[1];
                int n = arr.get(right)[2];
                if (i != m && i != n && j != m && j != n) {
                    ArrayList al = new ArrayList();
                    al.add(nums[i]);
                    al.add(nums[j]);
                    al.add(nums[m]);
                    al.add(nums[n]);
                    list.add(al);
                }
                left++;
            } else if (tempSum < target)
                left++;
            else
                right--;

        }
        return list;
    }
}
