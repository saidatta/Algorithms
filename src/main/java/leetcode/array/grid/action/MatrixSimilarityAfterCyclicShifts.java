package leetcode.array.grid.action;

public class MatrixSimilarityAfterCyclicShifts {

    public static boolean isMatrixSimilarAfterShifts(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] shiftedMat = new int[m][n];

        for (int i = 0; i < m; i++) {
            int[] shiftedRow = shiftRow(mat[i], (i % 2 == 0) ? -k : k, n);
            shiftedMat[i] = shiftedRow;
        }

        return isEqual(mat, shiftedMat);
    }

    private static int[] shiftRow(int[] row, int k, int n) {
        int[] shifted = new int[n];
        k = (k % n + n) % n; // Normalize the shift value
        for (int i = 0; i < n; i++) {
            shifted[(i + k + n) % n] = row[i];
        }
        return shifted;
    }

    private static boolean isEqual(int[][] mat1, int[][] mat2) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                if (mat1[i][j] != mat2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 1, 2}, {5, 5, 5, 5}, {6, 3, 6, 3}};
        int k = 2;
        System.out.println(isMatrixSimilarAfterShifts(mat, k)); // Expected Output: true
    }
}

