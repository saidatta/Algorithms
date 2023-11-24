package leetcode.array.grid.traversal;

// https://leetcode.com/problems/number-of-enclaves/
class NumEnclaves {
    int m, n;

    public int numEnclaves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        // Mark boundary-reachable cells as visited
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) visit(i, 0, grid, visited);
            if (grid[i][m - 1] == 1) visit(i, m - 1, grid, visited);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) visit(0, j, grid, visited);
            if (grid[n - 1][j] == 1) visit(n - 1, j, grid, visited);
        }

        // Count the number of unvisited land cells
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void visit(int x, int y, int[][] grid, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= n || y >= m || grid[x][y] == 0 || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        visit(x - 1, y, grid, visited); // Left
        visit(x, y - 1, grid, visited); // Bottom
        visit(x + 1, y, grid, visited); // Right
        visit(x, y + 1, grid, visited); // Top
    }
}

