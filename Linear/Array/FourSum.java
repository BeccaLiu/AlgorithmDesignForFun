package Linear.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rliu on 12/26/16.
 * 18. 4Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all *unique* quadruplets in the array which gives the sum of target.
 */
public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return list;
        Arrays.sort(nums);
        //[I am stuck here]: do not understand the physical meaning of i,j,m,n
        //i is the index of the first integer, i will be different each round
        for (int i = 0; i + 3 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j + 2 < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int res = target - (nums[i] + nums[j]);
                int m = j + 1;
                int n = nums.length - 1;
                while (m < n) {
                    int sum = nums[m] + nums[n];
                    if (sum == res) {
                        StringBuilder sb = new StringBuilder();
                        ArrayList al = new ArrayList();
                        al.add(nums[i]);
                        al.add(nums[j]);
                        al.add(nums[m++]);
                        al.add(nums[n--]);
                        list.add(al);
                        while (m < n && nums[m] == nums[m - 1])
                            m++;
                        while (m < n && nums[n] == nums[n + 1])
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
}
