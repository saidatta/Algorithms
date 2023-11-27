package leetcode.dp.array;

// https://leetcode.com/problems/minimum-cost-for-tickets/
// https://www.youtube.com/watch?v=4pY1bsBpIY4
public class MinCostTickets {
    public int minCostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        boolean[] travelDays = new boolean[lastDay + 1];
        for (int day : days) {
            travelDays[day] = true;
        }

        // dp[i] represents the minimum cost to travel up to day i
        int[] dp = new int[lastDay + 1];

        for (int i = 1; i <= lastDay; i++) {
            if (!travelDays[i]) {
                // If not traveling on day i, cost remains the same as the previous day
                dp[i] = dp[i - 1];
                continue;
            }

            // Calculate costs for 1-day, 7-day, and 30-day passes
            int costForOneDay = dp[Math.max(0, i - 1)] + costs[0];
            int costForSevenDays = dp[Math.max(0, i - 7)] + costs[1];
            int costForThirtyDays = dp[Math.max(0, i - 30)] + costs[2];

            // Minimum of the three options on the ith day.
            dp[i] = Math.min(costForOneDay, Math.min(costForSevenDays, costForThirtyDays));
        }

        return dp[lastDay];
    }

    // Main method for testing
    public static void main(String[] args) {
        MinCostTickets solution = new MinCostTickets();

        int[] days1 = {1, 4, 6, 7, 8, 20};
        int[] costs1 = {2, 7, 15};
        System.out.println("Minimum cost for Example 1: " + solution.minCostTickets(days1, costs1)); // Expected output: 11

        int[] days2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = {2, 7, 15};
        System.out.println("Minimum cost for Example 2: " + solution.minCostTickets(days2, costs2)); // Expected output: 17
    }
}
