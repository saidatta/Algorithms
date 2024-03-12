package leetcode.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

//
public class WaysToPartition {
    // Time Complexity: O(n), Space Complexity: O(n)
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array to store cumulative sums
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        // Map to count contributions of prefixes without changing any element
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(prefixSum[0], 1);

        // Calculate prefix sums and update the count map
        for (int i = 1; i < n - 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            prefixCount.put(prefixSum[i], prefixCount.getOrDefault(prefixSum[i], 0) + 1);
        }
        // Complete the prefix sum for the last element
        prefixSum[n - 1] = prefixSum[n - 2] + nums[n - 1];

        int totalSum = prefixSum[n - 1];
        int maxWays = 0;
        // Check if the total sum is even and update maxWays
        if (totalSum % 2 == 0) {
            maxWays = prefixCount.getOrDefault(totalSum / 2, 0);
        }

        // Map to store the count of prefixes before the current index
        Map<Integer, Integer> prefixBeforeCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int diff = k - nums[i];
            int changedSum = totalSum + diff;
            // Check if changing the current element leads to an even total sum
            if (changedSum % 2 == 0) {
                maxWays = Math.max(maxWays,
                        prefixCount.getOrDefault(changedSum / 2 - diff, 0) +
                                prefixBeforeCount.getOrDefault(changedSum / 2, 0));
            }
            // Update prefix count maps after considering each element
            prefixCount.put(prefixSum[i], prefixCount.getOrDefault(prefixSum[i], 0) - 1);
            prefixBeforeCount.put(prefixSum[i], prefixBeforeCount.getOrDefault(prefixSum[i], 0) + 1);
        }
        return maxWays;
    }
}


