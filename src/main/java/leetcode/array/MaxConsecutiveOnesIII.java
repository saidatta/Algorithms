package leetcode.array;

// https://leetcode.com/problems/max-consecutive-ones-iii/description/
public class MaxConsecutiveOnesIII {
    /**
     * Returns the maximum number of continuous ones that can be achieved by flipping at most K zeros.
     *
     * @param A the input array consisting of only 0s and 1s.
     * @param K the maximum number of zeros allowed to flip to 1s.
     * @return the length of the maximum subarray with at most K zeros.
     */
    public int longestOnes(int[] A, int K) {
        int windowStart = 0;       // Start of the window
        int maxLength = 0;         // Maximum length of valid window
        int zeroCount = 0;         // Number of zeros in the current window

        if (A == null || A.length == 0) {
            return 0;
        }

        for (int windowEnd = 0; windowEnd < A.length; windowEnd++) {
            // If the current element is a zero, increase the zero count for the window
            if (A[windowEnd] == 0) {
                zeroCount++;
            }

            // If the number of zeros exceeds K, shrink the window
            while (windowStart <= windowEnd && zeroCount > K) {
                if (A[windowStart] == 0) {
                    zeroCount--;
                }
                windowStart++;
            }

            // Update the maximum window length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII solution = new MaxConsecutiveOnesIII();

        // Example 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        int output1 = solution.longestOnes(nums1, k1);
        System.out.println("Example 1 Output: " + output1); // Expected Output: 6

        // Example 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        int output2 = solution.longestOnes(nums2, k2);
        System.out.println("Example 2 Output: " + output2); // Expected Output: 10
    }
}
