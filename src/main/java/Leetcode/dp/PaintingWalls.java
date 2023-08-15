package Leetcode.dp;

import java.util.Arrays;

public class PaintingWalls {

//    Either we add this task to the previous tasks, adding its cost and time to the total, or
//    we ignore this task, keeping the cost and time the same as before. By iterating over each task and updating the
//    dp array in these two ways, we can find the minimum cost to paint all the walls.
    public static int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, 1000000000);
        }
        dp[0][0] = 0;

        //cost
        for (int i = 0; i < n; i++) {
            //time
            for (int j = 0; j <= n; j++) {
                int paidPainterFree = Math.min(n, j + time[i] + 1);
                int paidPainterCost = Math.min(dp[i+1][paidPainterFree], dp[i][j] + cost[i]);
                dp[i+1][paidPainterFree] = paidPainterCost;

                int freePainterCost = Math.min(dp[i+1][j], dp[i][j]);
                dp[i+1][j] = freePainterCost;
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        int[] cost1 = {1, 2, 3, 2};
        int[] time1 = {1, 2, 3, 2};
        System.out.println(paintWalls(cost1, time1));  // Output: 3

        int[] cost2 = {2, 3, 4, 2};
        int[] time2 = {1, 1, 1, 1};
        System.out.println(paintWalls(cost2, time2));  // Output: 4
    }
}
