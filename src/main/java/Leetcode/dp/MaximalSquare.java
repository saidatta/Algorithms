package Leetcode.dp;

/**
 * https://leetcode.com/problems/maximal-square/#/description
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }

        // dp(i, j) represents the length of the square
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }

        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1 ; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    // Here, if you encounter a rectangle in somehow, then you the recursive mins
                    // will return 0s
                    int checkForSquareOnes =  Math.min(Math.min(dp[i][j-1] , dp[i-1][j-1]), dp[i-1][j]);
                    dp[i][j] = checkForSquareOnes + 1;
                    result = Math.max(dp[i][j], result); // update result
                }
            }
        }
        return result*result;
    }
}
