package leetcode.array.search;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum-less-than-k/description/
public class TwoSumLessThanK {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        int maxSum = -1;

        while (left < right) {
            int curSum = nums[left] + nums[right];
            if (curSum < k) {
                maxSum = Math.max(maxSum, curSum);
                left++;
            } else {
                right--;
            }
        }

        return maxSum;
    }
}
