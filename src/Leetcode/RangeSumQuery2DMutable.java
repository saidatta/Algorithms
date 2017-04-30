package Leetcode;

import Leetcode.Tushar.NextPowerOf2;

/**
 * https://leetcode.com/problems/range-sum-query-2d-mutable/#/description
 *
 * Created by venkatamunnangi on 4/29/17.
 */
public class RangeSumQuery2DMutable {

    public RangeSumQuery2DMutable(int[][] matrix) {

    }

    void createSegmentTree(int[][] matrix) {
        int len = matrix.length;
        NextPowerOf2 np2 = new NextPowerOf2();
        np2.nextPowerOf2(len);
    }

    public void update(int row, int col, int val) {

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return -1;
    }
}
