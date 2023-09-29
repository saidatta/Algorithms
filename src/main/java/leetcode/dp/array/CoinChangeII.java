package leetcode.dp.array;

// https://leetcode.com/problems/coin-change-ii/
public class CoinChangeII {
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1,2,5};
        int amount1 = 5;
        System.out.println(change(amount1, coins1));  // Expected output: 4

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(change(amount2, coins2));  // Expected output: 0

        int[] coins3 = {10};
        int amount3 = 10;
        System.out.println(change(amount3, coins3));  // Expected output: 1
    }

}
