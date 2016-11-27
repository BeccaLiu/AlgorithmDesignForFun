package Graph.backtracking;

import java.util.ArrayList;

/**
 * Created by rliu on 11/27/16.
 * Factor Combination
 * Numbers can be regarded as product of its factors. For example,
 * <p>
 * 12 = 2 x 2 x 3;
 * = 2 x 6
 * = 3 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * <p>
 * Analysis:
 * after drawing the solution tree, we need find a way to prune to optimize its time complexity
 */
public class FactorCombination {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        factorCombination(res, new ArrayList<>(), 12, 2);
        System.out.print(res);
    }

    // 12-[2]-> 6-[2]-> 3-[3]-> 1  res 2,2,3
    //       -> 6-[3]-> 2-[2]-> 1
    //       -> 6-[6]-> 1          res 2,6
    //   -[3]-> 4-[2]-> 2-[2]-> 1
    //       -> 4-[4]-> 1          res 3,4
    //   -[4]-> 3-[3]-> 1 \
    //   -[6]-> 2-[2]-> 1  |-> pruning by n>sqrt(n)
    //   -[12]-> 1        /
    //res is contains all the result
    //list contains the factor from the beginning level to current level
    //base case is when reminder=1,
    public static void factorCombination(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int n, int factor) {
        if (n == 1) {
            if (list.size() > 1) //pruning the result like 12=12*1
                res.add(new ArrayList<Integer>(list));
            return;
        }

        //every time current factor is equal or bigger than previous level factor, it is pruning in vertical
        //however we can pruning in horizontal by : i<Math.sqrt(n)
        //for (int i = factor; i <= n; i++) {
        for (int i = factor; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                factorCombination(res, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }

        //TODO: why need this? looks duplicate: think about 15 which result is [[3,5]] when level 1=3 level 2=5 5>Math.Sqrt(5)
        //only exception is when n want to divided by n
        list.add(n);
        factorCombination(res, list, 1, n);
        list.remove(list.size() - 1);
    }
}
