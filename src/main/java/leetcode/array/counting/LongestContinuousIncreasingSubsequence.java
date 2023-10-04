package leetcode.array.counting;

// https://leetcode.com/problems/longest-continuous-increasing-subsequence/
public class LongestContinuousIncreasingSubsequence {

    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 1;   // Initialize max length to 1 since single number is always an increasing subsequence
        int currentLength = 1;  // Initialize current length to 1 for the same reason

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentLength++;  // if current number is greater than the previous one, increase current length
                maxLength = Math.max(maxLength, currentLength);  // update the maximum length if needed
            } else {
                currentLength = 1;  // reset current length to 1 if not an increasing sequence
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 4, 7};
        System.out.println(findLengthOfLCIS(nums1));  // Expected output: 3

        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS(nums2));  // Expected output: 1
    }
}
