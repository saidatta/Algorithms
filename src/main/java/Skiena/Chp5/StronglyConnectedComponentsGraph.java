package Skiena.Chp5;

import java.util.LinkedList;
import java.util.Stack;

/**
 * A directed graph is strongly connected if there is a path between all pairs of vertices. A strongly connected
 * component (SCC) of a directed graph is a maximal strongly connected subgraph.
 * For example, there are 3 SCCs in the following graph (1,2,0) , 3, 4
 *
 *   1 -> 0 -> 3
 *   \(1->2)   \(3->4)
 *   2          4
 *
 * We can find all strongly connected components in O(V+E) time using Kosaraju’s algorithm.
 *
 * Following is detailed Kosaraju’s algorithm.
 *
 * 1) Create an empty stack ‘S’ and do dfs traversal of a graph. In dfs traversal, after calling
 *    recursive dfs for adjacent vertices of a vertex, push the vertex to stack.
 *    In the above graph, if we start dfs from vertex 0, we get vertices in stack as 1, 2, 4, 3, 0.
 * 2) Reverse directions of all arcs to obtain the transpose graph.
 * 3) One by one pop a vertex from S while S is not empty. Let the popped vertex be ‘v’.
 *    Take v as source and do dfs (call DFSUtil(v)). The dfs starting from v prints strongly connected component of v.
 *    In the above example, we process vertices in order 0, 3, 4, 2, 1 (One by one popped from stack).
 *
 *
 * Why transpose is necessary for this kosaraju algorithm.
 * http://cs.stackexchange.com/questions/19652/kosaraju-s-algorithm-why-transpose
 *
 * Time Complexity: The above algorithm calls dfs, fins reverse of the graph and again calls dfs.
 * dfs takes O(V+E) for a graph represented using adjacency list.
 * Reversing a graph also takes O(V+E) time. For reversing the graph, we simple traverse all adjacency lists.
 *
 * Created by venkatamunnangi on 1/2/17.
 */
public class StronglyConnectedComponentsGraph {
    private int noOfVertices;
    private LinkedList<Integer> adjacentNodes[];

    public StronglyConnectedComponentsGraph(int v) {
        this.noOfVertices = v;
        adjacentNodes = new LinkedList[noOfVertices];

        for(int i = 0; i< noOfVertices; i++) {
            adjacentNodes[i] = new LinkedList<>();
        }
    }

    private void addEdge(int v, int w) {
        adjacentNodes[v].add(w);
    }


    public void printSCCs() {
        Stack<Integer> stack = new Stack<Integer>();

        boolean visited[] = new boolean[noOfVertices];

        for(int i = 0; i< noOfVertices;i++) {
            if(!visited[i]) {
                fillOrder(i, stack, visited);
            }
        }

        StronglyConnectedComponentsGraph transposedGraph = getTranspose();

        //mark everything as false again so that we make sure that transppose
        // is able to reach all the vertices that were reachable from the original graph.
        for(int i = 0;i< visited.length; i++) {
            visited[i] = false;
        }

        StronglyConnectedComponentsGraph graph = getTranspose();

        while(!stack.empty()) {
            int vertex = stack.pop();

            if(!visited[vertex]) {
                graph.dfsUtil(vertex, visited);
                System.out.println();
            }
        }
    }

    private void fillOrder(int vertex, Stack<Integer> stack, boolean [] visited) {
        visited[vertex] = true;

        for(int v1 : adjacentNodes[vertex]) {
            if(!visited[v1]) {
                fillOrder(v1, stack, visited);
            }
        }

        stack.push(vertex);
    }

    private StronglyConnectedComponentsGraph getTranspose() {
        StronglyConnectedComponentsGraph graph = new StronglyConnectedComponentsGraph(noOfVertices);

        for(int i = 0;i<noOfVertices;i++) {
            for(int v1 : adjacentNodes[i]) {
                graph.addEdge(v1, i);
            }
        }
        return graph;
    }

    private void dfsUtil(int vertex, boolean visited[]) {
        visited[vertex] = true;
        System.out.println(vertex + " ");

        for(int i : adjacentNodes[vertex]) {
            if(!visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    // Driver method
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        StronglyConnectedComponentsGraph g = new StronglyConnectedComponentsGraph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components "+
                "in given graph ");
        g.printSCCs();
    }
}
