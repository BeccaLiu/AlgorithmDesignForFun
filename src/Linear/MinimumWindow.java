package Linear;

import java.util.HashMap;

/**
 * Created by rliu on 11/11/16.
 * Minimum Window
 * Given a string S and a string T
 * find a substring in s with the minimum length
 * which contains all the character in T
 * S = BXCDEBBDC
 * T = BCD
 * Time Complexity: O(N)
 * Sub Prob: 1. How to store Character in T => HashMap <char,frequncy>
 * 2. when to move start/end => moving start when there is a match, when there is no match, move end
 * 3. how to identify a match => counter = T.length match when counter==0
 * 4. how to record the min => global min
 * <p>
 * Follow up:   1. using Array Map to replace hashMap
 * 2. As the char has the a ASCII associate to it
 * 3. A =>65 B=>66 ....z=>122
 * 4. initial int[] map=new int[128];
 */
public class MinimumWindow {
    public static String findMinString(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return "";
        if (t.length() > s.length())
            return "";

        HashMap<Character, Integer> freq = new HashMap<>();
        for (char m : t.toCharArray()) {
            if (freq.containsKey(m))
                freq.put(m, freq.get(m) + 1);
            else
                freq.put(m, 1);
        }

        int start = 0;
        int end = 0;
        int counter = t.length();
        int rts = 0;
        int min = Integer.MAX_VALUE;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (freq.containsKey(c)) {
                if (freq.get(c) > 0)
                    counter--;
                freq.put(c, freq.get(c) - 1);
            }
            end++;
            while (counter == 0) {
                if ((end - start) < min) {
                    rts = start;
                    min = end - start;
                }

                Integer f = freq.get(s.charAt(start));
                if (f != null) {
                    freq.put(s.charAt(start), f + 1);
                    if (f >= 0)
                        counter++;
                }
                start++;
            }
        }
        return min < s.length() ? s.substring(rts, rts + min) : "Not Found";
    }

    public static String findMinArrayMap(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return "";
        if (t.length() > s.length())
            return "";

        int[] map = new int[128];
        for (char m : t.toCharArray())
            map[m]++;
        int start = 0, end = 0;
        int rts = 0;
        int min = Integer.MAX_VALUE;
        int counter = t.length();
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0)
                counter--;
            while (counter == 0) {
                if ((end - start) < min) {
                    rts = start;
                    min = end - start;
                }
                if (map[s.charAt(start++)]++ >= 0)
                    counter++;
            }
        }
        return min < s.length() ? s.substring(rts, rts + min) : "Not Found";
    }

    public static void main(String[] args) {
        String s = "EBXCDEBBDC";
        String t = "BCD";
        System.out.println(findMinString(s, t));
        System.out.println(findMinArrayMap(s, t));

    }
}
