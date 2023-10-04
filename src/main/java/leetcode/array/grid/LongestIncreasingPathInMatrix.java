package leetcode.array.grid;

public class LongestIncreasingPathInMatrix {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        m = matrix.length;
        n = matrix[0].length;
        int result = 0;

        // Create a memoization table initialized with -1 (not visited)
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }

        // Start DFS from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] != -1) {
                    result = Math.max(result, dfs(matrix, i, j, memo));
                }
            }
        }

        return result;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // Return the stored result if this cell is already computed
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int maxLen = 1;
        for (int[] dir : DIRECTIONS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && matrix[newI][newJ] > matrix[i][j]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, newI, newJ, memo));
            }
        }

        // Store the result in the memo table and return
        memo[i][j] = maxLen;
        return maxLen;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix solver = new LongestIncreasingPathInMatrix();

        int[][] matrix1 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(solver.longestIncreasingPath(matrix1));  // Outputs: 4

        int[][] matrix2 = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(solver.longestIncreasingPath(matrix2));  // Outputs: 4

        int[][] matrix3 = {{1}};
        System.out.println(solver.longestIncreasingPath(matrix3));  // Outputs: 1
    }
}
