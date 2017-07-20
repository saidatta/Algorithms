package Leetcode;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/#/description
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        //positive and negative?
        // can the stuff be null?
        // same number be equal multiple times.
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
