package Graph.backtracking;

/**
 * Created by rliu on 1/22/17.
 * 72. Edit Distance
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * <p>
 * Analysis:
 * 1. for recursion question, think about following three question
 * 1.1 what's the sub problem? CURRENT LEVEL
 * 1.2 what's the next sub problem? how to get into the NEXT LEVEL
 * 1.3 data you want to pass to NEXT LEVEL, data you want to get from NEXT LEVEL
 * <p>
 * 2. Recursion Rule
 * CURRENT level, we have i, j which means the index we need to take look at, and previous then that, it is already matched
 * insert -> NEXT(i,j+1)
 * replace -> NEXT(i+1,j+1)
 * delete -> NEXT(i+1,j)
 * S1.charAt(i)==S2.charAt(j) ->  NEXT(i+1,j+1)
 * <p>
 * 3. Base Case:
 * Good base case: i==S1.length&&j==S2.length
 * Bad base case: i==S1.length||j==S2.length only one of index is reach the end, but the other one doesn't
 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("head", "sandy"));
    }

    public static int minDistance(String word1, String word2) {
        return matchWithDP(word1, word2);
        //return matchWithMemo(word1, word2, 0, 0, new int[word1.length()][word2.length()]);
        //return match(word1, word2, 0, 0);
    }

    public static int match(String s1, String s2, int i, int j) {
        if (i == s1.length())
            return s2.length() - j;
        else if (j == s2.length())
            return s1.length() - i;
        else {
            if (s1.charAt(i) == s2.charAt(j))
                //no need to add 1 to match as we do not have operation if we matched at current level
                return match(s1, s2, i + 1, j + 1);
            else {
                //s1.charAt(i) != s2.charAt(j) need additional operation
                int insert = match(s1, s2, i, j + 1);
                int replace = match(s1, s2, i + 1, j + 1);
                int delete = match(s1, s2, i + 1, j);
                return Math.min(Math.min(insert, replace), delete) + 1;
            }
        }
    }

    //as we have abstractly a model from i,j to i+1,j+1 according to conditions, we need a 2d matrix to store the info
    public static int matchWithMemo(String s1, String s2, int i, int j, int[][] memo) {
        if (i == s1.length())
            return s2.length() - j;
        else if (j == s2.length())
            return s1.length() - i;

        if (memo[i][j] != 0) //if we already calculate this part just return it directly
            return memo[i][j];
        else {
            int res = 0;
            if (s1.charAt(i) == s2.charAt(j))
                //no need to add 1 to match as we do not have operation if we matched at current level
                res = matchWithMemo(s1, s2, i + 1, j + 1, memo);
            else {
                int insert = matchWithMemo(s1, s2, i, j + 1, memo);
                int replace = matchWithMemo(s1, s2, i + 1, j + 1, memo);
                int delete = matchWithMemo(s1, s2, i + 1, j, memo);
                res = Math.min(Math.min(insert, replace), delete) + 1;
            }
            memo[i][j] = res;
            return res;
        }
    }

    //to DP solution
    //match(i,j) -> s1(0,i),s2(0,j) matched
    //match[i,j] -> s1(0,i),s2(0,j) min steps to match
    public static int matchWithDP(String s1, String s2) {
        if (s1 == null || s1.length() == 0)
            return s2.length();
        if (s2 == null || s2.length() == 0)
            return s1.length();

        int[][] matched = new int[s1.length() + 1][s2.length() + 1];

        //ATTENTION: in DP, sometimes, we initial the base case by setting special case to 1 ahead of time, and in the for loop we start at i=1 and do arr[i]=arr[i-1]blablabla
        //sometimes, like this dp solution, we initial the default 0 when make a new array or matrix, and in the for loop we start at i=0, but do arr[i+1]=arr[i]
        for (int i = 0; i <= s1.length(); i++)
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    matched[i][j] = j;
                else if (j == 0)
                    matched[i][j] = i;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    //should calculate matched[i+1][j+1] rather than matched[i][j] at this point as we already know the base matched[0][0]=0;
                    matched[i][j] = matched[i - 1][j - 1];
                else
                    matched[i][j] = Math.min(matched[i - 1][j - 1], Math.min(matched[i][j - 1], matched[i - 1][j])) + 1;
            }
        return matched[s1.length()][s2.length()];
    }
}
