package HashMap;

/**
 * Created by rliu on 2/16/17.
 * 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 */
public class MiniumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOABECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length())
            return "";

        int[] counter = new int[128];

        for (int i = 0; i < t.length(); i++) {
            counter[t.charAt(i) - ' ']++;
        }
        int toMatched = t.length();

        String rt = new String();
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            char curr = s.charAt(end++);
            if (counter[curr - ' ']-- > 0) { //if counter bigger than 1 means this char is the char to be matched
                toMatched--;
            }
            if (toMatched == 0) { //when all of the string is matched, we need to move start to get the smallest string and reset the start
                while (start <= end) {
                    counter[s.charAt(start) - ' ']++;
                    if (counter[s.charAt(start) - ' '] <= 0) { // this char we do not care
                        start++;
                    } else { //if s.charAt(start)==1 means wo found the char that in t, but will make start and end invalid again.
                        String temp = s.substring(start, end);
                        System.out.println(temp);
                        if (rt.equals("") || temp.length() < rt.length())
                            rt = temp;
                        toMatched++; //invalid again tomatched+1
                        start++; //reset the start
                        break; //moving end from now on
                    }
                }
            }
        }

        return rt;
    }
}
