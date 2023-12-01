package leetcode.dp.string;

// https://leetcode.com/problems/delete-operation-for-two-strings/
public class DeleteOperationTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j] will store the length of the (Longest Common Subsequence) LCS of substrings
        // word1[0...i-1] and word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Building the dp table in row-by-row order
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Case 1: Characters match, increment LCS length by 1 from previous indices
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Case 2: Characters don't match, take the max from either removing the current
                    // character from consideration from word1 or word2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Calculate the minimum number of deletions required
        // m + n - 2 * dp[m][n]: Total length - 2 times the length of LCS
        // where m and n refer to the lengths of s1 and s2.
        // dp[m][n] now refers to the length of LCS among the two given strings.
        return m + n - 2 * dp[m][n];
    }


    public static void main(String[] args) {
        DeleteOperationTwoStrings solution = new DeleteOperationTwoStrings();
        String word1 = "sea", word2 = "eat";
        System.out.println(solution.minDistance(word1, word2));  // Output: 2
        word1 = "leetcode";
        word2 = "etco";
        System.out.println(solution.minDistance(word1, word2));  // Output: 4
    }
}
