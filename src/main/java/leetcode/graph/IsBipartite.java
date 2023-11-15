package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/is-graph-bipartite/
public class IsBipartite {

//     Depth First Search (DFS) or Breadth First Search (BFS) traversal can be used to determine if the graph
//    is bipartite. The idea is to color nodes using two colors (often represented as 1 and -1 or 0 and 1) and make
//    sure that neighboring nodes have different colors. If at any point neighboring nodes have the same color, the
//    graph is not bipartite.
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // 0 means not visited, 1 is one color, -1 is another color
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            // this is to make sure we cover disjoint component graphs
            if (colors[i] == 0 && !validColor(graph, colors, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean validColor(int[][] graph, int[] colors, int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        colors[node] = 1; // 1 or -1 colors.

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int adjacent : graph[current]) {
                if (colors[adjacent] == colors[current]) {
                    return false;
                }
                if (colors[adjacent] == 0) {
                    // Not visited.
                    queue.offer(adjacent);
                    colors[adjacent] = -colors[current];
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsBipartite solution = new IsBipartite();

        int[][] graph1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println(solution.isBipartite(graph1));  // Expected output: false

        int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(solution.isBipartite(graph2));  // Expected output: true
    }
}

