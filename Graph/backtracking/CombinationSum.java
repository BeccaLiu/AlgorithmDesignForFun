package Graph.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rliu on 1/21/17.
 * 39. Combination Sum
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [[7],[2, 2, 3]]
 */
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0)
            return list;
        Arrays.sort(candidates);
        helper(candidates, target, list, new ArrayList<>(), 0);
        return list;
    }

    public static void helper(int[] arr, int target, List<List<Integer>> res, ArrayList<Integer> list, int pos) {
        if (target <= 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                list.add(arr[i]);
                helper(arr, target - arr[i], res, list, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
