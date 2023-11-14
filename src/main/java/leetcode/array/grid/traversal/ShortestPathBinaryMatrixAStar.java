package leetcode.array.grid.traversal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// A*
// https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class ShortestPathBinaryMatrixAStar {
    // 8 directions.
    private static final int[][] directions = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

        // Set up the A* search.
        Queue<Candidate> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.totalEstimate));
        pq.add(new Candidate(0, 0, 1, estimate(0, 0, grid)));

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // Carry out the A* search.
        while (!pq.isEmpty()) {

            Candidate best = pq.remove();

            // Is this for the target cell?
            if (best.row == grid.length - 1 && best.col == grid[0].length - 1) {
                return best.distanceSoFar;
            }

            // Have we already found the best path to this cell?
            if (visited[best.row][best.col]) {
                continue;
            }

            visited[best.row][best.col] = true;

            for (int[] neighbour : getNeighbours(best.row, best.col, grid)) {
                    int neighbourRow = neighbour[0];
                    int neighbourCol = neighbour[1];

                // This check isn't necessary for correctness, but it greatly
                // improves performance.
                if (visited[neighbourRow][neighbourCol]) {
                    continue;
                }

                // Otherwise, we need to create a Candidate object for the option
                // of going to neighbor through the current cell.
                int newDistance = best.distanceSoFar + 1;
                int totalEstimate = newDistance + estimate(neighbourRow, neighbourCol, grid);
                Candidate candidate = new Candidate(neighbourRow, neighbourCol, newDistance, totalEstimate);
                pq.add(candidate);
            }
        }
        // The target was unreachable.
        return -1;
    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours;
    }

    // Get the best case estimate of how much further it is to the bottom-right cell.
    private int estimate(int row, int col, int[][] grid) {
        int remainingRows = grid.length - row - 1;
        int remainingCols = grid[0].length - col - 1;
        return Math.max(remainingRows, remainingCols);
    }

    // Candidate represents a possible option for travelling to the cell
    // at (row, col).
    static class Candidate {
        public int row;
        public int col;
        public int distanceSoFar;
        public int totalEstimate;

        public Candidate(int row, int col, int distanceSoFar, int totalEstimate) {
            this.row = row;
            this.col = col;
            this.distanceSoFar = distanceSoFar;
            this.totalEstimate = totalEstimate;
        }
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrixAStar solution = new ShortestPathBinaryMatrixAStar();

        // Example 1: Path exists
        int[][] grid1 = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0}
        };
        System.out.println("Shortest Path (Example 1): " + solution.shortestPathBinaryMatrix(grid1));

        // Example 2: No path exists
        int[][] grid2 = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 1}
        };
        System.out.println("Shortest Path (Example 2): " + solution.shortestPathBinaryMatrix(grid2));

        // Example 3: Direct path
        int[][] grid3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Shortest Path (Example 3): " + solution.shortestPathBinaryMatrix(grid3));
    }
}
