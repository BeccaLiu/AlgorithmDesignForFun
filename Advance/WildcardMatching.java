package Advance;

/**
 * Created by rliu on 2/22/17.
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aab", ""));
    }

    public static boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0)
            return p == null || p.length() == 0;
        if (s == null || s.length() == 0) {
            for (int i = 0; i < p.length(); i++)
                if (p.charAt(i) != '*')
                    return false;
            return true;
        }

        int[][] memo = new int[s.length() + 1][p.length() + 1];
        return isMatchHelper(s, p, 0, 0, memo);
    }

    public static boolean isMatchHelper(String s, String p, int i, int j, int[][] memo) {
        if (j == p.length()) {
            boolean isValid = i == s.length();
            memo[i][j] = isValid ? 1 : -1;
            return isValid;
        }
        if (memo[i][j] != 0)
            return memo[i][j] == 1;
        if (p.charAt(j) == '*') {
            boolean isValid = (i < s.length() && isMatchHelper(s, p, i + 1, j, memo)) || (isMatchHelper(s, p, i, j + 1, memo));
            memo[i][j] = isValid ? 1 : -1;
            return isValid;
        } else {
            boolean isValid = i < s.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) && isMatchHelper(s, p, i + 1, j + 1, memo);
            memo[i][j] = isValid ? 1 : -1;
            return isValid;
        }
    }
}
