package leetcode.array;

import java.util.*;

public class SubarraySumEqualsK {

    /**
     * Calculates the number of subarrays whose sum equals k.
     *
     * @param nums Array of numbers.
     * @param k Target sum.
     * @return The number of subarrays with sum equal to k.
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        int count = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;

            if (sum == k) {
                count++;
            }

            // Increment the count of current prefix sum using lambda
            prefixSumCount.compute(sum, (key, val) -> val == null ? 1 : val + 1);

            // If there are previous prefix sums with value (sum - k),
            // then there are that many subarrays that end at current index with sum k.
            count += prefixSumCount.getOrDefault(sum - k, 0);

            if (k == 0) {
                // Adjust count for subarrays with sum 0
                count--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
//        int[] nums = {1, 2, 3, 2, -2, 3};
        int[] nums = {1,1,1};
        int k = 2;
        int result = solution.subarraySum(nums, k);
        System.out.println("Number of subarrays with sum " + k + ": " + result); // Expected output: 4
    }
}

