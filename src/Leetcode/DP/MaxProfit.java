package Leetcode.DP;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?tab=Description
 *
 * Created by venkatamunnangi on 3/2/17.
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int buy = prices[0], profit = 0;

        for(int i = 1; i< prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            profit = Math.max(profit, prices[i] - buy);
        }

        return profit;
    }

}
