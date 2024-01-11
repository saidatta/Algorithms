package leetcode.array.grid.traversal;

// https://leetcode.com/problems/path-with-maximum-minimum-value/description/
public class MaximumMinimumPath {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int left = 0, right = Math.min(grid[0][0], grid[m - 1][n - 1]);

        while (left < right) {
            // Use upper mid to prevent infinite loop
            int mid = (left + right + 1) >>> 1;
            if (canReachEnd(grid, mid, m, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canReachEnd(int[][] grid, int minVal, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, minVal, visited, m, n);
    }

    private boolean dfs(int[][] grid, int x, int y, int minVal, boolean[][] visited, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || grid[x][y] < minVal) {
            return false;
        }
        if (x == m - 1 && y == n - 1) {
            return true;
        }
        visited[x][y] = true;

        // Check all four directions
        return dfs(grid, x + 1, y, minVal, visited, m, n) ||
                dfs(grid, x - 1, y, minVal, visited, m, n) ||
                dfs(grid, x, y + 1, minVal, visited, m, n) ||
                dfs(grid, x, y - 1, minVal, visited, m, n);
    }
}
