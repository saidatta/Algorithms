package Leetcode.dp.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        if(cost.length == 1) {
            return cost[0];
        }

        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);

//The cost to reach the previous step (dp[i-1]) plus the cost to climb from there to the current step (cost[i])
//The cost to reach the second previous step (dp[i-2]) plus the cost to climb from there to the current step (cost[i-1])
        for(int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i], dp[i-2] + cost[i-1]);
        }

        return dp[cost.length - 1];
    }
}
