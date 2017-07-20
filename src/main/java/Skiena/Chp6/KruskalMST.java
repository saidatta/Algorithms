package Skiena.Chp6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.*;

/**
 *         <p>
 *         Find minimum spanning tree usinig Kruskals algorithm
 *         <p>
 *         Time complexity - O(ElogE)
 *         Space complexity - O(E + V)
 *         <p>
 *         References
 *         https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
 *
 * Created by venkatamunnangi on 1/3/17.
 */
public class KruskalMST {

    class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge<Integer> e1, Edge<Integer> e2) {
            if(e1.getWeight() <= e2.getWeight()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /**
     *
     */
    public List<Edge<Integer>> getKruskalMST(Graph<Integer> graph) {
        List<Edge<Integer>> allEdges = graph.getAllEdges();

        Collections.sort(allEdges, new EdgeComparator());

        UnionFind unionFind = new UnionFind();

        //create all the vertex components through unionfind's make set.
        for(Vertex<Integer> vertex : graph.getAllVertex()) {
            unionFind.makeSet(vertex.getId());
        }

        List<Edge<Integer>> resultingEdges = new ArrayList<>();
        for(Edge<Integer> edge : allEdges) {
            //get the representative component sets of two vertices of the edge.
            long r1 = unionFind.findSet(edge.getVertex1().getId());
            long r2 = unionFind.findSet(edge.getVertex2().getId());

            //evaulate If vertices are in the same set or different set
            if(r1 == r2) {
                // if vertices are in the same set, then ignore the set
                // as it will result in a possible cycle.
            } else {
                // if vertices are in different set then add the edge to result and union these two sets into one.
                resultingEdges.add(edge);
                unionFind.union(r1,r2);
            }
        }
        return resultingEdges;
    }

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<Integer>(false);
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
