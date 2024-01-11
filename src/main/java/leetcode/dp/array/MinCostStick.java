package leetcode.dp.array;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
public class MinCostStick {
    /**
     * Calculates the minimum cost to make all cuts on a rod.
     *
     * @param rodLength The length of the rod.
     * @param cuts An array of integers where each integer represents a position on the rod to be cut.
     * @return The minimum cost to make all the cuts.
     */
    public int minCost(int rodLength, int[] cuts) {
        int numCuts = cuts.length;
        int[] sortedCuts = new int[numCuts + 2];
        System.arraycopy(cuts, 0, sortedCuts, 1, numCuts);
        sortedCuts[numCuts + 1] = rodLength;
        Arrays.sort(sortedCuts);

        int[][] dp = new int[numCuts + 2][numCuts + 2];

        // Iterate over possible lengths of sub-rods
        for (int length = 2; length < numCuts + 2; ++length) {
            for (int left = 0; left < numCuts + 2 - length; ++left) {
                int right = left + length;
                int minCost = Integer.MAX_VALUE;

                // Find the minimum cost for cutting the current sub-rod
                for (int mid = left + 1; mid < right; ++mid) {
                    int cost = dp[left][mid] + dp[mid][right] + sortedCuts[right] - sortedCuts[left];
                    minCost = Math.min(minCost, cost);
                }

                dp[left][right] = minCost;
            }
        }

        // Return the minimum cost to cut the whole rod
        return dp[0][numCuts + 1];
    }

    public static void main(String[] args) {
        MinCostStick minCostStick = new MinCostStick();
        // Example rod length and cut positions
        int rodLength = 10;
        int[] cuts = {1, 3, 4, 5};

        // Creating an instance of Solution class and calculating the minimum cost
        int minCost = minCostStick.minCost(rodLength, cuts);

        // Printing the result
        System.out.println("The minimum cost to make all the cuts is: " + minCost);
    }
}

