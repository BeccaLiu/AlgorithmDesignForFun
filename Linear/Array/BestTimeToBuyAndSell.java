package Linear.Array;

/**
 * Created by rliu on 2/4/17.
 * 121. Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSell {
    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int low = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < low)
                low = prices[i];
            int profit = prices[i] - low;
            if (profit > max)
                max = profit;
        }
        return max;

    }

}
