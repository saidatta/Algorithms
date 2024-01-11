package leetcode.greedy.array;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty
// https://www.youtube.com/watch?v=_AcO35R0fss
class MinimumOperationsArrayEmpty {
    public int minOperations(int[] nums) {
        // Create a frequency counter for each unique element in the array
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        // Initialize the minimum number of operations required to make the array empty
        int ans = 0;

        // Iterate through the frequency of each element in the array
        for (int c : counter.values()) {
            // If a number occurs only once, it's impossible to perform any operation to remove it
            if (c == 1) {
                return -1;
            }

            // Add the ceiling of c / 3 to the total operations.
            // This accounts for removing elements in groups of three, or in pairs if needed.
            // The ceiling ensures that we account for any remaining 1 or 2 elements after groups of three.
            ans += (int) Math.ceil((double) c / 3);
        }

        // Return the total minimum number of operations
        return ans;
    }
}

