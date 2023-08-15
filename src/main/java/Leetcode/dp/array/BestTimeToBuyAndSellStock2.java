package Leetcode.dp.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        // stores the total profit
        int profit = 0;

        // Iterate over the array, starting from the second element
        for(int i = 1; i < prices.length; i++) {
            // If the current price is greater than the previous price
            if(prices[i] > prices[i - 1]) {
                // Add the difference to the total profit
                profit += prices[i] - prices[i - 1];
            }
        }

        // Return the total profit
        return profit;
    }
}
