package leetcode.array.traversal;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/#/description
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class SearchA2DMatrixII {
//    To search for the target in the given matrix efficiently, we can start from the top right corner of the matrix.
//    This will allow us to prune a row or column at each step, depending on the comparison between the current matrix
//    value and the target:
//
//    If the target is smaller than the current value, we can move one step to the left.
//    If the target is larger than the current value, we can move one step down.
//    This method will ensure that we don't traverse the same row or column more than once, making it efficient.
//

//    The runtime complexity of this approach is O(m + n) where m is the number of rows and n is the number of columns
//    in the matrix. This is because in the worst case, we might need to traverse all rows and all columns once.

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = 0;
        // Start from the top right corner.
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                // Found the target
                return true;
            } else if (matrix[row][col] < target) {
                // Move down.
                row++;
            } else {
                // Move left.
                col--;
            }
        }

        // Target not found in the matrix
        return false;
    }

    public static void main(String[] args) {
        SearchA2DMatrixII searcher = new SearchA2DMatrixII();
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(searcher.searchMatrix(matrix, 5));  // true
        System.out.println(searcher.searchMatrix(matrix, 20)); // false
    }
}
