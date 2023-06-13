package Leetcode.Array;

/**
 * https://leetcode.com/problems/equal-row-and-column-pairs/
 */

//Time complexity: n^2
//Space complexity: O(n)
public class EqualRowColumnPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length, ans = 0;
        long[] rowSum = new long[n];
        long[] colSum = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] = rowSum[i] * 10 + grid[i][j];
                colSum[j] = colSum[j] * 10 + grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rowSum[i] == colSum[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
