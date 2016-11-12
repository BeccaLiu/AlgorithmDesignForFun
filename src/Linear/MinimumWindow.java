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
 * Conclusion: 1.
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
                System.out.println(s.substring(start, end));
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
        return s.substring(rts, rts + min);
    }

    public static void main(String[] args) {
        String s = "EBXCDEBBDC";
        String t = "BCD";
        System.out.println(findMinString(s, t));

    }
}
