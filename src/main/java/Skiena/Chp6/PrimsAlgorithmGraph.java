package Skiena.Chp6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by venkatamunnangi on 1/3/17.
 */
public class PrimsAlgorithmGraph {
    // adj array
    private final ArrayList<Pair<Integer, Integer>>[] adjList;
    private final int V;

    public PrimsAlgorithmGraph(int n) {
        V = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int x, int y, int w) {
        adjList[x].add(new Pair<>(w, y));
        adjList[y].add(new Pair<>(w, x));
    }

    public int primMST() {
        // Min Heap
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::weight));

        boolean[] visited = new boolean[V];
        int ans = 0;

        pq.add(new Pair<>(0, 0)); // weight, node

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> best = pq.poll();

            int to = best.toVertex();
            int weight = best.weight();

            if (visited[to]) {
                continue;
            }

            ans += weight;
            visited[to] = true;

            for (Pair<Integer, Integer> edge : adjList[to]) {
                if (!visited[edge.weight()]) {
                    pq.add(new Pair<>(edge.toVertex(), edge.weight()));
                }
            }
        }
        return ans;
    }

    private record Pair<K, V>(K weight, V toVertex) { }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        PrimsAlgorithmGraph g = new PrimsAlgorithmGraph(n);
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int w = scanner.nextInt();
            g.addEdge(x - 1, y - 1, w);
        }

        System.out.println(g.primMST());
    }
}
