package Linear.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Created by rliu on 11/12/16.
 * Valid Parentheses
 * Given a string with only the character of () {} [] check whether the input is valid
 * key: Using stack to solve the data stream problem that you need scan each item of the input, but might store it somewhere and process it later
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        HashMap<Character, Character> hm = new HashMap<>();
        hm.put('(', ')');
        hm.put('[', ']');
        hm.put('{', '}');
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (c != hm.get(stack.pop()))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String a = "[]{]()";
        String b = "[{()}]";
        System.out.println(isValid(a));
        System.out.println(isValid(b));

    }
}
