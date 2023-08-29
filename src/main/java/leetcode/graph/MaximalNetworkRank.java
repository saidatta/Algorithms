package leetcode.graph;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/maximal-network-rank/description/
public class MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degrees = new int[n];
        Set<String> set = new HashSet<>();

        for (int[] road : roads) {
            degrees[road[0]]++;
            degrees[road[1]]++;
            set.add(road[0] + "," + road[1]); // Store roads in set for O(1) lookups
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degrees[i] + degrees[j];
                if (set.contains(i + "," + j) || set.contains(j + "," + i)) {
                    rank--;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    public static void main(String[] args) {
        MaximalNetworkRank solver = new MaximalNetworkRank();

        int[][] roads1 = {{0,1},{0,3},{1,2},{1,3}};
        int[][] roads2 = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};

        System.out.println(solver.maximalNetworkRank(5, roads1)); // Expected output: 4
        System.out.println(solver.maximalNetworkRank(8, roads2)); // Expected output: 5
    }
}
