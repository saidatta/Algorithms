package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/#/description
 *
 * O(m+n)
 *
 * Created by venkatamunnangi on 10/2/16.
 */
public class SpiralMatrix {
    //O(n*m)
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> travelledNumbers = new ArrayList<>();

        if (matrix.length == 0) {
            return travelledNumbers;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                travelledNumbers.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                travelledNumbers.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    travelledNumbers.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    travelledNumbers.add(matrix[j][colBegin]);
                }
            }
            colBegin++;
        }

        return travelledNumbers;
    }
}
