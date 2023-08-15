package Leetcode.dp;

/**
 * https://leetcode.com/problems/unique-paths-ii/#/description
 *
 * Created by venkatamunnangi on 3/24/17.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    // += is crucial as we dont need to do a 2d grid.
                    // we are adding the previous row at that location(up)
                    // with current rows left min ways (left).
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[width - 1];
    }

    public static void main(String [] args) {
        UniquePathsII uniquePathsII = new UniquePathsII();
    }
}
