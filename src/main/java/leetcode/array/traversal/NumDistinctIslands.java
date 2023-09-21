package leetcode.array.traversal;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-distinct-islands
public class NumDistinctIslands {

    private int[][] grid;
    private boolean[][] visited;

    private static final char START = '0';
    private static final char DOWN = 'D';
    private static final char UP = 'U';
    private static final char RIGHT = 'R';
    private static final char LEFT = 'L';
    private static final char BACKTRACK = 'B';  // added backtrack indicator

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        Set<String> islands = new HashSet<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                StringBuilder currentIsland = new StringBuilder();
                dfs(row, col, START, currentIsland);
                if (currentIsland.length() > 0) {
                    islands.add(currentIsland.toString());
                }
            }
        }
        return islands.size();
    }

    private void dfs(int row, int col, char direction, StringBuilder currentIsland) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }
        if (visited[row][col] || grid[row][col] == 0) {
            return;
        }

        visited[row][col] = true;
        currentIsland.append(direction);

        dfs(row + 1, col, DOWN, currentIsland);
        dfs(row - 1, col, UP, currentIsland);
        dfs(row, col + 1, RIGHT, currentIsland);
        dfs(row, col - 1, LEFT, currentIsland);

        currentIsland.append(BACKTRACK);
    }

    public static void main(String[] args) {
        NumDistinctIslands solver = new NumDistinctIslands();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(solver.numDistinctIslands(grid1));  // Expected output: 1

        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println(solver.numDistinctIslands(grid2));  // Expected output: 3
    }
}
