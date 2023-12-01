package leetcode.array.grid.simulation;

// https://leetcode.com/problems/check-knight-tour-configuration/
public class CheckKnightTour {

    // All 8 possible moves of a knight
    static final int[][] KNIGHT_MOVES = {
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    // Main function to check if the given grid represents a valid knight's tour
    public boolean checkValidGrid(int[][] grid) {
        int totalRows = grid.length, totalCols = grid[0].length;
        boolean[][] visited = new boolean[totalRows][totalCols];

        // Start the knight's tour validation from the starting point (0,0) with move number 0
        validateKnightTour(grid, visited, 0, 0, 0, totalRows, totalCols);

        // Check if all cells were visited during the tour
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                if (!visited[i][j]) {
                    return false; // If any cell is not visited, the tour is invalid
                }
            }
        }

        return true; // All cells were visited, so the tour is valid
    }

    // Recursive function to validate knight's tour starting from (v1, v2) for the given move number
    private void validateKnightTour(int[][] grid,
                                   boolean[][] visited,
                                   int row, int col, int move,
                                   int totalRows, int totalCols) {
        // Boundary and validity checks
        if (row < 0 || col < 0 || row >= totalRows || col >= totalCols || visited[row][col] || grid[row][col] != move) {
            return;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Recursively validate for all 8 knight moves
        for (int[] moveOffset : KNIGHT_MOVES) {
            int newRow = row + moveOffset[0];
            int newCol = col + moveOffset[1];

            validateKnightTour(grid, visited,
                    newRow,
                    newCol,
                    move + 1,
                    totalRows, totalCols);
        }
    }

    public static void main(String[] args) {
        CheckKnightTour solution = new CheckKnightTour();
        int[][] grid1 = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        System.out.println(solution.checkValidGrid(grid1)); // Output: true

        int[][] grid2 = {{0,3,6},{5,8,1},{2,7,4}};
        System.out.println(solution.checkValidGrid(grid2)); // Output: false
    }
}
