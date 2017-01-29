package Linear.String;

import java.util.LinkedHashMap;

/**
 * Created by rliu on 1/28/17.
 * 387. First Unique Character in a String
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 */
public class FirstUniqueCharinString {
    public static void main(String[] args) {
        System.out.println(firstUniqCharTwoPointer("leetcode"));
        System.out.println(firstUniqCharTwoPointer("loveleetcode"));
        System.out.println(firstUniqCharTwoPointer("aadadaad"));
    }

    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int[] alphabet = new int[26];
        //too complicated
//        Arrays.fill(alphabet, -2);
//        for (int i = 0; i < s.length(); i++) {
//            int letter = s.charAt(i) - 'a';
//            if (alphabet[letter] == -2)
//                alphabet[letter] = i;
//            else if (alphabet[letter] >= 0)
//                alphabet[letter] = -1;
//            else
//                alphabet[letter] = -1;
//        }

        //no matter what you will iterate the map again, so the first array can just
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        int rt = -1;
        for (int i = 0; i < s.length(); i++) {
            int letter = s.charAt(i) - 'a';
            if (alphabet[letter] == 1) {
                rt = i;
                break;
            }
        }
        return rt;
    }

    public static int firstUniqChar1Pass(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int[] alphabet = new int[26];
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int letter = s.charAt(i) - 'a';
            alphabet[letter]++;
            if (alphabet[letter] == 1)
                map.put(s.charAt(i), i);
            else
                map.remove(s.charAt(i));
        }
        if (map.size() == 0)
            return -1;
        else
            return map.entrySet().iterator().next().getValue();
    }

    //TODO: 18ms
    public static int firstUniqCharTwoPointer(String s) {
        if (s == null || s.length() == 0)
            return -1;

        int[] alphabet = new int[26];
        int rt = 0;
        alphabet[s.charAt(0) - 'a']++;

        for (int i = 1; i < s.length(); i++) {
            int letter = s.charAt(i) - 'a';
            alphabet[letter]++;
            if (s.charAt(i) == s.charAt(rt)) {
                while (rt <= i && alphabet[s.charAt(rt) - 'a'] != 1) {
                    rt++;
                }
            }
        }
        return rt == s.length() ? -1 : rt;
    }
}
