package leetcode.array.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/making-a-large-island/
public class MakingLargeIsland {

    private int[][] grid;
    private int n;

    // Directions arrays for: up, down, left, right.
    private static final int[] DIRECTIONS_X = {-1, 1, 0, 0};
    private static final int[] DIRECTIONS_Y = {0, 0, -1, 1};

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;

        int maxArea = 0;
        // Assigning unique negative IDs for different islands.
        int islandId = -1;
        List<Integer> areasOfIslands = new ArrayList<>();

        // Identify and mark each island, and store its area.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = markIslandWithBFS(i, j, islandId--);
                    areasOfIslands.add(area);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        // Evaluate potential new island sizes by changing water to land.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // This is water.
                    maxArea = Math.max(maxArea, calculatePotentialIslandSize(i, j, areasOfIslands));
                }
            }
        }

        return maxArea;
    }

    private int markIslandWithBFS(int startX, int startY, int islandId) {
        int islandSize = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        grid[startX][startY] = islandId;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            islandSize++;

            for (int dir = 0; dir < 4; dir++) {
                int newX = curr[0] + DIRECTIONS_X[dir];
                int newY = curr[1] + DIRECTIONS_Y[dir];

                if (isValidPosition(newX, newY) && grid[newX][newY] == 1) {
                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = islandId;
                }
            }
        }

        return islandSize;
    }

    private int calculatePotentialIslandSize(int x, int y, List<Integer> areasOfIslands) {
        Set<Integer> adjacentIslands = new HashSet<>();
        int potentialSize = 1;  // The current water cell turned into land.

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + DIRECTIONS_X[dir];
            int newY = y + DIRECTIONS_Y[dir];

            if (isValidPosition(newX, newY) && grid[newX][newY] < 0 && !adjacentIslands.contains(grid[newX][newY])) {
                adjacentIslands.add(grid[newX][newY]);
                potentialSize += areasOfIslands.get(-grid[newX][newY] - 1);
            }
        }

        return potentialSize;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        // Sample grid (0: water, 1: land)
        int[][] grid = {
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };

        MakingLargeIsland solver = new MakingLargeIsland();
        int largestIslandSize = solver.largestIsland(grid);

        System.out.println("Largest possible island size: " + largestIslandSize);
    }
}
