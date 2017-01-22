package Graph.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/21/17.
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Input: k = 3, n = 7
 * Output:[[1,2,4]]
 * Input: k = 3, n = 9
 * Output [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        if (k == 0 || n == 0)
            return list;
        helper(n, list, new ArrayList<>(), 1, k);
        return list;
    }

    //pruning for half of each level as for 7
    //1st: 1 2 3 4 5 6 7 8
    //we can pruning the 5,6,7,8 as it is impossible that target-[target/2 which is 5 in this case] will definitely smaller or equals then target/2+1
    //like if n=200 which means we need go very deep,can we stop at some place that is definitely impossible
    public static void helper(int target, List<List<Integer>> res, ArrayList<Integer> list, int number, int k) {
        if (target <= 0 && list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = number; i <= 9; i++) {
            int newTarget = target - i;
            if (newTarget >= 0 && list.size() < k) {
                list.add(i);
                helper(newTarget, res, list, i + 1, k);
                list.remove(list.size() - 1);
            }
        }
    }
}
