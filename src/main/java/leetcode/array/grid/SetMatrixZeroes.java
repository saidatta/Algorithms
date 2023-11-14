package leetcode.array.grid;

import java.util.Arrays;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        // Arrays to keep track of rows and columns that should be set to 0
        int[] zeroRows = new int[numRows];
        int[] zeroCols = new int[numCols];

        // Initialize arrays with -1 values
        Arrays.fill(zeroRows, -1);
        Arrays.fill(zeroCols, -1);

        // Scan matrix and mark rows and columns that should be set to 0
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = 0;
                    zeroCols[j] = 0;
                }
            }
        }

        // Modify the matrix based on the zeroRows and zeroCols information
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (zeroRows[i] == 0 || zeroCols[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
