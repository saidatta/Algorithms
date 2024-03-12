package leetcode.dp;

// https://leetcode.com/problems/minimum-falling-path-sum/description/
public class MinimumFallingPathSum {
    public static int minFallingPathSum(int[][] matrix) {
        // Initialize a one-dimensional array to store the minimum falling path sum for each column.
        // Its length is matrix.length + 1 for simplicity in handling edge cases.
        int[] prevRowFallingSums = new int[matrix.length + 1];

        // Start iterating from the bottom row to the top row of the matrix.
        for (int row = matrix.length - 1; row >= 0; row--) {
            // Array to store the falling path sums for the current row.
            int[] currentRowFallingSums = new int[matrix.length + 1];

            // Iterate over each column in the current row.
            for (int col = 0; col < matrix.length; col++) {
                // If we are at the first column, there is no left diagonal element.
                if (col == 0) {
                    currentRowFallingSums[col] = Math.min(prevRowFallingSums[col],
                                    prevRowFallingSums[col + 1]) + matrix[row][col];
                } else if (col == matrix.length - 1) {
                    // If we are at the last column, there is no right diagonal element.
                    currentRowFallingSums[col] = Math.min(
                            prevRowFallingSums[col],
                            prevRowFallingSums[col - 1]) + matrix[row][col];
                } else {
                    // For middle columns, consider both left and right diagonal elements.
                    currentRowFallingSums[col] =
                            Math.min(prevRowFallingSums[col],
                                    Math.min(prevRowFallingSums[col + 1],
                                            prevRowFallingSums[col - 1])) + matrix[row][col];
                }
            }

            // Update the previous row's falling path sums to be the current row's sums for the next iteration.
            prevRowFallingSums = currentRowFallingSums;
        }

        // After processing all rows, find the minimum falling path sum that starts from the first row.
        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, prevRowFallingSums[startCol]);
        }

        return minFallingSum;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int[][] matrix2 = {{-19, 57}, {-40, -5}};
        System.out.println(minFallingPathSum(matrix1)); // Output: 13
        System.out.println(minFallingPathSum(matrix2)); // Output: -59
    }
}
