package leetcode.dp;

/**
 * The class provides an efficient mechanism to compute the sum of elements within a
 * 2D region of a matrix. It pre-computes a cumulative region sum with respect to
 * the origin at (0,0) which allows sum queries to be answered in constant time.
 * https://leetcode.com/problems/range-sum-query-2d-immutable/#/description
 *
 * O(1) sumRegion time
 * Created by venkatamunnangi on 3/24/17.
 */

public class RangeSumQuery2DImmutable {

    // prefixSum stores the cumulative sum of regions with respect to the origin (0,0)
    private final int[][] prefixSum;

    /**
     * Constructs and pre-computes a cumulative region sum for the given matrix.
     *
     * @param matrix The input 2D matrix.
     */
    public RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            prefixSum = new int[0][0];
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        prefixSum = new int[rows + 1][cols + 1];

        // Construct the prefix sum matrix.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                prefixSum[r + 1][c + 1] = matrix[r][c]
                        + prefixSum[r + 1][c]
                        + prefixSum[r][c + 1]
                        - prefixSum[r][c];  // Avoid double counting the overlap
            }
        }
    }

    /**
     * Computes the sum of elements in the given 2D region using the principle
     * of inclusion-exclusion.
     *
     * Sum(ABCD) = Sum(OD) - Sum(OB) - Sum(OC) + Sum(OA)
     *
     * @param row1 The starting row of the region.
     * @param col1 The starting column of the region.
     * @param row2 The ending row of the region.
     * @param col2 The ending column of the region.
     * @return The sum of elements in the defined region.
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1]      // Sum(OD)
                - prefixSum[row1][col2 + 1]          // Sum(OB)
                - prefixSum[row2 + 1][col1]          // Sum(OC)
                + prefixSum[row1][col1];             // Sum(OA)
    }
}

