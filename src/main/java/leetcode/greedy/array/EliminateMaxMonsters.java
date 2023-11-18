package leetcode.greedy.array;

import java.util.Arrays;

// https://leetcode.com/problems/eliminate-maximum-number-of-monsters/description/
public class EliminateMaxMonsters {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        double[] timeToCity = new double[n];

        // Calculate time for each monster to reach the city
        for (int i = 0; i < n; i++) {
            timeToCity[i] = (double) dist[i] / speed[i];
        }

        // Sort the times
        Arrays.sort(timeToCity);

        // Count how many monsters can be eliminated
        for (int i = 0; i < n; i++) {
            if (timeToCity[i] <= i) {
                return i; // Monster reached the city
            }
        }

        return n; // All monsters can be eliminated
    }

    public static void main(String[] args) {
        EliminateMaxMonsters solution = new EliminateMaxMonsters();
        System.out.println(solution.eliminateMaximum(new int[]{1,3,4}, new int[]{1,1,1})); // Output: 3
        System.out.println(solution.eliminateMaximum(new int[]{1,1,2,3}, new int[]{1,1,1,1})); // Output: 1
        System.out.println(solution.eliminateMaximum(new int[]{3,2,4}, new int[]{5,3,2})); // Output: 1
    }
}
