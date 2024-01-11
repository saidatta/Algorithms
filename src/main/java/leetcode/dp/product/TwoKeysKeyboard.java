package leetcode.dp.product;

// https://leetcode.com/problems/2-keys-keyboard/
public class TwoKeysKeyboard {
    public int minSteps(int n) {
        if (n == 1) return 0;

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i; // Maximum number of steps is i (1 copy and i-1 paste)
            for (int j = i - 1; j > 1; j--) {
                // If j is a divisor of i, it means we can form i by pasting j, i/j times
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }
        return dp[n];
    }
}

