package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 11/24/16.
 * Paint House I
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouseI {
    //min[i][k]=cost[i][k]+Math.min(min[i-1][])
    public static void main(String[] args) {
        int[][] cost = {{5, 8, 6},
                {19, 14, 13},
                {7, 5, 12},
                {14, 15, 17},
                {3, 20, 10}};
        System.out.println(paintHouseRec(cost));
        System.out.println(paintHouseDP(cost));

    }

    //0.Primitive: every time choose the smallest, but it is not work
    //1.using min[][] to store the min:
    //min[i][k]=cost[i][k]+Math.min(min[i-1][(k+1)%3],min[i-1][(k+2)%3])
    //2.you will discover, min[i][k] only depends on last level, which means you do not need using 2d array
    //Time O(3n)
    //space O(3)
    //3.online algorithm, if there are k color, will take O(kn) and space O(k)
    //4.every time we use track min and it is not work, so we may track secondMin O(1)
    //the primitive idea is not working is because when we choose the minimum of previous level, it may be the same color
    //curMin=Cost[i][k]+(lastColor==k?lastSecMin:lastMin)
    //time: O(kn)
    //space O(1)
    public static int paintHouseRec(int[][] cost) {
        if (cost == null || cost.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cost[0].length; i++) {
            int c = dfsHelper(cost, 0, i, new int[cost.length][cost[0].length]);
            if (c < min)
                min = c;
        }
        return min;
    }

    public static int dfsHelper(int[][] cost, int i, int j, int[][] saved) {
        if (i == cost.length)
            return 0;
        if (saved[i][j] != 0)
            return saved[i][j];
        return cost[i][j] + Math.min(dfsHelper(cost, i + 1, (j + 1) % 3, saved), dfsHelper(cost, i + 1, (j + 2) % 3, saved));
    }

    public static int paintHouseDP(int[][] cost) {
        if (cost == null || cost.length == 0)
            return 0;
        //can also create two array last[] and cur[] and then swap after process
        int[] total_cost = Arrays.copyOf(cost[0], 3);
        for (int i = 1; i < cost.length; i++) {
            int[] temp = Arrays.copyOf(total_cost, 3);
            for (int j = 0; j < cost[0].length; j++) {
                total_cost[j] = cost[i][j] + Math.min(temp[(j + 1) % 3], temp[(j + 2) % 3]);
            }
        }
        return Math.min(total_cost[0], Math.min(total_cost[1], total_cost[2]));
    }
}
