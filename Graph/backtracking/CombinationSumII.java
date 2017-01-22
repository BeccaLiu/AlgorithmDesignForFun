package Graph.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rliu on 1/21/17.
 * 40. Combination Sum II
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [[1, 7],[1, 2, 5],[2, 6],[1, 1, 6]]
 */
public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0)
            return list;
        Arrays.sort(candidates);
        helper(candidates, target, list, new ArrayList<>(), 0);
        return list;
    }

    //pos means the starting index of current available nums
    public static void helper(int[] arr, int target, List<List<Integer>> res, ArrayList<Integer> list, int pos) {
        if (target <= 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            if (i == pos || i > pos && arr[i] != arr[i - 1]) { //for each level, if pos[i]==pos[i-1] we will skip
                if (target - arr[i] >= 0) {
                    list.add(arr[i]);
                    helper(arr, target - arr[i], res, list, i + 1); //for next level and current level, we do not want duplicate, so the pos will be current pos+1
                    list.remove(list.size() - 1);
                }
            }
        }
    }

}
