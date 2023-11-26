package leetcode.array.grid.math;

import java.util.ArrayList;

/**
 * Implementation of Sparse Matrix Multiplication using Compressed Sparse Row (CSR) format.
 * Ref: https://leetcode.com/problems/sparse-matrix-multiplication/editorial/
 */
class SparseMatrixMultiplicationCSR {

    /**
     * Represents a sparse matrix in CSR or CSC format.
     */
    static class SparseMatrix {
        public int cols, rows;
        public ArrayList<Integer> values, colIndices, rowIndices;

        /**
         * Compresses a matrix in either CSR (row-wise) or CSC (column-wise) format.
         *
         * @param matrix  2D integer array representing the original matrix.
         * @param colWise Boolean indicating if the compression is column-wise (CSC).
         *                If false, the compression is row-wise (CSR).
         */
        public SparseMatrix(int[][] matrix, boolean colWise) {
            this.rows = matrix.length;
            this.cols = matrix[0].length;
            this.values = new ArrayList<>();
            this.rowIndices = new ArrayList<>();
            this.colIndices = new ArrayList<>();

            if (colWise) {
                // CSC Compression
                this.colIndices.add(0);
                for (int col = 0; col < cols; col++) {
                    for (int row = 0; row < rows; row++) {
                        if (matrix[row][col] != 0) {
                            values.add(matrix[row][col]);
                            rowIndices.add(row);
                        }
                    }
                    colIndices.add(values.size());
                }
            } else {
                // CSR Compression
                this.rowIndices.add(0); // Used for both CSR and CSC
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        if (matrix[row][col] != 0) {
                            values.add(matrix[row][col]);
                            colIndices.add(col);
                        }
                    }
                    rowIndices.add(values.size());
                }
            }
        }

        /**
         * Multiplies two matrices using their sparse representations.
         *
         * @param mat1 The first matrix in normal 2D array format.
         * @param mat2 The second matrix in normal 2D array format.
         * @return The product matrix in normal 2D array format.
         */
        public int[][] multiply(int[][] mat1, int[][] mat2) {
            SparseMatrix A = new SparseMatrix(mat1, false);
            SparseMatrix B = new SparseMatrix(mat2, true);
            int[][] result = new int[mat1.length][mat2[0].length];

            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result[0].length; col++) {
                    int startA = A.rowIndices.get(row);
                    int endA = A.rowIndices.get(row + 1);
                    int startB = B.colIndices.get(col);
                    int endB = B.colIndices.get(col + 1);

                    // Multiply and add matching index elements
                    while (startA < endA && startB < endB) {
                        if (A.colIndices.get(startA) < B.rowIndices.get(startB)) {
                            startA++;
                        } else if (A.colIndices.get(startA) > B.rowIndices.get(startB)) {
                            startB++;
                        } else {
                            result[row][col] += A.values.get(startA) * B.values.get(startB);
                            startA++;
                            startB++;
                        }
                    }
                }
            }

            return result;
        }
    }
}
