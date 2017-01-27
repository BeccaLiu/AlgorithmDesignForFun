package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 1/22/17.
 * 97. Interleaving String
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class InterLeavingString {
    public static void main(String[] args) {
        //System.out.println(isInterleaveDP("aabcc", "dbbca", "aadbbcbcac"));
        //System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleaveDP("aa", "ab", "aaba"));

    }

    //if(s1(i)==s3(k)) DFS(i+1,j,k+1)
    //if(s2(j)==s3(k)) DFS(i,j+1,k+1)
    //if(s1(i)!=s3(k)&&s2(j)!=s3(k)) return false
    //Base case: if(i==s1.len&&j==s2.len&&k=s3.len) return true
    //
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        if (s1.equals(s3) || s2.equals(s3))
            return true;
//        return isInterleaveRec(s1,s2,s3,0,0,0);
        int[][] valid = new int[s1.length() + 1][s2.length() + 1];
        for (int[] i : valid)
            Arrays.fill(i, -1);
        boolean res = isInterleaveMemo(s1, s2, s3, 0, 0, 0, valid);
        System.out.println("with memo");
        for (int[] i : valid)
            System.out.println(Arrays.toString(i));
        return res;
    }

    public static boolean isInterleaveRec(String s1, String s2, String s3, int i, int j, int k) {
        if (i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        boolean validS1 = false;
        boolean validS2 = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k))
            validS1 = isInterleaveRec(s1, s2, s3, i + 1, j, k + 1);
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k))
            validS2 = isInterleaveRec(s1, s2, s3, i, j + 1, k + 1);
        return validS1 || validS2;
    }

    //then we might think there are some duplicated work
    // k is always moving afterward and i+j=k,so we only need 1 2d array, and for arr[i][j] means if s1(0,i) s2(0,j) s3(0,i+j) is interleave
    //
    public static boolean isInterleaveMemo(String s1, String s2, String s3, int i, int j, int k, int[][] valid) {
        if (valid[i][j] != -1)
            return valid[i][j] == 1;

        if (i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        boolean validS1 = false;
        boolean validS2 = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k))
            validS1 = isInterleaveMemo(s1, s2, s3, i + 1, j, k + 1, valid);
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k))
            validS2 = isInterleaveMemo(s1, s2, s3, i, j + 1, k + 1, valid);
        valid[i][j] = validS1 || validS2 == true ? 1 : 0;
        return validS1 || validS2;
    }

    //dp solution:
    //in the rec call, we have three factor we are consider, which is i,j,k,
    // we can represent the duplicate dp as one 3d matrix dp[][][] and dp[i][j][k]=dp[i][j-1][k-1]||dp[i-1][j][k-1]||s1(i)==s3(k)||s2(j)==s3(k)
    // next step is initial array
    // the meaning of dp[i][j] : s1(0,i) s1(0,j) s3(0,i+j-2) is interleave
    // dp[i][j] = dp[i-1][j] is matched and we want to match s1 with s3
    //          or dp[i][j-a] is matched and we want to match s2 with s3
    //   0 d b b b c a
    // 0
    // a    ex ->db is matched &&match(a,dba) ||ad is matched&&match()
    // a
    // b
    // c
    // c
    public static boolean isInterleaveDP(String s1, String s2, String s3) {
        //boolean[] valid = new boolean[s2.length() + 1];
        //valid[0]=true;
        //if we using one array, we do save the space but we need set each item in array to either true or false which increase the time complexity
        //for i==0 and j==0 initial the value with s1 match s3 or s2 mathc s3
        boolean[][] valid = new boolean[s1.length() + 1][s2.length() + 1];
        valid[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            if (i == 0) { //initial
                for (int j = 1; j <= s2.length(); j++)
                    // valid[j] = valid[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    if (valid[0][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                        valid[0][j] = true;
            }
            for (int j = 0; i != 0 && j < s2.length() + 1; j++) {
                if (j == 0) { //initial
                    // valid[j] = valid[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    if (valid[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        valid[i][j] = true;
                } else {
                    //valid[j-1] means s1(0,i) s2(0,j-1) is match with s3 ,we want to know if s2(0,j) is continually matched with s3
                    //valid[j] means s1(0,i-1) s2(0,j) is match with s3, we want ot know if s1(0,i) is match with s3
                    //valid[j] = valid[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) || valid[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    if (valid[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) || valid[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        valid[i][j] = true;

                }
            }
            System.out.println(Arrays.toString(valid[i]));
        }
        return valid[s1.length()][s2.length()];
    }

    //conclusion, sometime recursion with memo is better than DP
    //TODO: I am so confused about whay these happened, even using 2d array try to not modify the array to true or false by only reset it to true, is not solving problem
    /*
    here is the output
[true, false, false, false, false, false]
[true, false, false, false, false, false]
[true, true, true, true, false, false]
[false, true, true, true, false, false]
[false, false, false, false, false, false]
[false, false, false, false, false, false]
false

with memo
-1 is where we even do not take look at it, which means using recursion with dp, we do not need to go through the whole array, we just set and retrieve the info we want as we need
[0, -1, -1, -1, -1, -1]
[0, -1, -1, -1, -1, -1]
[0, 0, 0, 0, -1, -1]
[-1, 0, 0, 0, -1, -1]
[-1, -1, -1, -1, -1, -1]
[-1, -1, -1, -1, -1, -1]
false
     */

}
