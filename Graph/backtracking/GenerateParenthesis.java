package Graph.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/30/17.
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * "((()))", then we pop until ((, we found we can add a ) always add ( first and then add )
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n == 0)
            return res;
        dfs(n, res, new ArrayList<Character>(), 0, 0);
        return res;
    }

    public static void dfs(int n, List<String> res, ArrayList<Character> list, int open, int close) {
        if (list.size() == 2 * n) {
            StringBuilder sb = new StringBuilder();
            for (char c : list)
                sb.append(c);
            res.add(sb.toString());
        }
        //we are able to add ( whenever we want until we hit the max of input n
        //dfs here is we add ( all the way to the n
        //I am stuck here, can not clearly think out the solution to different condition
        if (open < n) {
            list.add('(');
            dfs(n, res, list, open + 1, close);
            list.remove(list.size() - 1);
        }
        //when open is more than close, we need to close it like (()(->(()())
        if (close < open) {
            list.add(')');
            dfs(n, res, list, open, close + 1);
            list.remove(list.size() - 1);
        }

    }

}
