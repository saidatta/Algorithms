package leetcode.array.prefixSum;

// https://leetcode.com/problems/maximum-sum-score-of-array/description/
public class MaximumSumScoreArray {
    public int maximumSumScore(int[] nums) {
        int n = nums.length;

        // Create the prefixSum and suffixSum arrays.
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];

        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }

        // Calculate the maximum sum score.
        int maxSumScore = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSumScore = Math.max(maxSumScore, Math.max(prefixSum[i], suffixSum[i]));
        }

        return maxSumScore;
    }

}
