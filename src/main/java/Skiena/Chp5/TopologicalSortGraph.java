package Skiena.Chp5;

import java.util.LinkedList;
import java.util.Stack;

import static java.lang.System.*;

/**
 * Topological Sort is the linear ordering of a directed graph's nodes such that
 * for every edge from node u to node v, u comes before v in the ordering
 *
 * Time Complexity: O(|V| + |E|)
 *
 * Created by venkatamunnangi on 1/2/17.
 */
public class TopologicalSortGraph {
    private int V; // no. of vertices
    private LinkedList<Integer>[] adjacentNodes;

    public TopologicalSortGraph(int v) {
        V = v;
        adjacentNodes = new LinkedList[V];
        for(int i = 0; i< V;++i) {
            adjacentNodes[i] = new LinkedList<>();
        }
    }

    void addEdge(int x, int y) {
        adjacentNodes[x].add(y);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            // unnecessary. kept for logical and readability reasons.
            visited[i] = false;
        }

        for(int i = 0; i<V;i++) {
            if(!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.empty()) {
            out.print(stack.pop() + " ");
        }
    }

    private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (Integer v1 : adjacentNodes[vertex]) {
            if (!visited[v1]) {
                topologicalSortUtil(v1, visited, stack);
            }
        }

        stack.push(vertex);
    }

    // Driver method
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        TopologicalSortGraph g = new TopologicalSortGraph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        out.println("Following is a Topological " +
                "sort of the given graph");
        g.topologicalSort();
    }
}
