package leetcode.math.geometry;

// https://leetcode.com/problems/number-of-corner-rectangles/
public class NumCornerRectangles {
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int cornerPairs = 0;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        cornerPairs++;
                    }
                }
                // nc2 - combinations. n!/((n-2)!n!) - pick 2 points from a sequence of points.
                count += cornerPairs * (cornerPairs - 1) / 2;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumCornerRectangles solution = new NumCornerRectangles();
        int[][] grid1 = {{1, 0, 0, 1, 0}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 1}};
        int[][] grid2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] grid3 = {{1, 1, 1, 1}};

        System.out.println(solution.countCornerRectangles(grid1));  // Output: 1
        System.out.println(solution.countCornerRectangles(grid2));  // Output: 9
        System.out.println(solution.countCornerRectangles(grid3));  // Output: 0
    }
}
