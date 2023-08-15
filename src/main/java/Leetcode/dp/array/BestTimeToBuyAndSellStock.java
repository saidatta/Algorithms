package Leetcode.dp.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?tab=Description
 *
 * Created by venkatamunnangi on 3/2/17.
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int buy = prices[0];
        int profit = 0;

        for(int i = 1; i< prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            profit = Math.max(profit, prices[i] - buy);
        }

        return profit;
    }


    public static void main(String [] args) {
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        int[] prices = {103,102,101,100,1};
        System.out.println(bestTimeToBuyAndSellStock.maxProfit(prices));
    }
}
