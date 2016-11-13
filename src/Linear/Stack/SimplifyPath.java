package Linear.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by rliu on 11/10/16.
 * Simplify the absolute path for a unix-file style
 * path=/tmp/../algo/./../etc  =/etc
 */
public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplePath("/tmp/cg/../algo/./../etc"));
    }

    public static String simplePath(String s) {
        if (s == null || s.length() == 0)
            return "";
        String[] arr = s.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String a : arr) {
            if (a.equals(".") || a.equals(""))
                continue;
            else if (a.equals(".."))
                stack.pop();
            else
                stack.push(a);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb = sb.insert(0, "/" + stack.pop());
        }
        return sb.toString();
    }
}
