package leetcode.dp.string;

// https://leetcode.com/problems/strange-printer/
public class StrangePrinter {
    public static void main(String[] args) {
        System.out.println(strangePrinter("aaabbb"));  // 2
        System.out.println(strangePrinter("aba"));     // 2
    }
//    We will maintain a 2D dp array where dp[i][j] will represent the minimum number of turns to print the string from
//    index i to j. The idea is to initialize dp[i][i] as 1 as we need only one turn to print a single character.
//    Then for each length from 2 to n, calculate the minimum number of turns for every substring of that length.
//
//    For every pair (i, j), we will have two cases:
//
//    If s[i] == s[j], the answer will be the same as the minimum number of turns needed to print the string from i to
//    j - 1.
//    If s[i] != s[j], we will try every possible partition from i to j - 1 and take the partition that yields the
//    minimum number of turns.
    public static int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = 1 + dp[i + 1][j];
                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + (k < j ? dp[k + 1][j] : 0));
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
