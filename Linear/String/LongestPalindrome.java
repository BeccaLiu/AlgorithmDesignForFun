package Linear.String;

/**
 * Created by rliu on 1/27/17.
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {
    //this is a O(N^2) solution, check every interval,
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        String longPl = s.substring(0, 1);
        //compare start i,i+1
        //(n-1)*O(n) we need to compare n-1 pairs
        for (int i = 0; i < s.length(); i++) {
            String temp = getPalindrome(s, i, i + 1);
            if (temp.length() > longPl.length())
                longPl = temp;
        }
        //only compare start i-1 and i+1
        //(n-1)*O(n)
        for (int i = 1; i < s.length(); i++) {
            String temp = getPalindrome(s, i - 1, i + 1);
            if (temp.length() > longPl.length())
                longPl = temp;
        }
        return longPl;
    }

    public static String getPalindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babab"));
    }

    //TODO: faster solution
}
