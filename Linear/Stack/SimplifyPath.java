package Linear.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by rliu on 11/10/16.
 * Simple Path
 * Simplify the absolute path for a unix-file style
 * path=/tmp/../algo/./../etc  =/etc
 */
public class SimplifyPath {
    public static void main(String[] args) {

        System.out.println(simplePath("/tmp/cg/../algo/./../etc"));
        System.out.println(simplePathTwoPointer("/tmp/cg/../algo/./../etc"));
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

    public static String simplePathTwoPointer(String s) {
        if (s == null || s.length() == 0)
            return "";
        String[] arr = s.split("/");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].equals(".") || arr[i].equals(""))
                continue;
            else if (arr[i].equals(".."))
                count++;
            else {
                if (count > 0) {
                    count--;
                    continue;
                }
                sb = sb.insert(0, "/" + arr[i]);
            }
        }
        return sb.toString();
    }
}
