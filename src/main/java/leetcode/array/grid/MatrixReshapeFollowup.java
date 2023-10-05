package leetcode.array.grid;

// Follow up: What if the columns of the given matrix (nums) do not have the same length?
public class MatrixReshapeFollowup {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if ((nums.length == 0) || (nums.length > 100)) {
            return nums;
        }
        int[][] res = new int[r][c];
        // 1-dimension index of entry;
        int count = 0;
        for (int[] column : nums) {
            if ((column.length == 0) || (column.length > 100)) {
                return nums;
            }
            for (int i : column) {
                if (count + 1 > r * c) {
                    // return original if output matrix is too small;
                    return nums;
                }
                res[count / c][count % c] = i;
                count++;
            }
        }
        if (count < r * c) {
            // return original if output matrix is too big;
            return nums;
        }
        return res;
    }

    public int[][] matrixReshape2(int[][] inputMatrix, int r, int c) {
        int totalElements = 0;

        for (int[] current : inputMatrix) {
            totalElements += current.length;
        }

        // Check if the reshaping is possible
        if (totalElements != r * c) {
            return inputMatrix;
        }

        int[][] reshapedMatrix = new int[r][c];
        int row = 0, col = 0, innerIndex = 0;

        for (int[] current : inputMatrix) {
            while (innerIndex < current.length) {
                reshapedMatrix[row][col] = current[innerIndex];

                col++;
                innerIndex++;

                // If we reach the end of a row in reshapedMatrix
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
            innerIndex = 0;  // Reset innerIndex for the next row in the original jagged array
        }

        return reshapedMatrix;
    }
}
