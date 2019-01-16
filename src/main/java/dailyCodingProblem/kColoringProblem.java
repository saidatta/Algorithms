package dailyCodingProblem;

import java.util.stream.IntStream;

public class kColoringProblem {
    private final int numOfVertices = 4;
    private int[] colors;

    private boolean isSafeColorAssignment(int v, int[][] graph, int[] color,
                                          int c) {
        return IntStream.range(0, numOfVertices).noneMatch(i -> graph[v][i] == 1 && c == color[i]);
    }

    private boolean graphColoringUtility(int[][] graph, int m,
                                         int[] color, int v) {
        /* base case: If all vertices are assigned
           a colors then return true */
        if (v == numOfVertices) {
            return true;
        }

        /* Consider this vertex v and try different
           colors */
        for (int c = 1; c <= m; c++) {
            /* Check if assignment of colors c to v
               is fine*/
            if (isSafeColorAssignment(v, graph, color, c)) {
                color[v] = c;

                /* recur to assign colors to rest
                   of the vertices */
                if (graphColoringUtility(graph, m,
                        color, v + 1)) {
                    return true;
                }

                /* If assigning colors c doesn't lead
                   to a solution then remove it */
                color[v] = 0;
            }
        }

        /* If no colors can be assigned to this vertex
           then return false */
        return false;
    }

    /* This function solves the m Coloring problem using
       Backtracking. It mainly uses graphColoringUtility()
       to solve the problem. It returns false if the m
       colors cannot be assigned, otherwise return true
       and  prints assignments of colors to all vertices.
       Please note that there  may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    public boolean graphColoring(int[][] graph, int m) {
        // Initialize all colors values as 0. This
        // initialization is needed correct functioning
        // of isSafeColorAssignment()
        colors = new int[numOfVertices];
        IntStream.range(0, numOfVertices).forEach(i -> colors[i] = 0);

        // Call graphColoringUtility() for vertex 0
        if (!graphColoringUtility(graph, m, colors, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        // Print the solution
        printSolution(colors);
        return true;
    }

    /* A utility function to print solution */
    void printSolution(int[] color) {
        System.out.println("Solution Exists: Following" +
                " are the assigned colors");
        for (int i = 0; i < numOfVertices; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
    }

    // driver program to test above function
    public static void main(String[] args) {
        kColoringProblem coloring = new kColoringProblem();
        /* Create following graph and test whether it is
           3 colorable
          (3)---(2)
           |   / |
           |  /  |
           | /   |
          (0)---(1)
        */
        int [][] graph = new int[][]{new int[]{0, 1, 1, 1},
                new int[]{1, 0, 1, 0},
                new int[]{1, 1, 0, 1},
                new int[]{1, 0, 1, 0},
        };
        int m = 3; // Number of colors
        coloring.graphColoring(graph, m);
    }
}
