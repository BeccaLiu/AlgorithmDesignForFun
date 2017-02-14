package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rliu on 2/13/17.
 * 301. Remove Invalid Parentheses
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * Analysis: to check if a string is valid parentheses, using stack or counter, to make it valid just remove all the ')' previously, if have consecutive ) remove the first one
 * record the position for next recursive call
 * as want to return the minimum, then we will think of bfs, first level remove 1, and if all the substring in level 1 is not valid, tentative remove second
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        System.out.println(removeInvalidParenthesesBFS("))()())()"));
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0)
            return list;
        list.add("");
        removeInvalidParenthesesDFS(s, 0, list);
        return list;
    }

    //bfs is much more straightforward but low in efficiency
    public static List<String> removeInvalidParenthesesBFS(String s) {
        List<String> list = new ArrayList<>();
//        if (s == null || s.length() == 0)
//            return list;

        ArrayDeque<String> q = new ArrayDeque<>();
        q.offer(s);
        HashSet<String> visited = new HashSet<>(); //using hashset to remove dup, as when we go to 2+ level, some distinct string after remove might generate duplicate entry
        visited.add(s);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (isValid(curr))
                    list.add(curr);
                for (int j = 0; j < curr.length(); j++) {
                    if (j - 1 < 0 || curr.charAt(j) != curr.charAt(j - 1)) {
                        String temp = curr.substring(0, j) + curr.substring(j + 1, curr.length());
                        if (!visited.contains(temp)) {
                            q.offer(temp);
                            visited.add(temp);
                        }
                    }
                }
            }
            if (list.size() > 0)
                break;
        }
        if (list.size() == 0)
            list.add("");
        return list;
    }

    public static boolean isValid(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                counter++;
            else if (s.charAt(i) == ')')
                counter--;
            if (counter < 0)
                break;
        }
        return counter == 0;
    }

    public static void removeInvalidParenthesesDFS(String s, int pos, List<String> list) {
        //int counter = 0;

        for (int counter = 0, i = pos; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                counter++;
            else if (s.charAt(i) == ')')
                counter--;
            if (counter < 0) {
                while (s.charAt(i - 1) == ')' && s.charAt(i) == ')') //moving i to ))))(the first (
                    i++;
                for (int j = pos; j < i; j++) {
                    if (s.charAt(j) == ')' && s.charAt(j - 1) != ')') //j will stop at first )
                        removeInvalidParenthesesDFS(s.substring(0, j) + s.substring(j + 1, s.length()), i - 1, list);
                }
                return;
            }
        }
    }
}
