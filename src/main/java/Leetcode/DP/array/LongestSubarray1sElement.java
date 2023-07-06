package Leetcode.DP.array;

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
 */
public class LongestSubarray1sElement {
    public int longestSubarray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int previousZero = -1, currentSubarrayLen = 0, maxSubarrayLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (previousZero != -1) {
                    maxSubarrayLen = Math.max(maxSubarrayLen, currentSubarrayLen);
                    // since i is at current 0, we do a -1.
                    currentSubarrayLen = i - previousZero - 1;
                }
                previousZero = i;
            } else {
                currentSubarrayLen++;
            }
        }
        maxSubarrayLen = Math.max(maxSubarrayLen, currentSubarrayLen);
//       if the longest subarray includes all the numbers in the input (i.e., max == nums.length), the algorithm must
//       subtract 1 from the result. This is because the problem statement requires deleting one element from the array.
        return maxSubarrayLen == nums.length ? nums.length - 1 : maxSubarrayLen;
    }
}
