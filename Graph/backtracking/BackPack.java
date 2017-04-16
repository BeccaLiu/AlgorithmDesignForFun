package Graph.backtracking;

import java.util.Arrays;

/**
 * Created by rliu on 4/15/17.
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 * eg If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
 */
public class BackPack {
    public static void main(String[] args) {
        System.out.println(backPack(10, new int[]{3, 4, 8, 5}));
    }


    public static int backPack(int m, int[] A) {
        // write your code here
//        Arrays.sort(A);
//        int[] max=new int[1];
//        backPacksubset(m,A,new int[1],max,0);
//        return max[0];
        Arrays.sort(A);
        int[][] dp = new int[A.length][m + 1]; //store the max size we can fill when backpack size is m
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i >= A[0] ? A[0] : 0;
        }

        for (int j = 1; j < A.length; j++) {
            for (int i = 1; i <= m; i++) {
                dp[j][i] = i - A[j] >= 0 ? Math.max(dp[j - 1][i - A[j]] + A[j], dp[j - 1][i]) : dp[j - 1][i];
            }
        }
        return dp[A.length - 1][m];
    }

    //this will exceed the limited of time, as the time complexity is 2^N which N is the size of A
    public static void backPacksubset(int m, int[] A, int[] size, int[] max, int idx) {

        max[0] = Math.max(max[0], size[0]);
        for (int i = idx; i < A.length; i++) {
            if (size[0] + A[i] > m)
                break;
            size[0] += A[i];
            backPacksubset(m, A, size, max, i + 1);
            size[0] -= A[i];
        }
    }
}
