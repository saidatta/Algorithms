package Leetcode.Array;

import java.util.Arrays;

public class ArrayPartition {
    public static int minValuePartition(int[] nums) {
        Arrays.sort(nums);
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int currentDiff = Math.abs(nums[i] - nums[i-1]);
            minValue = Math.min(minValue, currentDiff);
        }
        return minValue;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 2, 4};
        int[] nums2 = {100, 1, 10};
        System.out.println(minValuePartition(nums1));  // Output: 1
        System.out.println(minValuePartition(nums2));  // Output: 9
    }
}