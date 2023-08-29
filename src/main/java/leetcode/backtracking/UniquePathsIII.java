package leetcode.backtracking;

// https://leetcode.com/problems/unique-paths-iii/description/
public class UniquePathsIII {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int startR = -1, startC = -1;
        int stepsCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    startR = r;
                    startC = c;
                } else if (grid[r][c] == 0) {
                    ++stepsCount;
                }
            }
        }

        return dfs(grid, startR, startC, stepsCount);
    }

    int dfs(int[][] grid, int r, int c, int stepsCount) {
        if (grid[r][c] == 2) {
            if (stepsCount == -1) {
                return 1;
            }
            return 0;
        }
        grid[r][c] = -1;
        int res = 0;
        for (int[] d : dirs) {
            int newR = r + d[0], newC = c + d[1];
            if (newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length
                    && (grid[newR][newC] == 0 || grid[newR][newC] == 2)) {
                res += dfs(grid, newR, newC, stepsCount - 1);
            }
        }
        grid[r][c] = 0;
        return res;
    }
}
