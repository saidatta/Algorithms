package Leetcode.array.search;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/
 */
public class LastDayToCross {
    public int latestDayToCross(int rows, int cols, int[][] cells) {
        // Create a graph to store when each cell is covered with water
        int[][] graph = new int[rows][cols];
        // Create a visited array for DFS traversal
        boolean[] visited = new boolean[rows * cols];

        // Populate the graph
        for (int i = 0; i < cells.length; i++) {
            graph[cells[i][0] - 1][cells[i][1] - 1] = i + 1;
        }

        int left = 1, right = rows * cols + 1;

        // Binary search to find the last day it is possible to cross
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            Arrays.fill(visited, false);

            boolean pathExists = false;
            // Try to find a path from top to bottom for this day
            for (int j = 0; j < cols; j++) {
                if (dfs(graph, mid, 0, j, rows, cols, visited)) {
                    left = mid;
                    pathExists = true;
                    break;
                }
            }

            // If no path was found, try with fewer days
            if (!pathExists) {
                right = mid;
            }
        }

        return left;
    }

    // Depth-First Search (DFS) traversal to check if it is possible to cross from top to bottom
    boolean dfs(int[][] graph, int targetDay, int i, int j, int rows, int cols, boolean[] visited) {
        // Check if the cell is within the grid, hasn't been visited and is not covered by water
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i * cols + j] || graph[i][j] <= targetDay) {
            return false;
        }
        // If we've reached the bottom row, a path exists
        if (i == rows - 1) {
            return true;
        }

        // Mark the cell as visited
        visited[i * cols + j] = true;

        // Try to find a path in each of the four directions
        return dfs(graph, targetDay, i + 1, j, rows, cols, visited)
                || dfs(graph, targetDay, i, j - 1, rows, cols, visited)
                || dfs(graph, targetDay, i - 1, j, rows, cols, visited)
                || dfs(graph, targetDay, i, j + 1, rows, cols, visited);
    }

    public static void main(String[] args) {
        LastDayToCross solution = new LastDayToCross();

        int row1 = 2, col1 = 2;
        int[][] cells1 = {{1,1}, {2,1}, {1,2}, {2,2}};
        System.out.println(solution.latestDayToCross(row1, col1, cells1));  // Output: 2

        int row2 = 2, col2 = 2;
        int[][] cells2 = {{1,1}, {1,2}, {2,1}, {2,2}};
        System.out.println(solution.latestDayToCross(row2, col2, cells2));  // Output: 1

        int row3 = 3, col3 = 3;
        int[][] cells3 = {{1,2}, {2,1}, {3,3}, {2,2}, {1,1}, {1,3}, {2,3}, {3,2}, {3,1}};
        System.out.println(solution.latestDayToCross(row3, col3, cells3));  // Output: 3
    }
}
