package leetcode.dp.game;

// https://www.youtube.com/watch?v=vlsUUm_qqsY
// https://leetcode.com/problems/knight-dialer/editorial/ - S
public class KnightDialer {
    private static final int MOD = 1_000_000_007;
    public int knightDialer(int n) {
        int[][] dp = new int[n][10];
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }

        // dp that land in with each digit.
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][4] + dp[i - 1][6]) % MOD;
            dp[i][1] = (dp[i - 1][6] + dp[i - 1][8]) % MOD;
            dp[i][2] = (dp[i - 1][7] + dp[i - 1][9]) % MOD;
            dp[i][3] = (dp[i - 1][4] + dp[i - 1][8]) % MOD;
            dp[i][4] = ((dp[i - 1][0] + dp[i - 1][3]) % MOD + dp[i - 1][9]) % MOD;
            dp[i][6] = ((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][7]) % MOD;
            dp[i][7] = (dp[i - 1][2] + dp[i - 1][6]) % MOD;
            dp[i][8] = (dp[i - 1][1] + dp[i - 1][3]) % MOD;
            dp[i][9] = (dp[i - 1][2] + dp[i - 1][4]) % MOD;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[n - 1][i]) % MOD;
        }
        return sum;
    }

    public static void main(String[] args) {
        KnightDialer knightDialer = new KnightDialer();
        System.out.println(knightDialer.knightDialer(1));  // Output: 10
        System.out.println(knightDialer.knightDialer(2));  // Output: 20
        System.out.println(knightDialer.knightDialer(3131));  // Output: 136006598
    }
}

