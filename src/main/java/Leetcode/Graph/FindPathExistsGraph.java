package Leetcode.Graph;

public class FindPathExistsGraph {
    private int[] parent;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        parent = new int[n];

        // Initialize parent array.
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Iterate over the edges and union the nodes connected by each edge.
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        // Return true if source and destination have the same parent (i.e., are connected), false otherwise.
        return find(source) == find(destination);
    }

    // Find function using path compression.
    private int find(int a) {
        // If the parent of a is not itself, update its parent to be its grandparent.
        while (parent[a] != a) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }

    // Union function using union by rank.
    private void union(int a, int b) {
        // Find the roots of a and b.
        int rootA = find(a);
        int rootB = find(b);

        // If the roots are not the same, set the parent of rootA to rootB.
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}
