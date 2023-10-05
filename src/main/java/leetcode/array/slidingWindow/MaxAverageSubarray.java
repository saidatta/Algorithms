package leetcode.array.slidingWindow;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-average-subarray-i/
public class MaxAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxAverage = sum;

        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];
            maxAverage = Math.max(maxAverage, sum);
        }

        return maxAverage / k;
    }

    public static void main(String[] args) {
        MaxAverageSubarray solution = new MaxAverageSubarray();

        // Test case 1
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println(solution.findMaxAverage(nums1, k1));  // Expected output: 12.75

        // Test case 2
        int[] nums2 = {5};
        int k2 = 1;
        System.out.println(solution.findMaxAverage(nums2, k2));  // Expected output: 5.0
    }
}
