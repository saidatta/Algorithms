package leetcode.backtracking.array;

// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
public class PartitionKEqualSums {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is not divisible by k, partitioning is not possible
        if (totalSum % k != 0) {
            return false;
        }

        int subsetSum = totalSum / k;
        boolean[] used = new boolean[nums.length];

        return canPartition(0, nums, used, k, 0, subsetSum);
    }

    private boolean canPartition(int startIndex, int[] nums, boolean[] used, int k, int currentSum, int subsetSum) {
        if (k == 1) {
            return true;
        }

        if (currentSum == subsetSum) {
            // Found one subset, now look for next
            return canPartition(0, nums, used, k - 1, 0, subsetSum);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!used[i] && currentSum + nums[i] <= subsetSum) {
                used[i] = true;
                if (canPartition(i + 1, nums, used, k, currentSum + nums[i], subsetSum)) {
                    return true;
                }
                used[i] = false;
            }
        }

        return false;
    }
}
