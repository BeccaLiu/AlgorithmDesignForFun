package Linear.Stack;

import java.util.ArrayDeque;

/**
 * Created by rliu on 2/21/17.
 * 388. Longest Absolute File Path
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 */
public class LongestAbsFilePath {
    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

    public static int lengthLongestPath(String input) {
        if (input == null || input.length() == 0)
            return 0;

        boolean isFile = false;
        int l = 0;
        int r = 0;
        int maxLen = 0;
        int level = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        while (r <= input.length()) {
            if (r == input.length() || (input.charAt(r) == '\n')) {
                System.out.println(input.substring(l + level, r));
                int len = r - l - level;
                while (deque.size() >= 1 && level < deque.size())
                    deque.pop();
                if (deque.isEmpty())
                    deque.push(len);
                else if (deque.size() >= 1 && level >= deque.size())
                    deque.push(deque.peek() + len + 1); //+1 is for / like a/b/c

                if (isFile) {
                    isFile = false;
                    maxLen = Math.max(maxLen, deque.peek());
                }
                level = 0;
                l = r + 1;
                //[Attention: here is \t but not check charAt(i)=='\'&&charAt(i+1)=='t']
            } else if (r < input.length() - 1 && (input.charAt(r) == '\t')) {
                level++;
            }
            if (r < input.length() && input.charAt(r) == '.')
                isFile = true;
            r++;
        }
        return maxLen;
    }

}
