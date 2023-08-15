package Leetcode.dp.array;

public class BestTimeToBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        return maxProfit(2, prices);
    }

//    the rows represent the days and the columns represent the transactions.
//    The cell value at a given day and transaction represents the maximum profit that can be made using that many
//    transactions up to that day.
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // If k is greater than prices.length / 2, then this problem can be solved using the 'maximum profit with as
        // many transactions as you like' approach
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[][] dp = new int[k + 1][prices.length];
//        For each day, we calculate the maximum profit that we can make with t transactions up to day d. We either
//        choose not to do any transaction on the dth day, so the profit is the same as the profit on the (d-1)th day,
//        which is dp[t][d - 1]. Or we sell the stock on the dth day, in which case we need to find a day to buy the
//        stock that maximizes the profit.

//        This solution has a time complexity of O(nk) and a space complexity of O(nk), where n is the number of days
//        and k is the number of transactions.
        for (int txns = 1; txns <= k; txns++) {
            int maxDiff = -prices[0];
            for (int today = 1; today < prices.length; today++) {
                //// dont sell today vs sell today.
                dp[txns][today] = Math.max(dp[txns][today - 1], prices[today] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[txns - 1][today] - prices[today]);
            }
        }

        return dp[k][prices.length - 1];
    }
}
