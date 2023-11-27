package leetcode.array.sorting;

import java.util.Arrays;
import java.util.Comparator;

// https://www.youtube.com/watch?v=d-B_gk_gJtQ
// https://leetcode.com/problems/two-city-scheduling/editorial/
public class TwoCityScheduling {

    public static int twoCitySchedCost(int[][] costs) {
        // Sort by cost difference between city A and city B
        Arrays.sort(costs, Comparator.comparingInt(a -> (a[0] - a[1])));

        int totalCost = 0;
        int n = costs.length / 2;

        // Send the first n people to city A and the rest to city B
        for (int i = 0; i < costs.length; i++) {
            totalCost += (i < n) ? costs[i][0] : costs[i][1];
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.println(twoCitySchedCost(costs)); // Expected Output: 110
    }
}

