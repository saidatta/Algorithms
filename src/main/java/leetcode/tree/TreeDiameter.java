package leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/tree-diameter/description/
public class TreeDiameter {
    public int treeDiameter(int[][] edges) {
        List<List<Integer>> adjList = buildGraph(edges);

        // Start BFS from any node and find the farthest node.
        int arbitraryNode = edges[0][0];
        int[] farthestResult = bfs(adjList, arbitraryNode);

        // Start BFS from the farthest node found above to get the diameter.
        int farthestNode = farthestResult[1];
        int[] diameterResult = bfs(adjList, farthestNode);

        return diameterResult[0];
    }

    private List<List<Integer>> buildGraph(int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < edges.length + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }

    // Returns an array of 2 elements: the distance and the node at the max distance.
    private int[] bfs(List<List<Integer>> adjList, int startNode) {
        Set<Integer> visited = new HashSet<>();
        int distance = 0;  // start at 0 since we are going level by level.
        int lastVisitedNode = startNode;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                int currentNode = queue.poll();
                if (visited.add(currentNode)) {
                    lastVisitedNode = currentNode;
                    for (int neighbor : adjList.get(currentNode)) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
            if (!queue.isEmpty()) {
                // increment the distance for each level we process.
                distance++;
            }
        }
        return new int[]{distance, lastVisitedNode};
    }

    public class Solution {

        private int diameter = Integer.MIN_VALUE;

        /**
         * A recursive DFS method that calculates the maximum depth of subtrees and
         * the diameter of the tree.
         *
         * @param node     The current node being processed.
         * @param adjList  The adjacency list representation of the tree.
         * @param visited  Array to keep track of visited nodes.
         * @return The depth of the subtree rooted at 'node'.
         */
        private int computeDepthAndDiameter(int node, List<List<Integer>> adjList, boolean[] visited) {
            visited[node] = true;

            // The deepest child subtree
            int deepest = 0;
            // The second-deepest child subtree
            int secondDeepest = 0;

            for (Integer child : adjList.get(node)) {
                if (!visited[child]) {
                    int childDepth = computeDepthAndDiameter(child, adjList, visited) + 1;

                    if (childDepth >= deepest) {
                        secondDeepest = deepest;
                        deepest = childDepth;
                    } else if (childDepth > secondDeepest) {
                        secondDeepest = childDepth;
                    }
                }
            }

            diameter = Math.max(diameter, deepest + secondDeepest);
            return deepest;
        }

        /**
         * Calculates the diameter of the tree.
         *
         * @param edges  The given edges of the tree.
         * @return The diameter of the tree.
         */
        public int treeDiameter(int[][] edges) {
            int numNodes = edges.length;

            // Initialize the adjacency list.
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i <= numNodes; i++) {
                adjList.add(new ArrayList<>());
            }

            // Populate the adjacency list using edges.
            for (int[] edge : edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }

            boolean[] visited = new boolean[numNodes + 1];
            computeDepthAndDiameter(0, adjList, visited);

            return diameter;
        }
    }

}
