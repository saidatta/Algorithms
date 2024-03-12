package leetcode.graph.scc;

// https://leetcode.com/problems/critical-connections-in-a-network/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import leetcode.array.grid.traversal.DiagonalTraverseII.Pair;

// https://leetcode.com/problems/critical-connections-in-a-network/editorial/
public class CriticalConnectionsNetwork {
    private Map<Integer, List<Integer>> graph;
    private Map<Integer, Integer> rank;
    private Map<Pair<Integer, Integer>, Boolean> connDict;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.formGraph(n, connections);
        this.dfs(0, 0);

        List<List<Integer>> result = new ArrayList<>();
        for (Pair<Integer, Integer> criticalConnection : this.connDict.keySet()) {
            result.add(new ArrayList<>(Arrays.asList(criticalConnection.key(), criticalConnection.value())));
        }

        return result;
    }

    private int dfs(int node, int discoveryRank) {
        // That means this node is already visited. We simply return the rank.
        if (this.rank.get(node) != null) {
            return this.rank.get(node);
        }

        // Update the rank of this node.
        this.rank.put(node, discoveryRank);

        // This is the max we have seen till now. So we start with this instead of INT_MAX or something.
        int minRank = discoveryRank + 1;

        for (Integer neighbor : this.graph.get(node)) {
            // Skip the parent.
            Integer neighRank = this.rank.get(neighbor);
            if (neighRank != null && neighRank == discoveryRank - 1) {
                continue;
            }

            // Recurse on the neighbor.
            int recursiveRank = this.dfs(neighbor, discoveryRank + 1);

            // Step 1, check if this edge needs to be discarded.
            if (recursiveRank <= discoveryRank) {
                int sortedU = Math.min(node, neighbor), sortedV = Math.max(node, neighbor);
                this.connDict.remove(new Pair<>(sortedU, sortedV));
            }

            // Step 2, update the minRank if needed.
            minRank = Math.min(minRank, recursiveRank);
        }

        return minRank;
    }

    private void formGraph(int n, List<List<Integer>> connections) {
        this.graph = new HashMap<>();
        this.rank = new HashMap<>();
        this.connDict = new HashMap<>();

        // Default rank for unvisited nodes is "null"
        for (int i = 0; i < n; i++) {
            this.graph.put(i, new ArrayList<>());
            this.rank.put(i, null);
        }

        for (List<Integer> edge : connections) {
            // Bidirectional edges
            int u = edge.get(0), v = edge.get(1);
            this.graph.get(u).add(v);
            this.graph.get(v).add(u);

            int sortedU = Math.min(u, v), sortedV = Math.max(u, v);
            connDict.put(new Pair<>(sortedU, sortedV), true);
        }
    }
}

//Your detailed explanation offers a comprehensive understanding of how to identify bridges (critical connections) in
// an undirected graph using a modified Depth-First Search (DFS) approach. Let's break down the key aspects:
//
//        ### Understanding the Problem:
//        - **Goal:** Identify bridges in an undirected graph.
//        - **Bridge:** An edge whose removal disconnects the graph.
//- **Cut Node:** A node whose removal increases the number of connected components.
//
//        ### Key Concepts:
//        1. **Tarjan's Algorithm:** A standard approach for finding articulation points and bridges in a graph, using
//        DFS and discovery times. Not the focus here, but a related concept.
//        2. **Cycle Detection:** In an undirected graph, any edge part of a cycle is not a critical connection.
//        3. **Rank Concept:** A DFS-based approach to identify and exclude cycle edges.
//
//        ### Approach: Modified DFS for Cycle Detection
//1. **Graph Representation:** Use an adjacency list for efficient graph representation.
//2. **Ranking Nodes:** Assign a rank to each node during DFS, starting with 0 for the root node. Unvisited nodes have a rank of null/None.
//3. **Cycle Detection with Rank:**
//        - A cycle is detected if a node with a rank lower than the current node is encountered.
//        - Use the rank to determine if an edge is part of a cycle.
//4. **Discarding Edges:** Edges part of a cycle are not critical connections and can be discarded.
//
//### Algorithm Steps:
//        1. **Initialize Data Structures:** Graph (`graph`), connections dictionary (`connDict`), and rank array.
//2. **DFS Function (`dfs`):**
//        - Assign rank to each node.
//   - For each neighbor of the current node:
//        - Skip parent node (detected by rank).
//        - Perform DFS recursively and get `recursiveRank`.
//        - If `recursiveRank` ≤ current node’s rank, discard the edge.
//        - Track the minimum rank encountered (`minRank`).
//        - Return `minRank`.
//
//        3. **Execute DFS:** Start DFS from node 0 with rank 0.
//        4. **Result Preparation:** Convert remaining edges in `connDict` to a list.
//
//### Complexity Analysis:
//        - **Time Complexity:** O(V+E), where V is the number of vertices and E is the number of edges. The algorithm processes each node and edge once.
//        - **Space Complexity:** O(E), accounting for `connDict`, rank array, and graph structure.
//
//        ### Conclusion:
//This approach leverages the DFS algorithm with a novel ranking system to efficiently identify critical connections in an undirected graph. It's an elegant solution that circumvents the need to memorize complex algorithms like Tarjan's, focusing instead on fundamental DFS concepts and their creative application to solve graph-related problems.