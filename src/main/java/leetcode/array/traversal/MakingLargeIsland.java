package leetcode.array.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MakingLargeIsland {
    private int[][] grid;
    private int n;
    // These arrays represent the four directions: up, down, left, right
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;

        int maxArea = 0;
        int index = -1; // Assigning unique negative indices for different islands.
        List<Integer> islandAreas = new ArrayList<>();

        // Traverse through the grid and find all connected islands, mark them and store their areas.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = bfs(i, j, 1, index--);
                    islandAreas.add(area);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        // Check each water cell to see the effect of converting it to land.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] >= 0) {
                    Set<Integer> adjacentIslands = new HashSet<>();
                    int potentialArea = 1;

                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + dx[dir];
                        int y = j + dy[dir];

                        if (isValid(x, y) && grid[x][y] < 0 && !adjacentIslands.contains(grid[x][y])) {
                            adjacentIslands.add(grid[x][y]);
                            potentialArea += islandAreas.get(-grid[x][y] - 1);
                        }
                    }

                    maxArea = Math.max(maxArea, potentialArea);
                }
            }
        }

        return maxArea;
    }

    /**
     * A BFS traversal to find the area of the connected island starting at (x,y).
     *
     * @param x      The x-coordinate of the starting point.
     * @param y      The y-coordinate of the starting point.
     * @param target The value we want to search for (land cells).
     * @param mark   The value we will mark the visited cells with.
     * @return The number of cells on the island.
     */
    private int bfs(int x, int y, int target, int mark) {
        int area = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y});
        grid[x][y] = mark;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            area++;

            for (int dir = 0; dir < 4; dir++) {
                int newX = curr[0] + dx[dir];
                int newY = curr[1] + dy[dir];

                if (isValid(newX, newY) && grid[newX][newY] == target) {
                    queue.offer(new int[] {newX, newY});
                    grid[newX][newY] = mark;
                }
            }
        }

        return area;
    }

    /**
     * Checks whether the given coordinates are valid within the grid.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
