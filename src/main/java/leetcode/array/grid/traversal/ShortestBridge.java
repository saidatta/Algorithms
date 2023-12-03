package leetcode.array.grid.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int FOUND_ISLAND = 2;

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Use DFS to find and mark the first island
        for (int i = 0; i < n; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                    break;
                }
            }
        }

        // Step 2: Use BFS to expand the first island and find the shortest path to the second island
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cell = queue.remove();
                for (int[] dir : DIRECTIONS) {
                    int x = cell[0] + dir[0], y = cell[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < n && y < n) {
                        if (grid[x][y] == 1) {
                            return steps;
                        } else if (grid[x][y] == 0) {
                            // flipped island.
                            grid[x][y] = 2; // Mark as visited
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
            steps++;
        }

        return -1; // Should not reach here if input is valid
    }

    // DFS to mark the first island and add its perimeter to the queue
    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid.length || grid[i][j] != 1) return;
        grid[i][j] = FOUND_ISLAND; // Mark as part of the first island
        queue.add(new int[]{i, j});
        dfs(grid, i + 1, j, queue);
        dfs(grid, i - 1, j, queue);
        dfs(grid, i, j + 1, queue);
        dfs(grid, i, j - 1, queue);
    }

    public static void main(String[] args) {
        ShortestBridge solution = new ShortestBridge();
        int[][] grid1 = {{0, 1}, {1, 0}};
        System.out.println(solution.shortestBridge(grid1)); // Output: 1

        int[][] grid2 = {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        System.out.println(solution.shortestBridge(grid2)); // Output: 2
    }
}
