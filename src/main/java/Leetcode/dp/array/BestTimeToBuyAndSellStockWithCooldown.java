package Leetcode.dp.array;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/#/description
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] cooldown = new int[n];

        // Initial conditions
        buy[0] = -prices[0];
        sell[0] = 0;
        cooldown[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], cooldown[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            cooldown[i] = Math.max(cooldown[i - 1], sell[i - 1]);
        }

        return Math.max(sell[n - 1], cooldown[n - 1]);
    }

    class Pool {
        private static final int MAX_AVAILABLE = 100;
        private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

        public Object getItem() throws InterruptedException {
            available.acquire();
            return getNextAvailableItem();
        }

        public void putItem(Object x) {
            if (markAsUnused(x)) {
                available.release();
            }
        }

        // Not a particularly efficient data structure; just for demo
        protected Object[] items = new Object[MAX_AVAILABLE]; //
        // whatever kinds of items being managed
        protected boolean[] used = new boolean[MAX_AVAILABLE];

        protected synchronized Object getNextAvailableItem() {
            for (int i = 0; i < 10; ++i) {
                if (!used[i]) {
                    used[i] = true;
                    return items[i];
                }
            }
            return null; //
        }

        protected synchronized boolean markAsUnused(Object item) {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (item == items[i]) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
    }
}
