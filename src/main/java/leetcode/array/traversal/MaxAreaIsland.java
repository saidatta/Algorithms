package leetcode.array.traversal;

public class MaxAreaIsland {
    private final int[][] grid;
    private final boolean[][] visitedCells;

    public MaxAreaIsland(int[][] grid) {
        this.grid = grid;
        this.visitedCells = new boolean[grid.length][grid[0].length];
    }

    /**
     * Computes the area of an island starting from a specific cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The area of the island.
     */
    private int computeAreaFromCell(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
                visitedCells[row][col] || grid[row][col] == 0) {
            return 0;
        }

        visitedCells[row][col] = true;

        return 1 + computeAreaFromCell(row + 1, col) + computeAreaFromCell(row - 1, col)
                + computeAreaFromCell(row, col - 1) + computeAreaFromCell(row, col + 1);
    }

    /**
     * Computes the maximum area of an island in the grid.
     *
     * @return The maximum area.
     */
    public int maxAreaOfIsland() {
        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                maxArea = Math.max(maxArea, computeAreaFromCell(row, col));
            }
        }

        return maxArea;
    }
}
