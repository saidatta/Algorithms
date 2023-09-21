package leetcode.array.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/jump-game-iv/
public class JumpGameIV {
    public static int minJumps(int... arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }

        // Create a map of value to list of indices.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.putIfAbsent(arr[i], new ArrayList<>());
            graph.get(arr[i]).add(i);
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                // If it's the last index, return the steps.
                if (index == n - 1) {
                    return steps;
                }

                List<Integer> neighbors = graph.get(arr[index]);
                neighbors.add(index - 1);
                neighbors.add(index + 1);

                for (int nextIndex : neighbors) {
                    if (nextIndex >= 0 && nextIndex < n && !visited.contains(nextIndex)) {
                        visited.add(nextIndex);
                        queue.offer(nextIndex);
                    }
                }
                neighbors.clear(); // Clear to make sure we don't use these indices again.
            }
            steps++;
        }
        return -1; // Return -1 if no solution found, but this shouldn't happen with given constraints.
    }

    public static void main(String [] args) {
        System.out.println(minJumps(100,-23,-23,404,100,23,23,23,3,404));
        System.out.println(minJumps(7,6,9,6,9,6,9,7));
    }
}
