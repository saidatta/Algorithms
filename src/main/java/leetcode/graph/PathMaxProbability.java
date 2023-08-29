package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/path-with-maximum-probability/description/
 */
class PathMaxProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Adjacency list
        List<List<Pair<Integer, Double>>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double p = succProb[i];
            adj.get(u).add(new Pair<>(v, p));
            adj.get(v).add(new Pair<>(u, p));
        }

        // Distances array
        double[] successProbPaths = new double[n];
        Arrays.fill(successProbPaths, 0.0);
        successProbPaths[start] = 1.0;

        // Queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Pair<Integer, Double> pair : adj.get(curr)) {
                int node = pair.getKey();
                double prob = pair.getValue();
                double probToReachNewNode = successProbPaths[curr] * prob;

                // if the success prob to reach this node is higher than its previous history, update the path.
                if (probToReachNewNode > successProbPaths[node]) {
                    successProbPaths[node] = probToReachNewNode;
                    queue.offer(node);
                }
            }
        }

        return successProbPaths[end];
    }

    private class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}