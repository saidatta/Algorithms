package leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/tallest-billboard/description/
 */
public class TallestBillboard {

    // The goal
//    is to find the maximum possible height of a billboard constructed using a set of rods, where each rod can be used
//    as a positive or negative sign. The solution uses dynamic programming to keep track of the maximum achievable
//    height for each possible sum.
//
//    The approach involves iterating over the rods and updating the dynamic programming array (dp) based on the current
//    rod's height. For each rod, a copy of the dp array is created to ensure the updates do not interfere with
//    subsequent iterations. The dp array represents the maximum achievable height for each sum value from 0 to the
//    total sum of all rods.
//
//    The time complexity of the solution is O(N * S), where N is the number of rods and S is the sum of all rod heights
//    This is because we iterate over the rods in the outer loop, which takes O(N) time, and for each rod, we iterate
//    over the dp array, which has a maximum size of S+1. Therefore, the overall time complexity is O(N * S).
//
//    Space complexity:
//    The space complexity of the solution is O(S), where S is the sum of all rod heights. This is because we create a
//    dynamic programming array (dp) of size S+1 to store the maximum achievable height for each sum value.
//    Additionally, a temporary array (dpCopy) of the same size is created for each rod iteration. The space complexity
//    is primarily determined by the size of the dp array, which is proportional to the sum of all rod heights.

    public int tallestBillboard(int[] rods) {
        int sum = Arrays.stream(rods).sum();

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
//1 2 3 6; largest - 6
        for (int rod : rods) {
            int[] dpCopy = Arrays.copyOf(dp, sum + 1);

            for (int i = 0; i <= sum - rod; i++) {
                if (dpCopy[i] < 0) {
                    continue;
                }

                dp[i + rod] = Math.max(dp[i + rod], dpCopy[i]);
                dp[Math.abs(i - rod)] = Math.max(dp[Math.abs(i - rod)], dpCopy[i] + Math.min(i, rod));
            }
        }

        return dp[0];
    }
}
