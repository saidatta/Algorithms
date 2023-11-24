package leetcode.dp.array;

/**
 * The class finds the maximum sum of a partitioned array.
 * It divides the array into subarrays of length at most k and then maximizes the sum
 * by changing all elements in a subarray to the subarray's maximum value.
 */
public class MaxSumAfterPartitioning {

    /**
     * Calculates the maximum sum after partitioning the array.
     *
     * @param arr The input array.
     * @param k The maximum length of a subarray.
     * @return The maximum sum obtainable.
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // dp[i] stores the maximum sum we can achieve considering the first i elements
        int[] dp = new int[arr.length + 1];

        for (int i = 1; i <= arr.length; i++) {
            int maxPartitionValue = 0; // Keeps track of the maximum value in the current partition

            // Try different partition sizes up to k
            for (int currPartitionSize = 1; currPartitionSize <= k && currPartitionSize <= i; currPartitionSize++) {
                // Update the maximum value in the current partition
                maxPartitionValue = Math.max(maxPartitionValue, arr[i - currPartitionSize]);

                // Update dp[i] by considering the partition ending at i with length currPartitionSize
                dp[i] = Math.max(dp[i], dp[i - currPartitionSize] + maxPartitionValue * currPartitionSize);
            }
        }

        return dp[arr.length]; // The maximum sum for the whole array
    }

    public static void main(String[] args) {
        MaxSumAfterPartitioning solution = new MaxSumAfterPartitioning();

        // Example input
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        // Calculate the maximum sum
        int result = solution.maxSumAfterPartitioning(arr, k);

        // Print the result
        System.out.println("Maximum Sum: " + result);
    }
}
