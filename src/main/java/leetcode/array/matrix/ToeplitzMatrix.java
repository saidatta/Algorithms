package leetcode.array.matrix;

// https://leetcode.com/problems/toeplitz-matrix/
public class ToeplitzMatrix {

    /**
     * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of
     * the matrix into the memory at once?
     * If you can only load one row at a time, you can compare the current row and the next row one element at a time.
     * For example, load row 1, then load row 2, compare the required elements, and then discard row 1. Load row 3,
     * compare with row 2, and so on. This way, even though you are loading one row at a time, you're effectively
     * comparing the elements in the diagonals.
     *
     * What if the matrix is so large that you can only load up a partial row into the memory at once?
     * This is a more challenging scenario. Let's say you can load k elements from a row at once.
     * Load the first k elements of row 1.
     * Load the first k elements of row 2 and compare the required elements with the portion of row 1 in memory.
     * Slide the window: load the next k elements from both rows and continue the comparison.
     * Continue this until you've compared the entirety of row 1 and row 2.
     * Move on to comparing row 2 and row 3 in the same manner.
     * The key is to use a sliding window approach to compare sections of rows that fit in memory.
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
