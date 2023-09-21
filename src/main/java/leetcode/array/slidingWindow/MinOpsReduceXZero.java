package leetcode.array.slidingWindow;

public class MinOpsReduceXZero {
    public static int minOperations(int[] nums, int x) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int n = nums.length;
        int maxLen = -1;
        int windowSum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            windowSum += nums[right];
            while (windowSum > total - x && left <= right) {
                windowSum -= nums[left];
                left += 1;
            }
            if (windowSum == total - x) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen != -1 ? n - maxLen : -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 4, 2, 3};
        int x1 = 5;
        System.out.println(minOperations(nums1, x1));  // Expected: 2

        int[] nums2 = {5, 6, 7, 8, 9};
        int x2 = 4;
        System.out.println(minOperations(nums2, x2));  // Expected: -1

        int[] nums3 = {3, 2, 20, 1, 1, 3};
        int x3 = 10;
        System.out.println(minOperations(nums3, x3));  // Expected: 5
    }
}
