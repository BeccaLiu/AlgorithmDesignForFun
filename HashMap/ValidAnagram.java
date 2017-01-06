package HashMap;

import java.util.HashMap;

/**
 * Created by rliu on 1/6/17.
 * 242. Valid Anagram
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 */
public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("", ""));
        System.out.println(isAnagram("anagram", "nagarnm"));
    }

    //time complexity: O(s.length())
    public static boolean isAnagram(String s, String t) {
        if ((s == null && t == null) || (s.length() == 0 && t.length() == 0))
            return true;
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (map.containsKey(cur))
                map.put(cur, map.get(cur) + 1);
            else
                map.put(cur, 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char cur = t.charAt(i);
            if (map.containsKey(cur))
                map.put(cur, map.get(cur) - 1);
            else
                return false;
        }

        for (Integer i : map.values()) {
            if (i != 0)
                return false;
        }
        return true;
    }

    //faster solution
    // as the input is char, so there are only 26 char
    //although this solution is s.length as well
    //but much more faster than previous one, as building hashmap take times.
    //
    public static boolean isAnagramCharArray(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i = 0; i < 26; i++) if (alphabet[i] != 0) return false;
        return true;
    }
}
