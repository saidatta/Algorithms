package Leetcode;

/**
 * https://leetcode.com/problems/rotate-image/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class RotateImage {
    public void rotate(int[][] matrix) {

        //transpose the matrix
        transposeMatrix(matrix);

        // horizontally flip it.
        horizontalFlip(matrix);
    }

    private void transposeMatrix(int[][] matrix) {
        for(int i = 0; i<matrix.length;i++) {
            // j = i is not to allow re-do the swap 0,1 -> 1,0 case.
            for(int j = i; j< matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void horizontalFlip(int[][] matrix) {
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}
