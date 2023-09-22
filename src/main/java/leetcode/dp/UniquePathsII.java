package leetcode.dp;

/**
 * https://leetcode.com/problems/unique-paths-ii/#/description
 *
 * Created by venkatamunnangi on 3/24/17.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        int width = obstacleGrid[0].length;

        // dp will hold the number of unique paths to each position.
        int[] dp = new int[width];
        // Initialize starting point
        dp[0] = 1;

        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (isObstacle(row[j])) {
                    dp[j] = 0;
                } else if (j > 0) {
                    // Accumulate the number of unique paths.
                    // The current cell's value becomes the sum of the left cell and the above cell.
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[width - 1];
    }

    private boolean isObstacle(int value) {
        return value == 1;
    }
    public static void main(String[] args) {
        UniquePathsII solution = new UniquePathsII();

        // Test Case 1
        int[][] obstacleGrid1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid1)); // Expected output: 2

        // Test Case 2
        int[][] obstacleGrid2 = {
                {0,1},
                {0,0}
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid2)); // Expected output: 1

        // You can add more test cases as needed
    }
}
