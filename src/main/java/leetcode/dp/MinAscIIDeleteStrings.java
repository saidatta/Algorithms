package leetcode.dp;

/**
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * Time complexity: O(n∗m)O(n*m)O(n∗m) where nnn = size of first string, mmm = size of second string
 * Space complexity: O(n∗m)O(n*m)O(n∗m) where nnn = size of first string, mmm = size of second string
 */
public class MinAscIIDeleteStrings {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] + (int) s2.charAt(i - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + (int) s1.charAt(i - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                char a = s1.charAt(i - 1);
                char b = s2.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + (int) b, dp[i - 1][j] + (int) a);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
