package Linear.String;

/**
 * Created by rliu on 2/1/17.
 * 161. One Edit Distance
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {
    public static void main(String[] args) {
        System.out.println(isOneEditDistance2("abdddd", "acddddd"));
    }

    // \                        |
    //  \replace --> insert     | delete
    //just using dp will generate time limited exceed error
    public static boolean isOneEditDistance(String s, String t) {
        if (s.length() - t.length() > 1)
            return false;
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (i == 0)
                    dp[0][j] = j;
                else {
                    if (j == 0)
                        dp[i][j] = i;
                    else {
                        if (s.charAt(i - 1) == t.charAt(j - 1))
                            dp[i][j] = dp[i - 1][j - 1];
                        else
                            dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }
                }
            }
        }

        return dp[s.length()][t.length()] == 1;
    }

    //know the detail and fix the problem
    public static boolean isOneEditDistance2(String s, String t) {
        if (s.length() - t.length() > 1 || t.length() - s.length() > 1)
            return false;
        if (s.length() == t.length()) {//only replace
            int invalid = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    invalid++;
                }
            }
            return invalid == 1;
        } else {//insert or delete
            if (s == null || s.equals("") || t == null || t.equals(""))
                return true;

            int i = 0;
            int j = 0;

            //find the first invalid index
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i) != t.charAt(j)) {
                    break;
                }
                i++;
                j++;
            }

            int firstInvalidIdx = i;
            i = s.length() - 1;
            j = t.length() - 1;
            while (i >= firstInvalidIdx && j >= firstInvalidIdx) {
                if (s.charAt(i) != t.charAt(j))
                    break;
                i--;
                j--;
            }

            return !(i - firstInvalidIdx >= 1 || j - firstInvalidIdx >= 1);
        }
    }
}
