package leetcode.array.grid.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ShortestPathBinaryMatrixBFS {
    private static final int[][] directions =
            new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        int currentDistance = 1;

        // Carry out the BFS
        while (!queue.isEmpty()) {

            // Process all nodes at the current distance from the top-left cell.
            int nodesAtCurrentDistance = queue.size();
            for (int i = 0; i < nodesAtCurrentDistance; i++) {
                int[] cell = queue.remove();
                int row = cell[0];
                int col = cell[1];
                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    return currentDistance;
                }
                for (int[] neighbour : getNeighbours(row, col, grid)) {
                    int neighbourRow = neighbour[0];
                    int neighbourCol = neighbour[1];
                    if (visited[neighbourRow][neighbourCol]) {
                        continue;
                    }
                    visited[neighbourRow][neighbourCol] = true;
                    queue.add(new int[]{neighbourRow, neighbourCol});
                }
            }
            // We'll now be processing all nodes at current_distance + 1
            currentDistance++;
        }

        // The target was unreachable.
        return -1;
    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours;
    }
}
