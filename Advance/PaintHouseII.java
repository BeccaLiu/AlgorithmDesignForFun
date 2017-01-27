package Advance;

/**
 * Created by rliu on 1/26/17.
 * 265.Paint House II
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouseII {
    public static void main(String[] args) {

    }

    //the time complexity using the idea of dp paint house I is n*3*2, as each house, we need to check two of previous result
    //if we using the same strategy of previous method, the time complexity is n*k*(k-1)
    //the problem of the previous method is that we do not want to iterate the whole k-1 house to get the min
    //and we know that if we want to pain the house, we only need to know the min and second min, as one of them must not same color as current house
    //if we want to faster, we need to use the idea of greedy
    //and time complexity will be n*k*2 instead of n*k*(k-1)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        int lastMin = 0;
        int lastSecMin = 0;
        int lastMinIndex = -1;

        for (int i = 0; i < costs.length; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curIndex = -1;
            for (int j = 0; j < costs[0].length; j++) {
                int val = costs[i][j] + (j == lastMinIndex ? lastSecMin : lastMin);
                if (val < curMin) {
                    curSecMin = curMin;
                    curMin = val;
                    curIndex = j;
                } else if (val < curSecMin) {
                    curSecMin = val;
                }

            }
            lastMin = curMin;
            lastSecMin = curSecMin;
            lastMinIndex = curIndex;
        }
        return lastMin;
    }
}
