package Graph.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/22/17.
 * 90. Subsets II
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 */
public class Subsets2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        System.out.println(subsetsWithDup(arr));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        subset(nums, new ArrayList<>(), res, 0);
        return res;
    }

    public static void subset(int[] arr, ArrayList<Integer> list, List<List<Integer>> res, int pos) {
        res.add(new ArrayList<Integer>(list));

        for (int i = pos; i < arr.length; i++) {
            if (i > pos && arr[i] == arr[i - 1])
                continue;
            list.add(arr[i]);
            subset(arr, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
