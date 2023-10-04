package leetcode.array.grid;

// https://leetcode.com/problems/island-perimeter/
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // Add 4 for each land cell
                    perimeter += 4;

                    // Subtract 2 for each adjacent pair vertically (if within grid boundaries)
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }

                    // Subtract 2 for each adjacent pair horizontally (if within grid boundaries)
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        IslandPerimeter solver = new IslandPerimeter();

        int[][] grid1 = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(solver.islandPerimeter(grid1));  // Outputs: 16

        int[][] grid2 = {{1}};
        System.out.println(solver.islandPerimeter(grid2));  // Outputs: 4

        int[][] grid3 = {{1, 0}};
        System.out.println(solver.islandPerimeter(grid3));  // Outputs: 4
    }
}
