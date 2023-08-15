package Leetcode.array;

/**
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 */
public class CountNegativeMatrix {
    public int countNegatives(int[][] grid) {
        if(grid == null) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for(int j = grid[0].length-1; j >= 0; j--) {
                if(grid[i][j] < 0) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}
