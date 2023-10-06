package leetcode.graph.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/all-paths-from-source-to-target/description/
class AllPathsSourceTarget {
    private int target;
    private int[][] graph;
    private Map<Integer, List<List<Integer>>> memo;

    protected List<List<Integer>> allPathsToTarget(int currNode) {
        // memorization. check the result in the cache first
        if (memo.containsKey(currNode)) {
            return memo.get(currNode);
        }

        List<List<Integer>> results = new ArrayList<>();
        // base case
        if (currNode == this.target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(target);
            results.add(path);
            return results;
        }

        // iterate through the paths starting from each neighbor.
        for (int nextNode : this.graph[currNode]) {
            for (List<Integer> path : allPathsToTarget(nextNode)) {
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(path);
                results.add(newPath);
            }
        }
        memo.put(currNode, results);
        return results;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        this.target = graph.length - 1;
        this.graph = graph;
        this.memo = new HashMap<>();

        return this.allPathsToTarget(0);
    }
}