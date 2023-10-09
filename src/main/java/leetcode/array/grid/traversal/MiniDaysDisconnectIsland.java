package leetcode.array.grid.traversal;

/**
 * Solution for the problem: https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/description/
 */
public class MiniDaysDisconnectIsland {

    private final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    /**
     * Return minimum number of days after which there will be more than one island.
     */
    public int minDays(int[][] grid) {
        // If the current number of islands is not 1, return 0.
        if (numberOfIslands(grid) != 1) {
            return 0;
        }

        // Try removing each land cell and check if that disconnects the island.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    grid[row][col] = 0;
                    if (numberOfIslands(grid) != 1) {
                        return 1;
                    }
                    grid[row][col] = 1;  // Restore the cell
                }
            }
        }

        // If removing one cell doesn't disconnect the island, then 2 days are required.
        return 2;
    }

    /**
     * Returns the number of islands in the given grid.
     */
    private int numberOfIslands(int[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (!visited[row][col] && grid[row][col] == 1) {
                    depthFirstSearch(grid, row, col, visited);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Depth First Search to traverse and mark all cells of an island.
     */
    private void depthFirstSearch(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == 0) {
            return;
        }

        visited[row][col] = true;

        for (int[] direction : DIRECTIONS) {
            depthFirstSearch(grid, row + direction[0], col + direction[1], visited);
        }
    }
}
