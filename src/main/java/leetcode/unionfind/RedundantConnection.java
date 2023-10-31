package leetcode.unionfind;

// https://leetcode.com/problems/redundant-connection/description/
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int rootA = find(parents, edge[0]);
            int rootB = find(parents, edge[1]);
            if (rootA == rootB) {
                return edge;
            }
            parents[rootA] = rootB;
        }
        throw new IllegalArgumentException("Input is not a valid graph.");
    }

    private int find(int[] parents, int node) {
        if (parents[node] != node) {
            parents[node] = find(parents, parents[node]);
        }
        return parents[node];
    }
}
