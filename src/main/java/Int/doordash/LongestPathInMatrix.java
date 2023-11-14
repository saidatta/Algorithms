package Int.doordash;

public class LongestPathInMatrix {

    private static int dfs(int[][] matrix, int row, int col, int prevValue) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] != prevValue) {
            return 0;
        }


        int originalValue = matrix[row][col];
        matrix[row][col] = -1; // Mark as visited for current path

        int left = dfs(matrix, row, col - 1, originalValue);
        int right = dfs(matrix, row, col + 1, originalValue);
        int up = dfs(matrix, row - 1, col, originalValue);
        int down = dfs(matrix, row + 1, col, originalValue);

        matrix[row][col] = originalValue; // Reset for other paths
        return 1 + Math.max(Math.max(left, right), Math.max(up, down));
    }

    public static int longestPath(int[][] matrix) {
        int maxPath = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, matrix[i][j]));
            }
        }

        return maxPath;
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 1, 2, 1},
                {5, 5, 5, 5},
                {5, 5, 5, 1}
        };

        System.out.println("Longest path length: " + longestPath(nums)); // Output: 7

        int[][] nums2 = {
                {1, 1},
                {5, 5},
                {5, 5}
        };

        System.out.println("Longest path length: " + longestPath(nums2)); // Output: 4
    }
}

