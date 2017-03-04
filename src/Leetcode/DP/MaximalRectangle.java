package Leetcode.DP;

import java.util.Arrays;

/**
 * Created by venkatamunnangi on 3/3/17.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[] left = new int[cols], right = new int[cols], top = new int[cols];
        Arrays.fill(left, cols); // max distance (inclusive) to left-most 1 at (y,x)
        Arrays.fill(right, cols); // max distance (inclusive) to right-most 1 at (y,x)
        Arrays.fill(top, 0); // max distance (inclusive) to top-most 1 at (y,x)
        int max = 0;
        for (char[] aMatrix : matrix) {
            for (int x = 0; x < cols; x++) {
                if (aMatrix[x] == '1') {
                    top[x] += 1;
                } else {
                    top[x] = 0;
                }
            }
            int l = 0; // max left distance so far
            for (int x = 0; x < cols; x++) {
                if (aMatrix[x] == '1') {
                    left[x] = Math.min(left[x], ++l);
                } else {
                    left[x] = cols;
                    l = 0;
                }
            }
            int r = 0; // max right distance so far
            for (int x = cols - 1; x >= 0; x--) {
                if (aMatrix[x] == '1') {
                    right[x] = Math.min(right[x], ++r);
                } else {
                    right[x] = cols;
                    r = 0;
                }
            }
            for (int x = 0; x < cols; x++) {
                if (aMatrix[x] == '1') {
                    // width should exclude double count of current element
                    max = Math.max(max, (left[x] + right[x] - 1) * top[x]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();

        char[][] matrix = {"10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(), "10010".toCharArray()};
        System.out.println(mr.maximalRectangle(matrix));
    }
}
