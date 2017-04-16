package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 4/15/17.
 * 115. Distinct Subsequences
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        String S = "xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvwxzwieeuqnjozrfwmzsylcwvsthnxujvrkszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzdejletqjoxmwftlxfcxgxgvpperwbqvhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhxolfjafmsrfdghnatexyanldrdpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcozpetyownmyneehdwqmtpjloztswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmksivoazlducanrqxynxlgvwonalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhmaizbadynajpibepbuacggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqyqcruujlwmfoagrckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvhgzclidzybajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbhigffkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdybosxkrabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo";
        String T = "rwmimatmhydhbujebqehjprrwfkoebcxxqfktayaaeheys";
        String s = "rabbbit";
        String t = "rabbit";
        int[][] cache = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < t.length(); j++)
                cache[i][j] = -1;
        System.out.println(numDistinct(s, t));
        System.out.println(numDistinctDFS(s, t, 0, 0, cache));
        for (int[] i : cache)
            System.out.println(Arrays.toString(i));
    }

    public static long numDistinct(String s, String t) {
        if (s.equals(t))
            return 1;
        if (s == null || s.length() == 0)
            return 0;
        if (t == null || t.length() == 0)
            return 1;

        //意义是S(0，i) T（0，j）这个子问题
        //TOP DOWN
        long[][] dp = new long[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (j - 1 >= 0 && s.charAt(i - 1) == t.charAt(j - 1)) { //rabbb rab
                    dp[i][j] = dp[i - 1][j - 1] //keep s(i) include the number of disinct S: s(0,i-1) and T(0,j-1)
                            + dp[i - 1][j];  //delete s(i), here include the number of distinct S: s(0,i-1) and T(0,j)
                } else
                    dp[i][j] = dp[i - 1][j];
                if (dp[i][j] == 0) //pruning
                    break;
            }
        }
        for (long[] i : dp)
            System.out.println(Arrays.toString(i));
        return dp[s.length()][t.length()];

    }


    //这种想法是bottom up的 意义是S(i，S.end) T（j，T.end）这个子问题
    public static int numDistinctDFS(String s, String t, int i, int j, int[][] cache) { //distinct num of subsequences of s(i,end) == t(j,end)
        if (j == t.length())
            return 1;
        if (i == s.length())
            return 0;
        if (cache[i][j] >= 0)
            return cache[i][j];

        if (s.charAt(i) == t.charAt(j)) {

            cache[i][j] = numDistinctDFS(s, t, i + 1, j, cache)
                    + numDistinctDFS(s, t, i + 1, j + 1, cache);
            return cache[i][j];
        } else {
            cache[i][j] = numDistinctDFS(s, t, i + 1, j, cache);
            return cache[i][j];
        }
    }
}
