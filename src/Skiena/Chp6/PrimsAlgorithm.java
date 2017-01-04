package Skiena.Chp6;

/**
 * Created by venkatamunnangi on 1/3/17.
 */
public class PrimsAlgorithm {

    // Number of vertices in the graph.
    private static final int noOfVertices = 5;

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[noOfVertices];

        //Key values used to pick minimum weight edge in cut.
        int key[] = new int[noOfVertices];

        //To represnt set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[noOfVertices];

        for(int i = 0; i< noOfVertices;i++) {
            key[i] = Integer.MAX_VALUE;
        }

    }

}
