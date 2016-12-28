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
        System.out.println(simplifyPath("/...."));
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

    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return "";

        //[I am stuck here]: try to combine split and parse the content all at once by writing my own split function, but it does not work so well, and take me a lot of time to debug
        //so in some cases it may be easy to using just the java function, some cases it might be easy to using the a self write function.
        ArrayDeque<String> stack = new ArrayDeque<String>();
        int index = 0;
        for (String s : path.split("/")) {
            //pay attention to substring when index==i, string.substring(0,0), it will be ""
            //but string.substring(0,0)=="" will be false; should use equals()
            if (s.equals("") || s.equals("."))
                continue;
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(s);
            }
        }

        //corner case: ///  /....   /../ /home//dir
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.removeLast());
        }
        if (sb.length() == 0) {
            return "/";
        } else
            return sb.toString();
    }
}
