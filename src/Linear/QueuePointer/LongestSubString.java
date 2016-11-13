package Linear.QueuePointer;

import java.util.HashSet;

/**
 * Created by rliu on 11/11/16.
 * Longest Substring Without Repeating Characters
 * Given a string, find the longest substring without repeating character and return the length of this substring
 * bcacex -> 4
 * bbaaae -> 2
 */
public class LongestSubString {
    public static int longestSub(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> hs = new HashSet<>();
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        while (end < s.length()) {
            char curr = s.charAt(end++);
            if (hs.contains(curr)) {
                while (s.charAt(start++) != curr) {
                }
            } else {
                hs.add(curr);
                if ((end - start) > max)
                    max = end - start;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestSub("bb"));
        System.out.println(longestSub("bcacexg"));
        System.out.println(longestSub("bbaaaea"));
        System.out.println(longestSub("abcdeff"));

    }
}
