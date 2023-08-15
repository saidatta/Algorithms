package Leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/
 */
public class MaxBeautyArrayAfterOp {
    /**
     * Returns the maximum possible beauty of an array after applying the operation
     * any number of times, which is defined as the length of the longest subsequence
     * consisting of equal elements.
     *
     * @param A the input integer array
     * @param k a non-negative integer
     * @return the maximum possible beauty of the array
     */
    public int maximumBeauty(int[] A, int k) {
        // Sort the array first
        Arrays.sort(A);

        int n = A.length;  // the length of the array
        int i = 0;  // the start of the window
        int j;  // the end of the window

        // Use the sliding window approach
        for (j = 0; j < n; ++j) {
            // If the window is invalid (i.e., max - min > k * 2),
            // slide the window by increasing the start of the window
            if (A[j] - A[i] > k * 2) {
                i++;
            }
        }

        // Return the size of the largest valid window
        return j - i;
    }
}
