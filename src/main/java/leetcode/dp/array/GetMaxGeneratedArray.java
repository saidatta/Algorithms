package leetcode.dp.array;

/**
 * https://leetcode.com/problems/get-maximum-in-generated-array/description/
 */
public class GetMaxGeneratedArray {
    public int getMaximumGenerated(int n) {

        // edge cases
        if (n < 2) return n;
        int[] dp = new int[n+1];
        int m = Integer.MIN_VALUE;

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 != 0) {
                dp[i] = dp[i / 2] + dp[i / 2 + 1];
            } else {
                dp[i] = dp[i / 2];
            }
            m = Math.max(dp[i], m);
        }
        return m;
    }
}
