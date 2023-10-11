package leetcode.graph.shortest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
public class CheapestFlightsKStops {

    private static final int NO_PATH = Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Initialize distance array to store the shortest distance from src to every node.
        int[] distancesFromSrc = new int[n];
        Arrays.fill(distancesFromSrc, NO_PATH);
        distancesFromSrc[src] = 0;

        // Loop only K+1 times as we want the shortest distance with at most K stops.
        for (int stops = 0; stops <= k; stops++) {
            // Create a copy of distances to compute the shortest path for current iteration.
            int[] currentStopsDist = Arrays.copyOf(distancesFromSrc, n);
            for (int[] flight : flights) {
                int start = flight[0];
                int end = flight[1];
                int weight = flight[2];

                if (distancesFromSrc[start] != NO_PATH) {
                    currentStopsDist[end] = Math.min(currentStopsDist[end], distancesFromSrc[start] + weight);
                }
            }
            distancesFromSrc = currentStopsDist;
        }

        // If destination is unreachable, return -1. Otherwise, return the shortest distance.
        return distancesFromSrc[dst] == NO_PATH ? -1 : distancesFromSrc[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsKStops solution = new CheapestFlightsKStops();

        int n = 4;
        int[][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };
        int src = 0;
        int dst = 3;
        int k = 1;

        int result = solution.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("Cheapest price from " + src + " to " + dst + " with at most " + k + " stops is: " + result);
    }
}

// alternate, dijkstras, was slower than above Bellman-Ford
class Solution {

    /**
     * This method finds the cheapest flight route with a limited number of stops.
     * @param n The number of cities.
     * @param flights The list of available flights.
     * @param src The source city.
     * @param dst The destination city.
     * @param k The maximum number of stops allowed.
     * @return The cheapest cost for the flight. If there is no flight route available, return -1.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // Build adjacency list to represent the graph.
        Map<Integer, List<int[]>> graph = createGraph(flights);

        // Array to keep track of stops taken to reach each node.
        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

        // Priority queue to keep track of nodes to explore.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Each element of the queue is an array: {current_cost, current_node, stops_from_source}.
        pq.offer(new int[] { 0, src, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int costSoFar = current[0];
            int currentNode = current[1];
            int stopsSoFar = current[2];

            // If we've seen this node with fewer stops before, or stops exceed the limit, continue.
            if (stopsSoFar > stops[currentNode] || stopsSoFar > k + 1)
                continue;

            // Update stops for the current node.
            stops[currentNode] = stopsSoFar;

            // If destination is reached, return the cost.
            if (currentNode == dst)
                return costSoFar;

            // Explore neighbors.
            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int nextNode = neighbor[0];
                    int edgeCost = neighbor[1];
                    pq.offer(new int[] { costSoFar + edgeCost, nextNode, stopsSoFar + 1 });
                }
            }
        }

        return -1;
    }

    /**
     * Helper method to create a graph (in the form of an adjacency list).
     * @param flights The list of available flights.
     * @return The graph in the form of an adjacency list.
     */
    private Map<Integer, List<int[]>> createGraph(int[][] flights) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int start = flight[0];
            int end = flight[1];
            int cost = flight[2];
            graph.computeIfAbsent(start, key -> new ArrayList<>()).add(new int[]{end, cost});
        }
        return graph;
    }
}
