package Leetcode.Graph;

public class  IncreasingPathsGrid {
    private static final int MODULUS = 1000000007;
    private int[][] grid;
    private int[][] pathCounts;
    private int m, n;

    public int countPaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.pathCounts = new int[m][n];

        int totalPathCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalPathCount += search(i, j);
                totalPathCount %= MODULUS;
            }
        }
        return totalPathCount;
    }

    private int search(int i, int j) {
        if (pathCounts[i][j] != 0) {
            return pathCounts[i][j];
        }

        int pathCount = 1;
        int value = grid[i][j];
        if (i > 0 && grid[i - 1][j] > value) {
            pathCount += search(i - 1, j);
            pathCount %= MODULUS;
        }
        if (j > 0 && grid[i][j - 1] > value) {
            pathCount += search(i, j - 1);
            pathCount %= MODULUS;
        }
        if (i < m - 1 && grid[i + 1][j] > value) {
            pathCount += search(i + 1, j);
            pathCount %= MODULUS;
        }
        if (j < n - 1 && grid[i][j + 1] > value) {
            pathCount += search(i, j + 1);
            pathCount %= MODULUS;
        }

        pathCounts[i][j] = pathCount;

        return pathCount;
    }
}
