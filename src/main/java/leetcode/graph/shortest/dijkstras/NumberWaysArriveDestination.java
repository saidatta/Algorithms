package leetcode.graph.shortest.dijkstras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
public class NumberWaysArriveDestination {
    private static final int MOD = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {
        List<List<NodeWithDistance>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            adjList.get(road[0]).add(new NodeWithDistance(road[1], road[2]));
            adjList.get(road[1]).add(new NodeWithDistance(road[0], road[2]));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<NodeWithDistance> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.distance));
        pq.offer(new NodeWithDistance(0, 0));

        int[] ways = new int[n];
        ways[0] = 1;

        while (!pq.isEmpty()) {
            NodeWithDistance current = pq.poll();

            if (current.distance > dist[current.node]) {
                continue;
            }

            for (NodeWithDistance neighbor : adjList.get(current.node)) {
                long newDist = current.distance + neighbor.distance;
                int newNode = neighbor.node;

                if (newDist < dist[newNode]) {
                    dist[newNode] = newDist;
                    ways[newNode] = ways[current.node];
                    pq.offer(new NodeWithDistance(newNode, newDist));
                } else if (newDist == dist[newNode]) {
                    ways[newNode] = (ways[newNode] + ways[current.node]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }

    private record NodeWithDistance(int node, long distance) { }
}

//    Graph Representation: We use a HashMap where each key is a node, and the value is a list of arrays representing the neighbor node and the time to reach that neighbor.
//        Dijkstra's Algorithm: We use Dijkstra's algorithm to find the shortest time to reach each intersection from intersection 0. We keep track of the distance (time) to each node and the number of ways to reach each node in the shortest time.
//        Priority Queue: A priority queue is used to process nodes in order of increasing time.
//        Updating Distances and Ways: When a shorter path to a node is found, we update the distance and the number of ways. If a path with equal length is found, we add the number of ways to reach the current node to the ways of reaching the next node, modulo 1_000_000_007 to handle large numbers.
//        Result: The result is the number of ways to reach intersection n - 1 in the shortest time.
