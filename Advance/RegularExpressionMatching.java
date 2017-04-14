package Advance;

/**
 * Created by rliu on 11/24/16.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String string = "aab";
        String pattern = "c*a*b";
        System.out.println(recursion(string, pattern, 0, 0));
        System.out.println(recursion2(string, pattern, 0, 0));
        System.out.println(recursionMemorized(string, pattern, 0, 0, new int[string.length() + 1][pattern.length() + 1]));
        System.out.println(dp(string, pattern));
    }

    //for loop
    //first step: figure out how many cases there are
    public static boolean recursion(String s, String p, int i, int j) {
        //Base Case: using j==p.length() but not i==s.length() here, is because j reach the pattern's end faster
        if (j == p.length())
            return i == s.length();
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            boolean start = recursion(s, p, i, j + 2);
            for (int k = 1; k <= s.length() - i; k++) {
                start |= (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursion(s, p, i + k, j + 2);
                if (start == true)
                    return true;
            }
            return false;
        } else
            return j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursion(s, p, i + 1, j + 1);

    }

    //when a*ac match aaaac, we use for loop to try every index
    //but we can unify to two case: 1. delete a* ,match 0
    //                              2. keep a*, match 1
    //complexity does not improve, just turn horizontal complexity to vertical complexity
    //still duplicate work, the can not distinguish default false and real false
    public static boolean recursion2(String s, String p, int i, int j) {
        if (j == p.length())
            return i == s.length();
        if (j < p.length() - 1 && (p.charAt(j + 1) == '*')) {
            //no more for loop, as we only has two cases here
            //match one (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursion2(s, p, i + 1, j)
            //match 0 :recursion2(s, p, i, j + 2)
            return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursion2(s, p, i + 1, j) || recursion2(s, p, i, j + 2);
        } else
            return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursion(s, p, i + 1, j + 1);

    }

    //match[i][j] to avoid repeated work
    public static boolean recursionMemorized(String s, String p, int i, int j, int[][] match) {
        if (j == p.length()) {
            match[i][j] = (i == s.length()) ? 1 : -1;
//            System.out.println(i + "," + j + ":" + match[i][j]);
            return i == s.length();
        }
        if (match[i][j] != 0) {
//            System.out.println(i + "," + j + ":" + match[i][j]);
            return match[i][j] == 1;
        }
        if (j < p.length() - 1 && (p.charAt(j + 1) == '*')) {
            //no more for loop, as we only has two cases here
            boolean isValid = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursionMemorized(s, p, i + 1, j, match) || recursionMemorized(s, p, i, j + 2, match);
            match[i][j] = isValid ? 1 : -1;
//            System.out.println(i + "," + j + ":" + match[i][j]);
            return isValid;
        } else {
            boolean isValid = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && recursionMemorized(s, p, i + 1, j + 1, match));
            match[i][j] = isValid ? 1 : -1;
//            System.out.println(i + "," + j + ":" + match[i][j]);
            return isValid;
        }
    }

    public static boolean dp(String s, String p) {
        //+1 is because we need to leave one space for letter matching null like a match ""
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int i = 0; i < p.length(); i++)
            if (p.charAt(i) == '*')
                match[0][i + 1] = match[0][i - 1];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '*') {
                    match[i + 1][j + 1] = match[i + 1][j - 1] || ((s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && match[i][j + 1]);

                } else {
                    match[i + 1][j + 1] = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && match[i][j];
                }
            }
        }
        return match[s.length() - 1][p.length() - 1];
    }
}
