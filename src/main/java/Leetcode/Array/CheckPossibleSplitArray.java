package Leetcode.Array;

import java.util.Arrays;
import java.util.concurrent.DelayQueue;

public class CheckPossibleSplitArray {
    public boolean canPartition(int[] nums, int m) {
        Arrays.sort(nums);
        reverse(nums);
        return canSplit(nums, nums.length, m);
    }

    private boolean canSplit(int[] nums, int remaining, int m) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (remaining == 1) {
            return sum >= m;
        }
        for (int i = 1; i < nums.length; i++) {
            int leftSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }
            int rightSum = sum - leftSum;
            if ((leftSum >= m || i == 1) && (rightSum >= m || nums.length - i == 1)) {
                if (canSplit(Arrays.copyOfRange(nums, 0, i), remaining - 1, m) ||
                        canSplit(Arrays.copyOfRange(nums, i, nums.length), remaining - 1, m)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
