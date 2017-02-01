package Linear.String;

/**
 * Created by rliu on 1/31/17.
 * 395. Longest Substring with At Least K Repeating Characters
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 */
public class LongestSubstringWithAtLeastKRepeatChar {
    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb", 3));
        System.out.println(longestSubstring("cababbc", 2));
        System.out.println(longestSubstring("bbaaacbd", 3));
    }

    //can not solve in one pass with two pointer
    //might need two pass
    //using the idea of [divide and conquer], and divide at the invalid point
    public static int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length());
    }

    public static int longestSubstring(String s, int k, int start, int end) {
        if (end - start < k)
            return 0;
        if (k == 0)
            return s.length();

        int[] counter = new int[26];
        // boolean[] valid = new boolean[26];//Attention: using boolean array to replace HashSet<Character>

        for (int i = start; i < end; i++) {
            counter[s.charAt(i) - 'a']++;
//            if (counter[s.charAt(i) - 'a'] >= k)
//                valid[s.charAt(i) - 'a'] = true;
        }

        //must have this step to check if the string is fully valid, if it is, we do not need to go deeper
        boolean fullyValid = true;

        //need call recursively
        int max = Integer.MIN_VALUE;
        int lastStart = 0;
        for (int i = start; i < end; i++) {
            if (counter[s.charAt(i) - 'a'] < k) { //current char is not repeating more than k times
                //if (!valid[s.charAt(i) - 'a']) {
                fullyValid = false;
                max = Math.max(max, longestSubstring(s, k, lastStart, i));
                lastStart = i + 1;
            }

        }
        if (fullyValid)
            return end - start;
        max = Math.max(max, longestSubstring(s, k, lastStart, end));
        return max;
    }
}
