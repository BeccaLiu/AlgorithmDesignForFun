package Graph.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static Graph.backtracking.Permutation.swap;

/**
 * Created by rliu on 1/21/17.
 * 47. Permutations II
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [[1,1,2],[1,2,1],[2,1,1]]
 */
public class Permutation2 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2};
        System.out.println(permuteUniqueBFS(nums));
        System.out.println(permuteUniqueDFSSwapNoListNoHashSet(nums, new ArrayList<>(), 0));
        Arrays.sort(nums);
        System.out.println(permuteUniqueDFS(nums, new boolean[nums.length], new ArrayList<>(), new ArrayList<>()));
        System.out.println(permuteUniqueDFSSwap(nums, new ArrayList<>(), new ArrayList<>(), 0));
        System.out.println(permuteUniqueDFSSwapNoList(nums, new ArrayList<>(), 0));

    }

    //can not using BFS to dealing with duplicate as we can not predict the different like for the list 1122, maybe you can using hasset to eliminate the duplicate
    //!!yes we can but change if continue -> if break
    //diff: is continue it will be 211->2211,2121,2112(first),2121,1221,1212         211->21(2)1  121->(2)121 dup here
    //          break      will be 211->2211(first)2121,1221(second)2112,1212,1122
    //only insert num to pos i before it meets duplicate num,
    public static List<List<Integer>> permuteUniqueBFS(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> next = new ArrayList<>();
            for (List<Integer> list : res) {
                for (int i = 0; i <= list.size(); i++) { //here is i<=list.size() not <list.size()
                    ArrayList<Integer> temp = new ArrayList<>(list);
                    if (i > 0 && num == temp.get(i - 1)) //only insert the before similar position
                        break;  //use break here but not continue will solve the problem that 1122
                    temp.add(i, num);
                    next.add(temp);
                }
            }
            res = next;
        }
        return res;
    }

    //compare with permutation 1, the duplicates is happening when we insert the same number at the same position
    public static List<List<Integer>> permuteUniqueDFS(int[] nums, boolean[] alreadyInList, ArrayList<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!alreadyInList[i]) { //using boolean array to check if current index has already in the list
                //if current nums[i] is same as nums[i-1], and nums[i-1] has not been put into the list,we will skip, because we visited numbers in turns
                //if nums[i-1] is not in the list means we have already process all the permutations with nums[i-1] at i-1 position, if we add current nums[i], it will duplicate
                if (i > 0 && nums[i] == nums[i - 1] && !alreadyInList[i - 1])
                    continue;
                list.add(nums[i]);
                alreadyInList[i] = true;
                permuteUniqueDFS(nums, alreadyInList, list, res);
                alreadyInList[i] = false;
                list.remove(list.size() - 1);
            }
        }
        return res;
    }

    public static List<List<Integer>> permuteUniqueDFSSwap(int[] nums, List<List<Integer>> res, List<Integer> list, int position) {
        if (position == nums.length) {
            res.add(new ArrayList<>(list));
            return res;
        }

        HashSet<Integer> used = new HashSet<>();
        for (int i = position; i < nums.length; i++) {
            if (used.add(nums[i])) { //use.add using directly rather than used.contains() first and used.add, as if nums[i] inside used set, when adding, it will return false;
                list.add(nums[i]);
                swap(nums, position, i);//swap here is for going down
                permuteUniqueDFSSwap(nums, res, list, position + 1);
                swap(nums, position, i); //swap here is prepare for previous level going right
                list.remove(list.size() - 1);
            }
        }
        return res;
    }

    //as we are swap, we do not need to maintain a ArrayList<Integer> list
    public static List<List<Integer>> permuteUniqueDFSSwapNoList(int[] nums, List<List<Integer>> res, int position) {
        if (position == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : nums) //no need list as recursion function parameter but just add here once
                list.add(i);
            res.add(new ArrayList<>(list));
            return res;
        }

        HashSet<Integer> used = new HashSet<>();
        for (int i = position; i < nums.length; i++) {
            if (used.add(nums[i])) { //use.add using directly rather than used.contains() first and used.add, as if nums[i] inside used set, when adding, it will return false;

                swap(nums, position, i);//swap here is for going down
                permuteUniqueDFSSwapNoList(nums, res, position + 1);
                swap(nums, position, i); //swap here is prepare for previous level going right

            }
        }
        return res;
    }

    //it is impossible , if we want to have limited space, we need addition func to check dup, and this only fits when input array is not long
    public static List<List<Integer>> permuteUniqueDFSSwapNoListNoHashSet(int[] nums, List<List<Integer>> res, int position) {
        if (position == nums.length) {
            res.add(new ArrayList<Integer>() {{
                for (int i : nums) add(i);
            }});
            return res;
        }

        //!(a||b)=!a&&!b
        for (int i = position; i < nums.length; i++) {
            if (i == position || !isDup(nums, position, i)) { //as we are checking duplicate here, we do not need sort
                swap(nums, position, i);//swap here is for going down
                permuteUniqueDFSSwapNoListNoHashSet(nums, res, position + 1);
                swap(nums, position, i); //swap here is prepare for previous level going right
            }
        }
        return res;
    }

    public static boolean isDup(int[] nums, int begin, int j) {
        for (int a = begin; a < j; a++) {
            if (nums[j] == nums[a])
                return true;
        }
        return false;

    }


}
