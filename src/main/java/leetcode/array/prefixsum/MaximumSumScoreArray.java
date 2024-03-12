package leetcode.array.prefixsum;

// https://leetcode.com/problems/maximum-sum-score-of-array/description/
public class MaximumSumScoreArray {
    // A constant representing the lowest possible score for initialization.
    private static final long MINIMUM_SCORE = -1_000_000_000L;

    /**
     * Calculate the maximum sum score based on the input array.
     *
     * @param A Input array of integers.
     * @return Maximum sum score.
     */
    public long maximumSumScore(int[] A) {
        int n = A.length;

        // Initialize prefix sum array with an additional element for easier computation.
        long[] prefixSum = new long[n + 1];
        long ans = MINIMUM_SCORE;

        // Compute the prefix sum for the array.
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = A[i - 1] + prefixSum[i - 1];
        }

        // For each element in A, calculate the left and right sum.
        // Then determine the maximum between them and update the answer accordingly.
        for (int i = 0; i < n; i++) {
            long leftSum = prefixSum[i + 1] - prefixSum[0];
            long rightSum = prefixSum[prefixSum.length - 1] - prefixSum[i];

            long currentMax = Math.max(leftSum, rightSum);
            ans = Math.max(ans, currentMax);
        }

        return ans;
    }

}
