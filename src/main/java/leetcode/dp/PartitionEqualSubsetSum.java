package leetcode.dp;

// https://leetcode.com/problems/partition-equal-subset-sum/description/
public class PartitionEqualSubsetSum {
    /**
     * Determine if the input array can be partitioned into two subsets of equal sum.
     *
     * @param nums The input array of numbers.
     * @return True if the array can be partitioned, otherwise false.
     */
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int totalSum = computeTotalSum(nums);

        // If the total sum is odd, it's not possible to split the array into two subsets with equal sum.
        if (totalSum % 2 != 0) {
            return false;
        }

        // The goal becomes finding a subset with a sum of totalSum / 2.
        return hasSubsetWithSum(nums, totalSum / 2);
    }

    /**
     * Compute the total sum of the array elements.
     *
     * @param nums The input array of numbers.
     * @return The total sum of the array.
     */
    private int computeTotalSum(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        return totalSum;
    }

    /**
     * Check if there exists a subset of nums that adds up to the given sum using a dynamic programming approach.
     *
     * @param nums The input array of numbers.
     * @param sum  The target sum.
     * @return True if there's a subset that adds up to sum, otherwise false.
     */
    private boolean hasSubsetWithSum(int[] nums, int sum) {
        boolean[] dp = new boolean[sum + 1];
        // There is always a subset with sum 0: the empty subset.
        dp[0] = true;

        // Update dp array: dp[i] will be true if there's a subset of nums that adds up to i.
        for (int curr : nums) {
            for (int j = sum; j >= curr; j--) {
                dp[j] = dp[j] || dp[j - curr];
            }
        }

        return dp[sum];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();

        // Test 1
        int[] nums1 = {1, 5, 11, 5};
        System.out.println(solution.canPartition(nums1));  // Expected: true (because [1, 5, 5] and [11] are two subsets with equal sum)

        // Test 2
        int[] nums2 = {1, 2, 3, 5};
        System.out.println(solution.canPartition(nums2));  // Expected: false (no way to split these numbers into two subsets with equal sums)
    }

}
