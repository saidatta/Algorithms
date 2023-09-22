package leetcode.array.slidingWindow;

public class MinOpsReduceXZero {
    /**
     * Calculates the minimum operations required to reduce the array sum to a specific target.
     * This function essentially finds the maximum subarray with a specific sum.
     *
     * @param nums Array of integers.
     * @param x    The target to achieve by removing elements.
     * @return The minimum number of operations to achieve the target sum, or -1 if not possible.
     */
    public int minOperations(int[] nums, int x) {
        // Calculate the total sum of the array.
        int totalSum = computeTotal(nums);

        int arrayLength = nums.length;

        // The goal is to find the maximum subarray with sum = totalSum - x
        int target = totalSum - x;

        // If the total is already equal to the target, there's no need for operations.
        if (target == 0) {
            return arrayLength;
        }

        // Initialize two pointers for sliding window and other variables.
        int leftPointer = 0;
        int currentWindowSum = 0;
        int maxLength = -1;

        for (int rightPointer = 0; rightPointer < arrayLength; rightPointer++) {
            currentWindowSum += nums[rightPointer];

            // Reduce the window size from the left if the sum exceeds the target.
            while (currentWindowSum > target && leftPointer < arrayLength) {
                currentWindowSum -= nums[leftPointer];
                leftPointer++;
            }

            // If a matching subarray is found, update the maximum length.
            if (currentWindowSum == target) {
                maxLength = Math.max(maxLength, rightPointer - leftPointer + 1);
            }
        }

        // If no subarray was found, return -1, otherwise, return the number of elements outside the max subarray.
        return maxLength == -1 ? -1 : arrayLength - maxLength;
    }

    /**
     * Helper function to compute the total sum of the array.
     */
    private int computeTotal(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }


    public static void main(String[] args) {
//        int[] nums1 = {1, 1, 4, 2, 3};
//        int x1 = 5;
//        System.out.println(minOperations(nums1, x1));  // Expected: 2
//
//        int[] nums2 = {5, 6, 7, 8, 9};
//        int x2 = 4;
//        System.out.println(minOperations(nums2, x2));  // Expected: -1
//
//        int[] nums3 = {3, 2, 20, 1, 1, 3};
//        int x3 = 10;
//        System.out.println(minOperations(nums3, x3));  // Expected: 5
    }
}
