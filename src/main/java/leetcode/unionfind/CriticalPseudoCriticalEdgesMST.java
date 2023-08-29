package leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/
public class CriticalPseudoCriticalEdgesMST {
    static class UnionFind {
        private final int[] parent;
        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        public int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            parent[rootX] = rootY;
            return true;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            edges[i] = new int[]{edges[i][0], edges[i][1], edges[i][2], i};
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        int value = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                value += edge[2];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            // Compute the MST excluding the current edge
            int v = 0;
            int t = 0;
            uf = new UnionFind(n);
            for (int j = 0; j < m; j++) {
                if (i != j && uf.union(edges[j][0], edges[j][1])) {
                    v += edges[j][2];
                    t++;
                }
            }
            if (t != n - 1 || v > value) {
                ans.get(0).add(edges[i][3]);
                continue;
            }

            // Compute the MST including the current edge
            uf = new UnionFind(n);
            uf.union(edges[i][0], edges[i][1]);
            v = edges[i][2];
            t = 1;
            for (int j = 0; j < m; j++) {
                if (i != j && uf.union(edges[j][0], edges[j][1])) {
                    v += edges[j][2];
                    t++;
                }
            }
            if (t == n - 1 && v == value) {
                ans.get(1).add(edges[i][3]);
            }
        }

        return ans;
    }
}
