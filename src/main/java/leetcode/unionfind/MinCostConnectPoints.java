package leetcode.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/min-cost-to-connect-all-points/description/
public class MinCostConnectPoints {
    int[] parent;


//    we can use Kruskal's algorithm for the minimum spanning tree. The main idea is to:
//
//    1. Calculate the Manhattan distance for every pair of points.
//    2. Sort all the distances.
//    3. Start adding edges (from the smallest distance to the largest) to the spanning tree as long as they don't form
//    a cycle. Use Union-Find to check for cycles and to merge trees.
//    4. Once all points are connected, the sum of the distances used is the answer.

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, dist));
            }
        }

        edges.sort(Comparator.comparingInt(a -> a.cost));

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int numEdges = 0;
        int result = 0;
        for (Edge edge : edges) {
            if (union(edge.x, edge.y)) {
                result += edge.cost;
                numEdges++;
                if (numEdges == n - 1) break;
            }
        }
        return result;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        parent[rootX] = rootY;
        return true;
    }

    static class Edge {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
