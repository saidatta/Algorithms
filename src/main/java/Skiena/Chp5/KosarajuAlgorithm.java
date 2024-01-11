package Skiena.Chp5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarajuAlgorithm {
    private final List<List<Integer>> graph;
    private final int numVertices;

    public KosarajuAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        graph = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        graph.get(source).add(destination);
    }

    private List<List<Integer>> getTranspose() {
        List<List<Integer>> transpose = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int v = 0; v < numVertices; v++) {
            for (int adjVertex : graph.get(v)) {
                transpose.get(adjVertex).add(v);
            }
        }
        return transpose;
    }

    private void fillOrder(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (int adjVertex : graph.get(vertex)) {
            if (!visited[adjVertex]) {
                fillOrder(adjVertex, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<List<Integer>> findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        List<List<Integer>> transpose = getTranspose();
        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        visited = new boolean[numVertices];

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfsUtil(v, visited, component, transpose);
                stronglyConnectedComponents.add(component);
            }
        }

        return stronglyConnectedComponents;
    }

    private void dfsUtil(int v, boolean[] visited, List<Integer> component, List<List<Integer>> transpose) {
        visited[v] = true;
        component.add(v);
        for (int adj : transpose.get(v)) {
            if (!visited[adj]) {
                dfsUtil(adj, visited, component, transpose);
            }
        }
    }

    public static void main(String[] args) {
        KosarajuAlgorithm kosaraju = new KosarajuAlgorithm(5);
        kosaraju.addEdge(1, 0);
        kosaraju.addEdge(0, 2);
        kosaraju.addEdge(2, 1);
        kosaraju.addEdge(0, 3);
        kosaraju.addEdge(3, 4);

        List<List<Integer>> sccs = kosaraju.findSCCs();
        for (List<Integer> scc : sccs) {
            System.out.println("SCC: " + scc);
        }
    }
}
