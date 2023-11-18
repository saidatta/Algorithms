package leetcode.array.sorting;

import java.util.Arrays;

// https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/description/
public class MinimizeMaxPairSum {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int maxSum = -1; // only +ve numbers.
        for (int i = 0; i < nums.length / 2; i++) {
            // you want to create a collection of pairs of the least values together
            // after sorting, max number can be paired with min number to generate least pair.
            maxSum = Math.max(maxSum, nums[i] + nums[n - 1 - i]);
        }

        return maxSum;
    }
}
