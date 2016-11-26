package Advance;

/**
 * Created by rliu on 11/24/16.
 * Climbing Stairs
 * Given a stair case with n steps to reach the top
 * each time you have 2 options: go up 1 or 2 steps
 * calculate the number of distinct ways you can climb to the top from the bottom
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int stairsHeight = 5;
        System.out.println(distinct(stairsHeight));
    }

    public static int distinct(int stairHeight) {
        if (stairHeight <= 0)
            return 0;
        if (stairHeight == 1)
            return 1;

        int pre = 1;
        int cur = 2;

        for (int i = 3; i <= stairHeight; i++) {
            int temp = cur;
            cur = cur + pre;
            pre = temp;
        }
        return cur;

    }

}
