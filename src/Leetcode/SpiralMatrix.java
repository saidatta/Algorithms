package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/#/description
 *
 * Created by venkatamunnangi on 10/2/16.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> travelledNumbers = new ArrayList<>();

        if(matrix.length == 0 || matrix[0].length == 0) {
            return travelledNumbers;
        }
        int m = matrix.length, n = matrix[0].length;

        if(m == 1) {
            for(int i = 0;i< n;i++) {
                travelledNumbers.add(matrix[0][i]);
            }
            return travelledNumbers;
        } else if(n == 1) {
            for(int i = 0;i< m;i++) {
                travelledNumbers.add(matrix[i][0]);
            }
            return travelledNumbers;
        }


        int x = 0;
        int y = 0;
        while(m > 0 && n > 0) {

            for(int i = 0;i < n-1; i++) {
                travelledNumbers.add(matrix[x][y++]);
            }
            for(int j = 0;j <m-1; j++) {
                travelledNumbers.add(matrix[x++][y]);
            }
            for(int i = 0;i < n-1; i++) {
                travelledNumbers.add(matrix[x][y--]);
            }
            for(int j = 0;j <m-1; j++) {
                travelledNumbers.add(matrix[x--][y]);
            }
            // x++ - the after increment is crucial
            // as it increments after the loop
            x++;
            y++;
            m = m-2;
            n = n -2;
        }
        return travelledNumbers;
    }
}
