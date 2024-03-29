package leetcode.array;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/description/
public class MaxSubarraySumWithOneDeletion {

    /**
     * Calculate the maximum subarray sum after potentially removing one element.
     *
     * @param arr Input array of integers.
     * @return The maximum subarray sum after deletion.
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;

        if (n == 1) return arr[0];

        // Initialize the two prefix-sum arrays
        int[] leftToRightSum = new int[n];
        int[] rightToLeftSum = new int[n];

        // Calculate the total sum and the maximum element in the array
        int totalSum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int j : arr) {
            totalSum += j;
            maxElement = Math.max(maxElement, j);
        }

        // Calculate the left-to-right prefix sum using Kadane's algorithm
        int currSumFromLeft = 0;
        for (int i = 0; i < n; i++) {
            currSumFromLeft += arr[i];
            if (currSumFromLeft < 0) {
                currSumFromLeft = 0;
            }
            leftToRightSum[i] = Math.max(arr[i], currSumFromLeft);
        }

        // Calculate the right-to-left prefix sum using Kadane's algorithm
        int currSumFromRight = 0;
        for (int i = n - 1; i >= 0; i--) {
            currSumFromRight += arr[i];
            if (currSumFromRight < 0) {
                currSumFromRight = 0;
            }
            rightToLeftSum[i] = Math.max(arr[i], currSumFromRight);
        }

        // Traverse the array to find the maximum subarray sum after potentially removing one element
        int maxSumAfterRemoval = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int currentMax;
            if (i == 0) {
                currentMax = Math.max(totalSum, rightToLeftSum[i + 1]);
            } else if (i == n - 1) {
                currentMax = Math.max(totalSum, leftToRightSum[i - 1]);
            } else {
                currentMax = Math.max(totalSum, rightToLeftSum[i + 1] + leftToRightSum[i - 1]);
            }
            maxSumAfterRemoval = Math.max(maxSumAfterRemoval, currentMax);
        }

        return maxSumAfterRemoval == 0 ? maxElement : maxSumAfterRemoval;
    }

    public static void main(String[] args) {
        MaxSubarraySumWithOneDeletion solution = new MaxSubarraySumWithOneDeletion();

        // Test Case 1
        int[] arr1 = {1, -2, 0, 3};
        System.out.println(solution.maximumSum(arr1)); // Expected output: 4 (subarray [1, -2, 0, 3])

        // Test Case 2
        int[] arr2 = {1, -2, -2, 3};
        System.out.println(solution.maximumSum(arr2)); // Expected output: 3 (subarray [3])

        // Test Case 3
        int[] arr3 = {-1, -1, -1, -1};
        System.out.println(solution.maximumSum(arr3)); // Expected output: -1 (subarray [-1])
    }
}
