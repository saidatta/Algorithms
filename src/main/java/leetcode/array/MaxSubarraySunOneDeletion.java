package leetcode.array;

public class MaxSubarraySunOneDeletion {
    public int maximumSum(int[] arr) {
        int n = arr.length;

        if (n == 1) {
            return arr[0];
        }

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
            if (currSumFromLeft < 0) currSumFromLeft = 0;
            leftToRightSum[i] = Math.max(arr[i], currSumFromLeft);
        }

        // Calculate the right-to-left prefix sum using Kadane's algorithm
        int currSumFromRight = 0;
        for (int i = n - 1; i >= 0; i--) {
            currSumFromRight += arr[i];
            if (currSumFromRight < 0) currSumFromRight = 0;
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
}
