package leetcode.array.grid;

import java.util.Arrays;

// https://leetcode.com/problems/reshape-the-matrix/
public class MatrixReshape {
    public static int[][] matrixReshape(int[][] inputMatrix, int r, int c) {
        int m = inputMatrix.length;
        int n = inputMatrix[0].length;

        // Check if the reshaping is possible
        if (m * n != r * c) {
            return inputMatrix;
        }

        int[][] reshapedMatrix = new int[r][c];
        int row = 0, col = 0;

        for (int[] currentMatrix : inputMatrix) {
            for (int j = 0; j < n; j++) {
                reshapedMatrix[row][col] = currentMatrix[j];

                col++;

                // If we reach the end of a row in reshapedMatrix
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        }

        return reshapedMatrix;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 2}, {3, 4}};
        int[][] reshaped1 = matrixReshape(mat1, 1, 4);
        for (int[] row : reshaped1) {
            System.out.println(Arrays.toString(row));
        }

        int[][] mat2 = {{1, 2}, {3, 4}};
        int[][] reshaped2 = matrixReshape(mat2, 2, 4);
        for (int[] row : reshaped2) {
            System.out.println(Arrays.toString(row));
        }
    }

}
