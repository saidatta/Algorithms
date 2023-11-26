package leetcode.array.grid.math;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/description/
 * https://leetcode.com/problems/sparse-matrix-multiplication/editorial/
 *
 * Created by venkatamunnangi on 3/25/17.
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] firstMatrix, int[][] secondMatrix) {
        int rowsFirstMatrix = firstMatrix.length; // Number of rows in the first matrix
        int columnsFirstMatrix = firstMatrix[0].length; // Number of columns in the first matrix
        int columnsSecondMatrix = secondMatrix[0].length; // Number of columns in the second matrix

        int[][] resultMatrix =
                new int[rowsFirstMatrix][columnsSecondMatrix]; // Matrix to store the multiplication result

        // Iterate over each row of the first matrix
        for (int row = 0; row < rowsFirstMatrix; row++) {
            // For each non-zero element in the first matrix
            for (int elementPosition = 0; elementPosition < columnsFirstMatrix; elementPosition++) {
                if (firstMatrix[row][elementPosition] != 0) {
                    // Multiply it with each element in the corresponding column of the second matrix
                    for (int colSecondMatrix = 0; colSecondMatrix < columnsSecondMatrix; colSecondMatrix++) {
                        if (secondMatrix[elementPosition][colSecondMatrix] != 0) {
                            resultMatrix[row][colSecondMatrix] +=
                                    firstMatrix[row][elementPosition] * secondMatrix[elementPosition][colSecondMatrix];
                        }
                    }
                }
            }
        }

        return resultMatrix;
    }

    public static void main(String[] args) {
        SparseMatrixMultiplication solution = new SparseMatrixMultiplication();

        int[][] firstMatrix = {{1, 0, 0}, {-1, 0, 3}};
        int[][] secondMatrix = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        int[][] multiplicationResult = solution.multiply(firstMatrix, secondMatrix);

        // Print the resulting matrix
        for (int[] row : multiplicationResult) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

/*
 * Explanation:
 * The algorithm efficiently multiplies two sparse matrices by skipping over zero elements.
 * - We iterate through each element of the first matrix. If the element is non-zero, we then iterate through the
 * corresponding column of the second matrix.
 * - For each non-zero element in the second matrix, we calculate the product with the non-zero element from the
 * first matrix.
 * - This product is added to the appropriate position in the result matrix, accumulating the sum for each position.
 * - This approach significantly reduces the number of operations for sparse matrices, where many elements are zero.
 */


