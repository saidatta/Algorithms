package leetcode.dp.array;

import java.util.Arrays;

// The idea is to find the maximum sum of subarrays of length k and then find the best combination of these subarrays
// that are non-overlapping.
//
//  A step-by-step breakdown of the algorithm:
//  1. Calculate Cumulative Sums: First, calculate the cumulative sum of the array. This will allow us to quickly
//      compute the sum of any subarray of length k.
//  2. Find Max Sum Subarrays: Compute the max sum subarray for each possible starting position, from left to right,
//      and then from right to left. This will help in quickly identifying the best subarray to pair with others.
//  3. Dynamic Programming for Optimal Subarrays:
//     - Use two arrays to store the the starting position index of the max sum subarray ending at or before index
//        i from left to right and from right to left.
//     - Iterate through the array, and for each position i, calculate the sum of three subarrays: the best subarray
//       to the left of i, the subarray starting at i, and the best subarray to the right of i + k - 1.
//     - Keep track of the maximum sum and the corresponding indices.
//   4. Return Result: Return the indices of the three subarrays that give the maximum sum.
//
// https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
public class MaximumSum3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int numElements = nums.length;
        int[] cumulativeSum = new int[numElements + 1];
        int[] bestLeftIndex = new int[numElements];
        int[] bestRightIndex = new int[numElements];
        int[] resultIndices = new int[3];

        // Calculate cumulative sum for efficient subarray sum computation
        for (int i = 0; i < numElements; i++) {
            cumulativeSum[i + 1] = cumulativeSum[i] + nums[i];
        }

        // Left pass: Find the best subarray ending at or before each index
        int maxSumLeft = sum(cumulativeSum, 0, k);
        bestLeftIndex[k - 1] = 0;
        for (int i = k; i < numElements; i++) {
            int currentSum = sum(cumulativeSum, i - k + 1, i + 1);
            if (currentSum > maxSumLeft) {
                bestLeftIndex[i] = i - k + 1;
                maxSumLeft = currentSum;
            } else {
                bestLeftIndex[i] = bestLeftIndex[i - 1];
            }
        }

        // Right pass: Find the best subarray starting at or after each index
        int maxSumRight = sum(cumulativeSum, numElements - k, numElements);
        bestRightIndex[numElements - k] = numElements - k;
        for (int i = numElements - k - 1; i >= 0; i--) {
            int currentSum = sum(cumulativeSum, i, i + k);
            if (currentSum >= maxSumRight) {
                bestRightIndex[i] = i;
                maxSumRight = currentSum;
            } else {
                bestRightIndex[i] = bestRightIndex[i + 1];
            }
        }

        // Middle pass: Find the max sum of three subarrays
        int maxTotalSum = 0;
        for (int midStart = k; midStart <= numElements - 2 * k; midStart++) {
            int leftStart = bestLeftIndex[midStart - 1];
            int rightStart = bestRightIndex[midStart + k];
            int totalSum = sum(cumulativeSum, leftStart, leftStart + k) +
                    sum(cumulativeSum, midStart, midStart + k) +
                    sum(cumulativeSum, rightStart, rightStart + k);
            if (totalSum > maxTotalSum) {
                maxTotalSum = totalSum;
                resultIndices[0] = leftStart;
                resultIndices[1] = midStart;
                resultIndices[2] = rightStart;
            }
        }

        return resultIndices;
    }

    /**
     * Helper method to compute the sum of elements between start (inclusive) and end (exclusive) indices.
     */
    private int sum(int[] cumulativeSum, int start, int end) {
        return cumulativeSum[end] - cumulativeSum[start];
    }

    public static void main(String[] args) {
        MaximumSum3NonOverlappingSubarrays solver = new MaximumSum3NonOverlappingSubarrays();
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        int[] result = solver.maxSumOfThreeSubarrays(nums, k);
        System.out.println("Indices of max sum subarrays: " + Arrays.toString(result)); // Expected output: [0, 3, 5]
    }
}

