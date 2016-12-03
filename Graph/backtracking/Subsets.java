package Graph.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 11/26/16.
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * TODO:
 */
public class Subsets {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

    }

    public static void subset(int[] arr, List<Integer> list, List<List<Integer>> res) {
        res = new ArrayList<List<Integer>>();
        ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<Integer>());
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Integer> temp = queue.remove();
                res.add(new ArrayList<>(temp));
                for (int j = 0; j < temp.size() + 1; j++) {

                }

            }
        }
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j < i; j++) {
                list = new ArrayList<>();
                list.add(arr[j]);
            }

        }

    }
}
