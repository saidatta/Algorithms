package leetcode.array.binarysearch;

// https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence {
    /**
     * Determines the length of the longest increasing subsequence in the given array.
     *
     * @param nums The input array of numbers.
     * @return Length of the longest increasing subsequence.
     */
    public int lengthOfLIS(int[] nums) {
        // `tails` is an array storing the smallest tail of all increasing subsequences
        // of length `i + 1`. tails[i] is the value of the last element of the subsequence.
        int[] tails = new int[nums.length];

        // Represents the length of the longest increasing subsequence found so far.
        int size = 0;

        // Iterate through each number in the input.
        for (int num : nums) {
            // Use binary search to find the position of `num` in tails array.
            int left = 0, right = size;
            while (left != right) {
                int mid = (left + right) >>> 1;

                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // Update the tails array with the new number.
            tails[left] = num;

            // If `num` is the largest among all end elements of current LIS,
            // then increment the length of LIS.
            if (left == size) {
                ++size;
            }
        }

        return size;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();

        // Test cases
        int[] test1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solver.lengthOfLIS(test1));  // Expected output: 4

        int[] test2 = {0, 1, 0, 3, 2, 3};
        System.out.println(solver.lengthOfLIS(test2));  // Expected output: 4

        int[] test3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(solver.lengthOfLIS(test3));  // Expected output: 1
    }
}
