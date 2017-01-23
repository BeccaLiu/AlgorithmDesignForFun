package Graph.backtracking;

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
 */
public class Subsets {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(subsets(arr));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        subsetL(nums, new ArrayList<>(), res, 0);
        return res;
    }


    //Analysis: first it is asking about the subset, so how many set there will be for input like 1,2,3? it is 2^3 as for each number, we have two options, put it in the array or not
    //what we need for each sub problem? 1.a pos: the pos in the arr to see we should put this nums[pos] in array or not 2. a list contains all the info in subset
    //what we need pass to the next sub problem? tell next pos to move to the next pos 2. the newly processed subset
    //base case: good base: when pos reach the end
    //           no bad base;
    public static void subset(int[] arr, ArrayList<Integer> list, List<List<Integer>> res, int pos) {
        if (pos == arr.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        //do not put arr[i] in list; just dig down
        subset(arr, list, res, pos + 1);
        //put arr[i] in list;
        list.add(arr[pos]);
        subset(arr, list, res, pos + 1);
        list.remove(list.size() - 1);
    }

    //another way of thinking
    //pos 0 means empty set                  []
    //pos 1 for loop will generate      [1],    [2],[3]
    //pos 2 for loop will generate   [12],[13],[23]
    //pos 3 for loop will generate [1,2,3]
    //pos means the previous pos in arr has already been looked at and already make the decision of put or not put.
    public static void subsetL(int[] arr, ArrayList<Integer> list, List<List<Integer>> res, int pos) {
        //do not
        res.add(new ArrayList<Integer>(list));

        for (int i = pos; i < arr.length; i++) {
            list.add(arr[i]);
            subset(arr, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
