package leetcode.binary.bitmask;

import java.util.Arrays;
import java.util.stream.IntStream;

// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
class PartitionKEqualSumsBitMask {
    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;

        // Calculate the sum of all elements in the array.
        int totalArraySum = Arrays.stream(arr).sum();

        // Check if the total sum is divisible by k. If not, partitioning is not possible.
        if (totalArraySum % k != 0) {
            return false;
        }

        // Target sum for each subset.
        int targetSum = totalArraySum / k;

        // Initialize an array to store subset sums for each state represented by a bitmask.
        int[] subsetSum = IntStream.range(0, (1 << n)).map(i -> -1).toArray();
        // The state with no elements picked has a subset sum of 0.
        subsetSum[0] = 0;

        // Iterate over all possible states represented by bitmasks.
        for (int mask = 0; mask < (1 << n); mask++) {
            // Skip this state if it's not reachable.
            if (subsetSum[mask] == -1) {
                continue;
            }

            // Try adding each array element to the current subset.
            for (int i = 0; i < n; i++) {
                // Check if the element was not picked earlier and can be added without exceeding the target sum.
                if ((mask & (1 << i)) == 0 && subsetSum[mask] + arr[i] <= targetSum) {
                    subsetSum[mask | (1 << i)] = (subsetSum[mask] + arr[i]) % targetSum;
                }
            }

            // If all elements are included and the subset sum is 0, return true.
            if (subsetSum[(1 << n) - 1] == 0) {
                return true;
            }
        }

        // Check if the final state (all elements included) has a valid subset sum.
        return subsetSum[(1 << n) - 1] == 0;
    }
}

// 1. The iterative dynamic programming (DP) approach, as opposed to the depth-first search (DFS) with memoization,
//    eliminates the need for recursive function calls and stack space usage, making it more efficient in terms of
//    both time and space.
// 2. However, this efficiency comes with a trade-off: the iterative DP method systematically explores all subproblems,
//    unlike the DFS + Memo approach, which may skip certain subproblems, potentially leading to a higher overall count
//    of subproblems being evaluated.
// 3. The core of this iterative DP approach is the use of a bitmask to represent the state, indicating which elements
//    from the array have been included in the current subset.
// 4. The algorithm adds a non-picked element to the subset, updating the bitmask and the subset sum, and ensures
//    that the sum does not exceed the target sum, resetting when a subset equals the target sum.
// 5. The solution is deemed successful when every possible subset has been considered, and the final DP state
//    (represented by a bitmask with all bits set) yields a subset sum that is a multiple of the target sum, indicating
//    exactly k subsets have been formed.