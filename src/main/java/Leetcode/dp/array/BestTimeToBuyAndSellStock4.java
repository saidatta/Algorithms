package Leetcode.dp.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/#/description
 *
 * Created by venkatamunnangi on 3/3/17.
 */
public class BestTimeToBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) {
            return quickSolve(prices);
        }

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            // tmpMax means maximum profit of just doing i-1 transactions, using at most first j-1 prices.
            // and buying the stock at price[j]
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                int prevProfitWithSameTransaction = t[i][j - 1];
                int profitFormula = prices[j] + tmpMax; // curr price - buy.
                // profit of current transaction with current price.
                t[i][j] = Math.max(prevProfitWithSameTransaction, profitFormula);
                tmpMax =  Math.max(tmpMax, t[i - 1][j] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        return profit;
    }

    public static void main(String [] args) {
        BestTimeToBuyAndSellStock4 mp4 = new BestTimeToBuyAndSellStock4();
        int [] prices = {7,1,5,3,6,4};
        System.out.println(mp4.maxProfit(1, prices));
    }
}
