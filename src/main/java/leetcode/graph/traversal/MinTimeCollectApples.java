package leetcode.graph.traversal;

import java.util.*;

// https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/description/
public class MinTimeCollectApples {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // Convert edge list to adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // DFS to calculate time
        return dfs(0, -1, graph, hasApple);
    }

    private int dfs(int node, int parent, List<List<Integer>> graph, List<Boolean> hasApple) {
        int time = 0;
        for (int child : graph.get(node)) {
            if (child != parent) {
                time += dfs(child, node, graph, hasApple);
            }
        }
        if ((time > 0 || hasApple.get(node)) && node != 0) {
            time += 2; // Add time for this edge
        }
        return time;
    }

    public static void main(String[] args) {
        MinTimeCollectApples solution = new MinTimeCollectApples();
        int n = 7;
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = Arrays.asList(false,false,true,false,true,true,false);
        System.out.println(solution.minTime(n, edges, hasApple));  // Output: 8
    }
}
