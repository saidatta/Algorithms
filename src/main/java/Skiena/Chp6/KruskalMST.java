package Skiena.Chp6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.*;

/**
 * <p>
 * Find minimum spanning tree usinig Kruskals algorithm
 * <p>
 * Time complexity - O(ElogE)
 * Space complexity - O(E + V)
 * <p>
 * References
 * https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
 *
 * Created by venkatamunnangi on 1/3/17.
 */
public class KruskalMST {
    // Function to get the Minimum Spanning Tree using Kruskal's algorithm
    public List<Edge<Integer>> getKruskalMST(Graph<Integer> graph) {
        // Get all edges of the graph and sort them based on their weights
        List<Edge<Integer>> allEdges = graph.getAllEdges();
        allEdges.sort(new EdgeComparator());

        UnionFind unionFind = new UnionFind();

        // Initialize disjoint sets for all vertices in the graph
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            unionFind.makeSet(vertex.getId());
        }

        List<Edge<Integer>> resultingEdges = new ArrayList<>();
        for (Edge<Integer> edge : allEdges) {
            // Find the two vertices root components of the edge
            long root1 = unionFind.findParent(edge.getVertex1().getId());
            long root2 = unionFind.findParent(edge.getVertex2().getId());

            // Check if the vertices are in different sets to avoid cycles
            if (root1 != root2) {
                // Add the edge to the result and union the two sets
                resultingEdges.add(edge);
                unionFind.union(root1, root2);
            }
        }
        return resultingEdges;
    }

    // Comparator class for sorting edges based on their weights
    static class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge<Integer> e1, Edge<Integer> e2) {
            return Integer.compare(e1.getWeight(), e2.getWeight());
        }
    }
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        // vertex1 vertex2 weight.
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);
        KruskalMST mst = new KruskalMST();
        List<Edge<Integer>> result = mst.getKruskalMST(graph);
        for (Edge<Integer> edge : result) {
            out.println(edge.getVertex1() + " " + edge.getVertex2());
        }
    }
}
