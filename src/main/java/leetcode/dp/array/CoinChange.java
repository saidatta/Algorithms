package leetcode.dp.array;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/
public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        // initialize the array with a large value
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins0 = {1,3,4,5};
        int amount0 = 7;
        System.out.println(coinChange(coins0, amount0));  // Expected output: 3

        int[] coins1 = {1,2,5};
        int amount1 = 11;
        System.out.println(coinChange(coins1, amount1));  // Expected output: 3

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(coinChange(coins2, amount2));  // Expected output: -1

        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println(coinChange(coins3, amount3));  // Expected output: 0
    }

}
